package com.spring.demo.my.mvc.aop.aspect;

import com.spring.demo.my.mvc.aop.intercept.MyMethodInterceptor;
import com.spring.demo.my.mvc.aop.intercept.MyMethodInvocation;

import java.lang.reflect.Method;

public class MyMethodBeforeAdviceInterceptor extends MyAbstraceAspectAdvice implements MyAdvice, MyMethodInterceptor {

    private MyJoinPoint joinPoint;

    public MyMethodBeforeAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(MyMethodInvocation invocation) throws Throwable {
        this.joinPoint = invocation;
        before(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return invocation.proceed();
    }

    private void before(Method method, Object[] args, Object target) throws Throwable {
        super.invokeAdviceMethod(this.joinPoint, null, null);
    }
}
