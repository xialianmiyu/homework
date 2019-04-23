package com.spring.demo.my.mvc.beans.config;

/**
 * 存储配置文件中的信息
 * 保存在内存中的配置
 */
public class MyBeanDefinition {

    private String beanClassName;
    private boolean lazyInit=false;
    private String factoryBeanName;


    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }
}
