package com.controller;

import com.common.quartz.QuartzSpring;
import com.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试
 *
 * @author subo
 * @create 2017-09-28 11:29
 **/
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private QuartzSpring quartzSpring;

    @Autowired
    private TestService testService;

    @RequestMapping("test")
    @ResponseBody
    public String test() {
        System.out.println("test controller thread:" + Thread.currentThread().getName());
        testService.testService();
        return "success";
    }

    @RequestMapping("test1")
    @ResponseBody
    public String test(String name) {
        System.out.println(name);
        return "success";
    }
}
