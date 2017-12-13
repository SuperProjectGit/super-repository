package com.common.concurrent;

import org.springframework.beans.factory.annotation.Value;

import javax.sql.PooledConnection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author super
 * 通用线程池执行异步任务
 * Create time 2017/12/13 21:16
 */
public class CommonThreadPool {

    private static ThreadPoolExecutor pool = null;

    private CommonThreadPool() {
    }

    static {
        CustomThreadFactory threadFactory = new CustomThreadFactory("common");
        pool = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10), threadFactory);
        pool.allowCoreThreadTimeOut(true);
    }

    public static void asyncExecute(Runnable runnable) {
        pool.execute(runnable);
    }

    public static Integer getPoolSize() {
        return pool.getPoolSize();
    }
}