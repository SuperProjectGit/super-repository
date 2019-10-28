package com.common.pattern.visitor;

/**
 * @author subo
 * @create 2019-10-28 17:12
 **/
public class Visit {

  public static void main(String[] args) {
    HumanResourceVisitor humanResourceVisitor = new HumanResourceVisitor();
    ManagerVisitor managerVisitor = new ManagerVisitor();
    StaffA staffA = new StaffA("tom", 12, "3.25");
    staffA.accept(humanResourceVisitor);
    staffA.accept(managerVisitor);
    StaffB staffB = new StaffB("jeryy", 15, "3.75");
    staffB.accept(humanResourceVisitor);
    staffB.accept(managerVisitor);
  }

}
