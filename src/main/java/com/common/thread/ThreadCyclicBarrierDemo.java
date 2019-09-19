package com.common.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author subo
 * @create 2019-09-19 11:05
 **/
public class ThreadCyclicBarrierDemo {

  private static CyclicBarrier c1 = new CyclicBarrier(2);
  private static CyclicBarrier c2 = new CyclicBarrier(2);

  public static void main(String[] args) {
    Thread thread1 = new Thread(() -> {
      try {
        System.out.println("产品经理规划需求");
        c1.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
    });

    Thread thread2 = new Thread(() -> {
      try {
        c1.await();
        System.out.println("开发人员开发新需求功能");
        c2.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
    });

    Thread thread3 = new Thread(() -> {
      try {
        c2.await();
        System.out.println("测试人员测试新功能");
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
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
