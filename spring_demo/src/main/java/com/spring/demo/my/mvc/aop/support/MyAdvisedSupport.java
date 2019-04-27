package com.spring.demo.my.mvc.aop.support;

import com.spring.demo.my.mvc.aop.config.MyAopConfig;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MyAdvisedSupport {
    private Class<?> targetClass;
    private Object target;
    private MyAopConfig config;
    private Pattern pointCutClassPattern;
    private transient Map<Method, List<Object>> methodCache;

public MyAdvisedSupport(MyAopConfig config){
    this.config=config;
}


    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Object getTarget() {
        return target;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
