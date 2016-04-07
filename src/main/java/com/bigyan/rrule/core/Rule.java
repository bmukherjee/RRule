package com.bigyan.rrule.core;

import com.bigyan.rrule.exception.RRuleException;
import com.bigyan.rrule.exception.RuleExecutionException;

/**
 * A rule is basically a conditional statement. A RuleExecutor object will accept a Rule and execute
 * it in this order: begin(); if(condition()) then pass(); else fail(); cleanup();
 * 
 * @author bigyan
 *
 */
public interface Rule {

  /**
   * Perform initialization in this method.
   * 
   * @throws RuleExecutionException Wrap all checked exceptions.
   */
  void init() throws RuleExecutionException;

  /**
   * 
   * Evaluate the condition in this method.
   * 
   * 
   * @param params Parameters
   * @return Result of the condition
   * @throws RuleExecutionException Wrap all checked exceptions.
   */
  boolean condition(RuleArgs params) throws RuleExecutionException;

  /**
   * Execute this if the condition passes.
   * 
   * @param params Parameters
   * @throws RuleExecutionException Wrap all checked exceptions.
   */
  void pass(RuleArgs params) throws RuleExecutionException;

  /**
   * Execute this if the condition fails.
   * 
   * @param params Parameters 
   * @throws RuleExecutionException Wrap all checked exceptions.
   */
  void fail(RuleArgs params) throws RuleExecutionException;

  /**
   * Perform cleanup in this method.
   * 
   * @throws RuleExecutionException Wrap all checked exceptions.
   */
  void cleanup() throws RuleExecutionException;

}
