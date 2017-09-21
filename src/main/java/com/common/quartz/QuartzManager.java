package com.common.quartz;

import jdk.nashorn.internal.scripts.JO;
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
        //paramsAndState();
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
            //scheduler.deleteJob(new JobKey("job1", "group1"));
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
            Thread.sleep(70 * 1000);
            scheduler.shutdown(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cronTrigger() {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(QuartzJobExample.class).withIdentity("job2", "group1").build();
            CronExpression cronExpression = new CronExpression("0/20 * * * * ?");
            cronExpression.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").withSchedule(CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionIgnoreMisfires()).build();
            scheduler.pauseJob(new JobKey("job2", "group1"));
            scheduler.deleteJob(new JobKey("job2", "group1"));
            scheduler.scheduleJob(jobDetail, cronTrigger);
            scheduler.start();
            Thread.sleep(90 * 1000);
            //scheduler.shutdown(true);
            //scheduler = schedulerFactory.getScheduler();
            CronExpression cronExpressionNew = new CronExpression("0/10 * * * * ?");
            cronExpression.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            CronTrigger cronTriggerNew = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").withSchedule(CronScheduleBuilder.cronSchedule(cronExpressionNew).withMisfireHandlingInstructionIgnoreMisfires()).forJob(jobDetail).build();
            scheduler.rescheduleJob(cronTrigger.getKey(), cronTriggerNew);
            scheduler.start();
            Thread.sleep(90 * 1000);
            scheduler.shutdown(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void paramsAndState() {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobDetail jobDetail1 = JobBuilder.newJob(ColorJob.class).withIdentity("job3", "group1").build();
            SimpleTrigger simpleTrigger1 = TriggerBuilder.newTrigger().withIdentity("trigger3", "group1").startAt(new Date()).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(4).withMisfireHandlingInstructionIgnoreMisfires()).build();
            jobDetail1.getJobDataMap().put(ColorJob.FAVORITE_COLOR, "Green");
            jobDetail1.getJobDataMap().put(ColorJob.EXECUTION_COUNT, "1");

            JobDetail jobDetail2 = JobBuilder.newJob(ColorJob.class).withIdentity("job4", "group1").build();
            SimpleTrigger simpleTrigger2 = TriggerBuilder.newTrigger().withIdentity("trigger4", "group1").startAt(new Date()).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(4).withMisfireHandlingInstructionIgnoreMisfires()).build();
            jobDetail2.getJobDataMap().put(ColorJob.FAVORITE_COLOR, "Red");
            jobDetail2.getJobDataMap().put(ColorJob.EXECUTION_COUNT, 1);
            scheduler.scheduleJob(jobDetail1, simpleTrigger1);
            scheduler.scheduleJob(jobDetail2, simpleTrigger2);
            scheduler.start();
            Thread.sleep(60*1000);
            scheduler.shutdown(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
