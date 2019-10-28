package com.common.pattern.visitor;

/**
 * @author subo
 * @create 2019-10-28 17:05
 **/
public class HumanResourceVisitor implements Visitor {

  @Override
  public void visit(Staff staff) {
    System.out.println("human resource :name=" +  staff.getName() + " age=" + staff.getAge());
  }
}
