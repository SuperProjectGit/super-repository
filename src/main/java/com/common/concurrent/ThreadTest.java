package com.common.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadTest {
	public static void main(String[] args) {
		ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
		ThreadTest t = new ThreadTest();
		for(int i=0; i< 15; i++) {
			MyTask myTask = new MyTask(i);
			pool.execute(myTask);
			System.out.println("线程池中线程数目：" + pool.getPoolSize() + ", 队列中等待执行的任务数目：" + pool.getQueue().size() + ", 已执行完的任务数目：" + pool.getCompletedTaskCount());
		}
	}
	static class MyTask implements Runnable {
		private Integer taskNum;
		
		public MyTask(int num) {
			this.taskNum = num;
		}
		public void run() {
			System.out.println("正在执行task： " + taskNum);
			try {
				Thread.currentThread().sleep(4000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("task: " + taskNum + "执行完毕");
		}
		
	}
}
