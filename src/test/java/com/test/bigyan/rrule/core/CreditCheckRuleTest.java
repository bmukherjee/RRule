package com.test.bigyan.rrule.core;

import org.testng.annotations.Test;

import com.bigyan.rrule.core.Rule;
import com.bigyan.rrule.core.RuleArgs;
import com.bigyan.rrule.core.RuleExecutor;
import com.bigyan.rrule.exception.RuleExecutionException;
/**
 * A Basic Rule for Credit Checking.
 * RuleArgs: {
 *    user: {
 *      id: "abcd",
 *      accounts: [ {
 *          accno: 1234,
 *          balance: 100000
 *          }, ... ],
 *    },
 *    debitReq: {
 *      trid: 45,
 *      debitAmount: 30000
 *    }
 * }  
 *      
 * Rule:
 *   condition: args.user.balance >= args.debitReq.debitamount
 *   pass:      execute(bebitReq);
 *   fail:      output("Request Denied: Insufficient Balance");
 *          
 * @author bigyan
 *
 */
public class CreditCheckRuleTest implements Rule{
  
  String message= "";
  

  @Override
  public void init() throws RuleExecutionException {
    System.out.println("CreditCheckRule: Init");
  }

  @Override
  public boolean condition(RuleArgs params) throws RuleExecutionException {
    System.out.println("CreditCheckRule: Condition");
    return (Integer)params.getArg("balance") >= (Integer)params.getArg("debitamount");
     
  }

  @Override
  public void pass(RuleArgs params) throws RuleExecutionException {
    
    System.out.println("CreditCheckRule: Pass");
    message = "passed";
  }

  @Override
  public void fail(RuleArgs params) throws RuleExecutionException {
    System.out.println("CreditCheckRule: Fail");
    message = "failed";
    
  }

  @Override
  public void cleanup() throws RuleExecutionException {
    System.out.println("CreditCheckRule: condition");
    
  }
  
  @Test
  public void creditTest() {
    RuleExecutor executor = new RuleExecutor();
    
    RuleArgs args = new RuleArgs();
    args.putArg("balance", 10000);
    args.putArg("debitamount", 1000);
    
    executor.execute(this, args);
    assert message.equals("passed");
    
    args.putArg("debitamount", 100000);
    
    executor.execute(this, args);
    assert message.equals("failed");
    
    args.putArg("balance", 100000);
    
    executor.execute(this, args);
    assert message.equals("passed");
    
    args.putArg("balance", 0);
    executor.execute(this, args);
    assert message.equals("failed");
    
  }
  
  

}
