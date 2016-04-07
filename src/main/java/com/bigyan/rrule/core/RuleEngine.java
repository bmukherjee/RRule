package com.bigyan.rrule.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.bigyan.rrule.core.composite.CompositeRule;

@XmlRootElement(name = "rrule-engine")
public class RuleEngine {

  protected Properties properties;

  @XmlElement(required = true)
  protected Rules rules;

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {
      "rule"
  })
  static class Rules {

    @XmlElement(required = true)
    protected List<CompositeRule> rule;

    /**
     * Gets the value of the rule property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the rule property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getRule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link CompositeRule }
     * 
     * 
     */
    public List<CompositeRule> getRule() {
      if (rule == null) {
        rule = new ArrayList<CompositeRule>();
      }
      return this.rule;
    }

  }

}
