package com.common.concurrent.multithreadsort;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 顺序打印condition
 *
 * @author subo
 * @create 2018-09-04 10:48
 **/
public class SortCondition {

    private static Lock lock = new ReentrantLock();

    private static Condition a = lock.newCondition();

    private static Condition b = lock.newCondition();

    private static Condition c = lock.newCondition();

    private static int count = 0;

    static class ThreadA extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i= 0; i< 10; i++) {
                    while (count%3 == 0) {
                        a.await();
                    }
                    System.out.println("A");
                    count++;
                    b.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i= 0; i< 10; i++) {
                    while (count%3 == 1) {
                        b.await();
                    }
                    System.out.println("B");
                    count++;
                    c.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class ThreadC extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i= 0; i< 10; i++) {
                    while (count%3 == 2) {
                        c.await();
                    }
                    System.out.println("C");
                    count++;
                    a.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }

}
