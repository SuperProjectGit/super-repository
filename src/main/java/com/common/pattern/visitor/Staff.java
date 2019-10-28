package com.common.pattern.visitor;

/**
 * 被访问对象接口
 *
 * @author subo
 * @create 2019-10-28 17:00
 **/
public abstract class Staff {

  private String name;

  private Integer age;

  private String kpi;

  public Staff(String name, Integer age, String kpi) {
    this.name = name;
    this.age = age;
    this.kpi = kpi;
  }

  abstract void accept(Visitor visitor);

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getKpi() {
    return kpi;
  }

  public void setKpi(String kpi) {
    this.kpi = kpi;
  }
}
