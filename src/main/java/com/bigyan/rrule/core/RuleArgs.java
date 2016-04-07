package com.bigyan.rrule.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Rules has named parameters.
 * This class is used to hold the arguments to a rule. It uses a ConcurrentHashMap to store the arguments. 
 * @author bigyan
 *
 */
public class RuleArgs {
  Map<String, Object> args = new ConcurrentHashMap<String, Object>();

  /**
   * Get an Arg Value from the map
   * @param name name of the arg
   * @return value of the arg
   */
  public Object getArg(String name) {
    return args.get(name);
  }

  /**
   * Put (Replace or Create) an arg to the map 
   * @param name name of the arg
   * @param value value of the arg
   */
  public void putArg(String name, Object value) {
    args.put(name, value);
  }


}
