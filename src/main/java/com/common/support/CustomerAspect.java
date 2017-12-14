package com.common.support;

import com.common.annotations.XSS;
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
        MethodSignature signature = (MethodSignature) ((MethodInvocationProceedingJoinPoint)joinpoint).getSignature();
        Annotation[][] annotations = signature.getMethod().getParameterAnnotations();
        for (int i= 0; i< annotations.length; i++) {
            Annotation[] annotation = annotations[i];
            boolean flag = false;
            for (Annotation temp : annotation) {
                if (temp instanceof XSS) {
                    flag = true;
                }
            }
            if (flag) {
                System.out.println("before args[" + i + "]=" + args[i]);
                args[i] = "aspectjs value";
                System.out.println("after args[" + i + "]=" + args[i]);
            }
        }
        joinpoint.proceed(args);
    }

}
