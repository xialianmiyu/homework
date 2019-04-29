package com.spring.demo.my.mvc.test.aspect;

import com.spring.demo.my.mvc.aop.aspect.MyJoinPoint;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogAspect {


    public void before(MyJoinPoint joinPoint){
        log.info("before");
    }
    public void after(MyJoinPoint joinPoint){
        log.info("after");
    }
    public void afterThrowing(MyJoinPoint joinPoint,Throwable ex){
        log.info("afterThrowing");
    }
}
