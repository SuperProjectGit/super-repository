package com.common.concurrent;

import java.util.concurrent.*;

/**
 * @author super
 * 通用线程池执行异步任务
 * Create time 2017/12/13 21:16
 */
public class CommonThreadPool {

    private static ThreadPoolExecutor pool;

    private CommonThreadPool() {
    }

    static {
        CustomThreadFactory threadFactory = new CustomThreadFactory("common");
        pool = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(400), threadFactory);
        pool.allowCoreThreadTimeOut(true);
    }

    public static void asyncExecute(Runnable runnable) {
        pool.execute(runnable);
    }

    public static Future submit(Callable callable) {
        Future future = null;
        try {
            future = pool.submit(callable);
            String result = (String) future.get();
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return future;
    }

    public static Integer getPoolSize() {
        return pool.getPoolSize();
    }
}
