package com.common.concurrent;/**
 * @author super
 * @Description: Create time 2017/12/14 22:03
 */

import java.time.Clock;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 多任务处理
 * @author
 * @create 2017-12-14 22:03
 **/
public class MultiJob<T> {

    public T submit(Callable<T> callable) {
        T t = null;
        try {
            System.out.println(Clock.systemUTC());
            Future future = CommonThreadPool.submit(callable);
            t = (T) future.get();
            System.out.println(Clock.systemUTC());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
