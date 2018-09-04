package com.common.concurrent.multithreadsort;

/**
 * 顺序打印Synchronized同步法
 *
 * @author subo
 * @create 2018-09-04 10:26
 **/
public class SortSync {

    public static class ThreadPrinter implements Runnable {
        private Object prev;

        private Object self;

        private String name;

        private ThreadPrinter(String name, Object prev, Object self) {
            this.name = name;
            this.prev = prev;
            this.self = self;
        }

        @Override
        public void run() {
            int count = 10;
            while (count > 0) {
                synchronized (prev) {
                    synchronized (self) {
                        System.out.println(name);
                        count--;
                        self.notifyAll();
                    }
                    try {
                        if (count == 0) {
                            prev.notifyAll();
                        } else {
                            prev.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        ThreadPrinter ta = new ThreadPrinter("a", c, a);
        ThreadPrinter tb = new ThreadPrinter("b", a, b);
        ThreadPrinter tc = new ThreadPrinter("c", b, c);
        new Thread(ta).start();
        Thread.sleep(10);
        new Thread(tb).start();
        Thread.sleep(10);
        new Thread(tc).start();
        Thread.sleep(10);
    }
}
