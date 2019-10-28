package com.common.pattern.visitor;

/**
 * @author subo
 * @create 2019-10-28 17:07
 **/
public class StaffA extends Staff {

  public StaffA(String name, Integer age, String kpi) {
    super(name, age, kpi);
  }

  @Override
  void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
