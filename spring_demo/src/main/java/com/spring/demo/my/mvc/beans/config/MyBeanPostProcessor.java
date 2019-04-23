package com.spring.demo.my.mvc.beans.config;

public class MyBeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean,String beanName) throws Exception{
        return bean;
    }
    public Object postProcessAfterInitialization(Object bean,String beanName) throws Exception{
        return bean;
    }
}
