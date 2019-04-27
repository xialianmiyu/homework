package com.spring.demo.my.mvc.beans;

public class MyBeanWrapper {

    private Object wrapperInstance;
    private Class<?> wrappedClss;

    public MyBeanWrapper(Object wrapperInstance) {
        this.wrapperInstance = wrapperInstance;
    }

    public Class<?> getWrappedClss() {
        return wrappedClss;
    }


    public Object getWrapperInstance() {
        return wrapperInstance;
    }
}
