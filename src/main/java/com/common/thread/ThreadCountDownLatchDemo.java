package com.common.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author subo
 * @create 2019-09-19 11:05
 **/
public class ThreadCountDownLatchDemo {

  private static CountDownLatch c1 = new CountDownLatch(1);
  private static CountDownLatch c2 = new CountDownLatch(1);

  public static void main(String[] args) {
    Thread thread1 = new Thread(() -> {
      System.out.println("产品经理规划需求");
      c1.countDown();
    });

    Thread thread2 = new Thread(() -> {
      try {
        c1.await();
        System.out.println("开发人员开发新需求功能");
        c2.countDown();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    Thread thread3 = new Thread(() -> {
      try {
        c2.await();
        System.out.println("测试人员测试新功能");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    System.out.println("早上：");
    System.out.println("测试人员来上班了");
    thread3.start();
    System.out.println("产品经理来上班了");
    thread1.start();
    System.out.println("开发人员来上班了");
    thread2.start();
  }
}
