package com.common.concurrent;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * 自定义threadfactory
 *
 * @author subo
 * @create 2017-10-31 16:29
 **/
public class CustomThreadFactory implements ThreadFactory {

    private int counter;

    private String name;

    private List<String> stats;

    public CustomThreadFactory(String name) {
        this.counter = 1;
        this.name = name;
        this.stats = new ArrayList<String>();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, name + "-Thread_" + counter);
        counter++;
        stats.add(String.format("Created thread %d with name %s on %s \\n", thread.getId(), thread.getName(), new Date()));
        return thread;
    }

    public String getStats() {
        StringBuffer buffer = new StringBuffer();
        if (stats.size() > 0) {
            for (String string : stats) {
                buffer.append(string);
            }
            return buffer.toString();
        }
        buffer.append("empty");
        return buffer.toString();
    }
}
