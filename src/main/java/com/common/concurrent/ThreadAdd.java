package com.common.concurrent;

/**
 * 3个线程，顺序打印1-100，线程1打印1，线程2打印2，线程3打印3。。。以此类推
 *
 * @author super
 * @create 2018-03-29 23:01
 **/
public class ThreadAdd implements Runnable {

    private static final String TAG = "ThreadAdd";

    private static int si = 0;

    private int id;

    ThreadAdd(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        synchronized (ThreadAdd.class) {
            while (si < 100) {
                if (si  % 3 == id) {
                    System.out.print("Thread-" + id + "--->");
                    System.out.print("   " + si++);
                    System.out.println();
                    ThreadAdd.class.notifyAll();
                } else {
                    try {
                        ThreadAdd.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 =  new Thread(new ThreadAdd(0));
        Thread t2 =  new Thread(new ThreadAdd(1));
        Thread t3 =  new Thread(new ThreadAdd(2));
        t1.start();
        t2.start();
        t3.start();
    }
}
