package com.spring.demo.my.mvc.aop.aspect;

import java.lang.reflect.Method;

public interface MyJoinPoint {
    Object getThis();
    Object[] getArguments();
    Method getMethod();
    void setUserAttribute(String key,Object value);
    Object getUserAttribute(String key);
}
