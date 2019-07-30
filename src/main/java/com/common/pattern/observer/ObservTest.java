package com.common.pattern.observer;

/**
 * @author subo
 * @create 2019-07-30 16:28
 **/
public class ObservTest {

  public static void main(String[] args) {
    ConcreteObservedSub observedSub = new ConcreteObservedSub();

    ConcreteObserver tom = new ConcreteObserver("tom");
    ConcreteObserver jerry = new ConcreteObserver("jerry");
    ConcreteObserver mike = new ConcreteObserver("mike");
    observedSub.register(tom);
    observedSub.register(jerry);
    observedSub.register(mike);
    observedSub.setMessage("this is test1 message");
    observedSub.remove(mike);
    observedSub.setMessage("this is test2 message");
  }

}
