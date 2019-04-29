package com.spring.demo.my.mvc.aop.intercept;

import com.spring.demo.my.mvc.aop.aspect.MyJoinPoint;
import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;

import javax.transaction.TransactionRequiredException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyMethodInvocation implements MyJoinPoint {
    private Object proxy;
    private Method method;
    private Object target;
    private Object[] arguments;
    private List<Object> interceptorsAndDynamicMethodMatchers;
    private Class<?> targetClass;
    private Map<String, Object> userAttributes;

    //定义一个索引，从-1开始，来记录当前连接器执行的位置
    private int currentInterceptorIndes = -1;

    public MyMethodInvocation(Object proxy, Method method, Object target, Object[] arguments, List<Object> interceptorsAndDynamicMethodMatchers, Class<?> targetClass) {
        this.proxy = proxy;
        this.method = method;
        this.target = target;
        this.arguments = arguments;
        this.interceptorsAndDynamicMethodMatchers = interceptorsAndDynamicMethodMatchers;
        this.targetClass = targetClass;
    }

    public Object proceed() throws Throwable{
        //如果Interceptor执行完了，则执行joinPoint
        if(this.currentInterceptorIndes==this.interceptorsAndDynamicMethodMatchers.size()-1){
            return this.method.invoke(this.target,this.arguments);
        }
        Object interceptorInterceptionAdvice=this.interceptorsAndDynamicMethodMatchers.get(++this.currentInterceptorIndes);
        if(interceptorInterceptionAdvice instanceof MyMethodInvocation){
            MyMethodInterceptor mi= (MyMethodInterceptor) interceptorInterceptionAdvice;
            return  mi.invoke(this);
        }else{
            return proceed();
        }
    }

    @Override
    public Object getThis() {
        return this.target;
    }

    @Override
    public Object[] getArguments() {
        return new Object[0];
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public void setUserAttribute(String key, Object value) {
        if(value!=null){
            if(this.userAttributes==null){
                this.userAttributes=new HashMap<>();
            }
            this.userAttributes.put(key,value);
        }else{
            if(this.userAttributes!=null){
                this.userAttributes.remove(key);
            }
        }
    }

    @Override
    public Object getUserAttribute(String key) {
        return (this.userAttributes!=null?this.userAttributes.get(key):null);
    }
}
