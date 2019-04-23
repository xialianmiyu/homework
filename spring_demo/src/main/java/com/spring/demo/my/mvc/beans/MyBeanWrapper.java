package com.spring.demo.my.mvc.beans;

public class MyBeanWrapper {

    private Object wrapperInstance;
    private Class<?> wrappedClss;

    public Object getWrapperInstance() {
        return wrapperInstance;
    }

    public void setWrapperInstance(Object wrapperInstance) {
        this.wrapperInstance = wrapperInstance;
    }

    public Class<?> getWrappedClss() {
        return wrappedClss;
    }

    public void setWrappedClss(Class<?> wrappedClss) {
        this.wrappedClss = wrappedClss;
    }
}
