package com.common.pattern.observer;

/**
 * 具体的观察者
 *
 * @author subo
 * @create 2019-07-30 16:25
 **/
public class ConcreteObserver implements Observer {

  private String name;

  private String message;

  public ConcreteObserver(String name) {
    this.name = name;
  }

  @Override
  public void update(String message) {
    this.message = message;
    System.out.println(name + " 收到消息：" + message);
  }

}
