package com.spring.demo.my.mvc.beans.support;

import com.spring.demo.my.mvc.beans.config.MyBeanDefinition;
import com.spring.demo.my.mvc.context.support.MyAbstractApplicationContext;
import com.spring.demo.my.mvc.core.MyBeanFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class MyDefaultListableBeanFactory extends MyAbstractApplicationContext implements MyBeanFactory {
    //存储注册信息的BeanDefinition,伪IOC容器
    protected final Map<String, MyBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
}
