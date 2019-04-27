package com.spring.demo.my.mvc.aop;

public interface MyAopProxy {
    Object getProxy();
    Object getProxy(ClassLoader classLoader);
}
