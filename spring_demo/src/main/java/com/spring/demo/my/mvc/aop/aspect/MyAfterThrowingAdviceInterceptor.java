package com.spring.demo.my.mvc.aop.aspect;

import com.spring.demo.my.mvc.aop.intercept.MyMethodInterceptor;
import com.spring.demo.my.mvc.aop.intercept.MyMethodInvocation;

import java.lang.reflect.Method;

public class MyAfterThrowingAdviceInterceptor extends MyAbstraceAspectAdvice implements MyAdvice, MyMethodInterceptor {
    private String throwingName;

    public MyAfterThrowingAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(MyMethodInvocation invocation) throws Throwable {
       try{
           return invocation.proceed();
       }catch (Throwable e){
           invokeAdviceMethod(invocation,null,e.getCause());
           throw e;
       }

    }
    public void setThrowName(String throwName){
        this.throwingName=throwName;
    }
}
