package com.common.pattern.observer;

/**
 * 被观察者接口
 *
 * @author subo
 * @create 2019-07-30 16:10
 **/
public interface ObservedSub {

  void register(Observer observer);

  void remove(Observer observer);

  void notified();

}
