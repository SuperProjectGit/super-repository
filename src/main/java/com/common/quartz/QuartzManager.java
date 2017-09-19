package com.common.quartz;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 定时器管理类
 *
 * @author subo
 * @create 2017-09-13 17:40
 **/
public class QuartzManager {

    public static void main(String[] args) {
        //simpleTrigger();
        cronTrigger();
    }

    public static void simpleTrigger() {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobDetail job = JobBuilder.newJob(QuartzJobExample.class)
                    .withIdentity("job1", "group1")
                    .build();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 1);
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(calendar.getTime()).build();
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
            Thread.sleep(70*1000);
            scheduler.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cronTrigger() {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(QuartzJobExample.class).withIdentity("job1", "group1").build();
            CronExpression cronExpression = new CronExpression("0/20 * * * * ?");
            cronExpression.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
            scheduler.deleteJob(new JobKey("job1", "group1"));
            scheduler.scheduleJob(jobDetail, cronTrigger);
            scheduler.start();
            Thread.sleep(90*1000);
            scheduler.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
