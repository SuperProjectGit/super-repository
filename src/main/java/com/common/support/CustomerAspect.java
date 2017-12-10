package com.common.support;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * @author super
 * Create time 2017/12/10 16:31
 */
@Aspect
@Component
public class CustomerAspect {

    @Pointcut("execution(* com.controller.TestController.*(..))")
    private void customerMethod() {

    }

    @Around(value = "customerMethod()")
    public void doCustomerMethod(ProceedingJoinPoint joinpoint) throws Throwable {
        Object[] args = joinpoint.getArgs();
        String[] name = ((CodeSignature)joinpoint.getStaticPart().getSignature()).getParameterNames();
        System.out.println("customer method around");
        args[0] = "aspectjs value";
        MethodSignature signature = (MethodSignature) ((MethodInvocationProceedingJoinPoint)joinpoint).getSignature();
        Annotation[][] annotations = signature.getMethod().getParameterAnnotations();
        for (Annotation[] annotation : annotations) {
            
        }
        joinpoint.proceed(args);
    }

}
