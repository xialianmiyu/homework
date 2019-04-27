package com.spring.demo.my.mvc.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyJdkDynamicAopProxy implements MyAopProxy, InvocationHandler {

    private MyAdvisedSupport advised;
    @Override
    public Object getProxy() {
        return null;
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return null;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
