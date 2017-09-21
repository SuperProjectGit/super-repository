package com.common.quartz;

import org.quartz.*;

import java.security.PublicKey;
import java.util.Date;

/**
 * 基础定时任务
 *
 * @author subo
 * @create 2017-09-14 16:03
 **/
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ColorJob implements Job {

    public static final String FAVORITE_COLOR = "favoriteColor";

    public static final String EXECUTION_COUNT = "executionCount";

    private int counter = 1;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data = context.getJobDetail().getJobDataMap();
        String favoriteColor = data.getString(FAVORITE_COLOR);
        int count = data.getInt(EXECUTION_COUNT);
        count++;
        data.put(EXECUTION_COUNT, count);
        System.out.println("ColorJob: " + context.getJobInstance().toString() + " executing at " + new Date() + "\n" +
                "  favorite color is " + favoriteColor + "\n" +
                "  execution count (from job map) is " + count + "\n" +
                "  execution count (from job member variable) is " + counter);
    }
}
