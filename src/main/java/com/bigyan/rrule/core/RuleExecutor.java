package com.bigyan.rrule.core;

import java.util.List;

import com.bigyan.rrule.exception.RuleExecutionException;

/**
 * This class is used to execute a basic rule.
 * @author bigyan
 *
 */
public class RuleExecutor {

  /**
   * execute a rule with different parameters to if, pass and fail
   * @param rule The rule
   * @param ifArgs args for if method
   * @param passArgs args for pass method
   * @param failArgs args for fail method
   * @return result of condition
   * @throws RuleExecutionException on failure
   */
  public boolean execute(Rule rule, RuleArgs ifArgs, RuleArgs passArgs, RuleArgs failArgs) throws RuleExecutionException{
    rule.init();
    boolean re = rule.condition(ifArgs);
    if(re)
      rule.pass(passArgs);
    else
      rule.fail(failArgs);
    rule.cleanup();
    return re;
  }
  
  /**
   * execute a rule with a set of parameters
   * @param rule the rule to be executed
   * @param args named arglist for the rule
   * @return result of condition
   * @throws RuleExecutionException on failure
   */
  public boolean  execute(Rule rule, RuleArgs args) throws RuleExecutionException{
    return execute(rule, args, args, args);
  }
  

  
  public void executeComposite(List<Rule> ruleList, RuleArgs args) {
    
  }
  //TODO: Add parallel execution apis: need to think. Or maybe parallelism should be done inside the rule itself?
   
}
