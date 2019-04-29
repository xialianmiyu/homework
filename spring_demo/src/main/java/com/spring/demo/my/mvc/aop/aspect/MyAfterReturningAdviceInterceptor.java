package com.spring.demo.my.mvc.aop.aspect;

import com.spring.demo.my.mvc.aop.intercept.MyMethodInterceptor;
import com.spring.demo.my.mvc.aop.intercept.MyMethodInvocation;

import java.lang.reflect.Method;

public class MyAfterReturningAdviceInterceptor extends MyAbstraceAspectAdvice implements MyAdvice, MyMethodInterceptor {
    private MyJoinPoint joinPoint;
    public MyAfterReturningAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(MyMethodInvocation invocation) throws Throwable {
        Object retVal=invocation.proceed();
        this.joinPoint=invocation;
        this.afterReturning(retVal,invocation.getMethod(),invocation.getArguments(),invocation.getThis());
        return retVal;
    }
    private void afterReturning(Object retVal,Method method,Object[] arguments,Object athis) throws Throwable{
        super.invokeAdviceMethod(this.joinPoint,retVal,null);
    }

}
