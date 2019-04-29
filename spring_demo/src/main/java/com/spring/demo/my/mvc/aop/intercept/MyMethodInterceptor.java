package com.spring.demo.my.mvc.aop.intercept;

public interface MyMethodInterceptor {
    Object invoke(MyMethodInvocation invocation) throws Throwable;
}
