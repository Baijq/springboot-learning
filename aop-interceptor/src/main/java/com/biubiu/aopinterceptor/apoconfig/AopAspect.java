package com.biubiu.aopinterceptor.apoconfig;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Aop切面处理
 *
 * @author biubiu
 */
@Aspect
@Component
public class AopAspect {
    /**
     * 切面
     * 所有的web接口的请求都会拦截
     */
    @Pointcut("execution(* com.biubiu.aopinterceptor.controller.*.*(..))")
    public void log() {
    }

    /**
     * 方法执行之前
     *
     * @param joinPoint
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("---------------Aop -> doBefore 处理完毕-------------");
    }

    /**
     * 方法执行之后
     *
     * @param
     */
    @After("log()")
    public void doAfter() {
        System.out.println("---------------Aop -> doAfter-------------");
    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result) {
        System.out.println("---------------Aop -> doAfterReturn-------------");
    }
}
