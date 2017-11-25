package com.service.impl;

import com.service.TestService;
import org.springframework.stereotype.Service;

/**
 *
 * @author super
 * Create time 2017/11/25 20:58
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public void testService() {
        System.out.println("test service thread:" + Thread.currentThread().getName());
    }
}
