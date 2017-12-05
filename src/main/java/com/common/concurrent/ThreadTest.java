package com.common.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadTest {

	private static final CustomThreadFactory threadFactory = new CustomThreadFactory("test");

	private static final ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5), threadFactory);

	public static void main(String[] args) {
		for(int i=0; i< 12; i++) {
			MyTask myTask = new MyTask(i);
			pool.execute(myTask);
			System.out.println("线程池中线程数目：" + pool.getPoolSize() + ", 队列中等待执行的任务数目：" + pool.getQueue().size() + ", 已执行完的任务数目：" + pool.getCompletedTaskCount());
		}
		new ThreadTest().test1();
		pool.shutdown();
		if (pool.isShutdown()) {
			System.out.println(threadFactory.getStats());
		}
	}

	public void test1() {
		MyTask myTask = new MyTask(10000);
		ThreadTest.pool.execute(myTask);
	}

	static class MyTask implements Runnable {
		private Integer taskNum;
		
		public MyTask(int num) {
			this.taskNum = num;
		}
		@Override
		public void run() {
			System.out.println("正在执行task： " + taskNum);
			try {
				Thread.sleep(4000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("task: " + taskNum + "执行完毕");
		}
		
	}
}
