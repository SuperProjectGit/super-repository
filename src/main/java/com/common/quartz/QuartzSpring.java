package com.common.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * spring quartz
 * spring @Scheduled 不适合分布式环境
 * @author subo
 * @create 2017-09-26 11:27
 **/
@Component
public class QuartzSpring {

    public QuartzSpring() {
        System.out.println("Quartz Spring start......");
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void test() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(format.format(new Date()));
    }

}

