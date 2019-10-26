package com.common.thread;

import java.util.concurrent.Semaphore;

/**
 * @author subo
 * @create 2019-09-19 11:05
 **/
public class ThreadSemaphoreDemo {

  private static Semaphore c1 = new Semaphore(1);
  private static Semaphore c2 = new Semaphore(1);

  public static void main(String[] args) {
    Thread thread1 = new Thread(() -> {
      System.out.println("产品经理规划需求");
      c1.release();
    });

    Thread thread2 = new Thread(() -> {
      try {
        c1.acquire();
        System.out.println("开发人员开发新需求功能");
        c2.release();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    Thread thread3 = new Thread(() -> {
      try {
        c2.acquire();
        thread2.join();
        c2.release();
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
