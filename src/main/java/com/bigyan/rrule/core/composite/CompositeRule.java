package com.bigyan.rrule.core.composite;

import java.util.LinkedList;
import java.util.List;

import com.bigyan.rrule.core.Rule;
import com.bigyan.rrule.core.RuleArgs;
import com.bigyan.rrule.exception.RuleExecutionException;

public abstract class CompositeRule implements Rule {
  public static enum SideEffectEnum {
    BOTH, NONE, PASS, FAIL
  };

  public static class CompositeRuleElement {
    Rule rule;
    boolean orNext = false;
    boolean inverse = false;
    SideEffectEnum sideEffect = SideEffectEnum.NONE;
  }

  protected List<CompositeRuleElement> ruleList;

  /**
   * This is a Field Accessor method. i.e. it returns a reference to a live list (not snapshot).
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getRuleList().add(newItem);
   * </pre>
   * 
   * @return a reference to the live list
   */
  public List<CompositeRuleElement> getRuleList() {
    if (ruleList == null)
      ruleList = new LinkedList<CompositeRuleElement>();
    return this.ruleList;
  }

  /**
   * This method executes the conditions and side effects(if applicable) of all the rules in this
   * composite rule in sequential order. Execution is short-circuited. e.g. a rule AND-ed with
   * another rule that has already failed is ignored(including side efffects).
   * 
   * @param params params for the rules. All child rules share the same param set.
   */
  @Override
  public final boolean condition(RuleArgs params) throws RuleExecutionException {
    boolean passesRule = true;
    List<CompositeRuleElement> ruleListToEval = new LinkedList<CompositeRuleElement>();
    for(CompositeRuleElement r: ruleList) {
      ruleListToEval.add(r);
      if(r.orNext)
        continue;
      passesRule = passesRule & evaluateORedRules(ruleListToEval, params);
      ruleListToEval.clear();
      if(!passesRule)
        break;      
    }
    if(passesRule && !ruleListToEval.isEmpty()) {
      passesRule = passesRule & evaluateORedRules(ruleListToEval, params);
    }
      
    return passesRule;
  }
  
  protected boolean evaluateORedRules(List<CompositeRuleElement> ruleListToEval, RuleArgs params) {
    boolean passesRule = false;
    for(CompositeRuleElement r: ruleListToEval)      
      passesRule = passesRule & (r.inverse ^ evaluateRule(r.rule, params, r.sideEffect));
 
    return passesRule;
  }
  
  protected boolean evaluateRule(Rule rule, RuleArgs params, SideEffectEnum sideEffect) {
    boolean passes = rule.condition(params);
    switch(sideEffect) {
      case NONE: break;
      case BOTH:
      case PASS: if(passes) rule.pass(params);
      case FAIL: if(!passes) rule.fail(params);
    }
    return passes;
  }

}
