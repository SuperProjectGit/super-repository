package com.common.pattern.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 具体的被观察者
 *
 * @author subo
 * @create 2019-07-30 16:14
 **/
public class ConcreteObservedSub implements ObservedSub {

  private List<Observer> list = new ArrayList<>();

  private String message;

  @Override
  public void register(Observer observer) {
    list.add(observer);
  }

  @Override
  public void remove(Observer observer) {
    if (!list.isEmpty()) {
      list.remove(observer);
    }
  }

  @Override
  public void notified() {
    Optional.ofNullable(list).get().stream().forEach(temp -> temp.update(message));
  }

  public void setMessage(String message) {
    this.message = message;
    notified();
  }
}
