package com.common.pattern.visitor;

/**
 * @author subo
 * @create 2019-10-28 17:01
 **/
public class ManagerVisitor implements Visitor {

  @Override
  public void visit(Staff staff) {
    System.out.println("manager handle:" + staff.getKpi());
  }
}
