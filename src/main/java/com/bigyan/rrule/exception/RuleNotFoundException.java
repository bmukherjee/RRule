package com.bigyan.rrule.exception;
/**
 * Throw this exception when a rule is not found
 * @author bigyan
 *
 */
@SuppressWarnings("serial")
public class RuleNotFoundException extends RRuleException{

  public RuleNotFoundException(String rule, Throwable cause) {
    super("Rule: " + rule + " not found", cause);
  }

}
