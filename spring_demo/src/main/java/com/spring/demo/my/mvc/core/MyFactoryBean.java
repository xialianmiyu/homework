package com.spring.demo.my.mvc.core;

public interface MyFactoryBean {

    Object getBean(String beanName) throws Exception;
    Object getBean(Class<?> beanClass) throws Exception;
}
