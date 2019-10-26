package com.common.thread;

/**
 * @author subo
 * @create 2019-09-19 11:05
 **/
public class ThreadWaitDemo {

  private static Object lock1 = new Object();
  private static Object lock2 = new Object();

  private static boolean t1Run = false;
  private static boolean t2Run = false;

  public static void main(String[] args) {
    Thread thread1 = new Thread(() -> {
      try {
        Thread.sleep(1000);
        synchronized (lock1) {
          System.out.println("产品经理规划需求");
          t1Run = true;
          lock1.notify();
        }
      } catch (InterruptedException e) {

      }
    });

    Thread thread2 = new Thread(() -> {
      synchronized (lock1) {
        try {
          if (!t1Run) {
            System.out.println("开发人员先休息会");
            lock1.wait();
          }
          synchronized (lock2) {
            System.out.println("开发人员开发新需求功能");
            lock2.notify();
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread thread3 = new Thread(() -> {
      synchronized (lock2) {
        try {
          if (!t2Run) {
            System.out.println("测试人员先休息会");
            lock2.wait();
          }
          System.out.println("测试人员测试新功能");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
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
