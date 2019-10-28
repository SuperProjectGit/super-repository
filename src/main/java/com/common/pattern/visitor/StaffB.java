package com.common.pattern.visitor;

/**
 * @author subo
 * @create 2019-10-28 17:11
 **/
public class StaffB extends Staff {

  public StaffB(String name, Integer age, String kpi) {
    super(name, age, kpi);
  }

  @Override
  void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
