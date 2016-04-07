package com.bigyan.rrule.exception;

/**
 * Throw this wrapped exception when executing a rule
 * 
 * @author bigyan
 *
 */
@SuppressWarnings("serial")
public class RuleExecutionException extends RRuleException {

  public RuleExecutionException(String rule, Throwable cause) {
    super("Rule " + rule + ": execution failed because of " + cause.getMessage(), cause);
  }



}
