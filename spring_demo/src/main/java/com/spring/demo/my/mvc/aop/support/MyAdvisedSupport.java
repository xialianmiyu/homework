package com.spring.demo.my.mvc.aop.support;

import com.spring.demo.my.mvc.aop.aspect.MyAfterReturningAdviceInterceptor;
import com.spring.demo.my.mvc.aop.aspect.MyAfterThrowingAdviceInterceptor;
import com.spring.demo.my.mvc.aop.aspect.MyMethodBeforeAdviceInterceptor;
import com.spring.demo.my.mvc.aop.config.MyAopConfig;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyAdvisedSupport {
    private Class<?> targetClass;
    private Object target;
    private MyAopConfig config;
    private Pattern pointCutClassPattern;
    private transient Map<Method, List<Object>> methodCache;

    public MyAdvisedSupport(MyAopConfig config) {
        this.config = config;
    }


    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Object getTarget() {
        return target;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
    public boolean pointCutMatch() {
        return pointCutClassPattern.matcher(this.targetClass.toString()).matches();
    }

    public List<Object> getInterceptorAndDynamicInterceptionAdvice(Method method,Class<?> targetClass) throws Exception{
        List<Object> cached=methodCache.get(method);
        if(cached==null){
            Method m=targetClass.getMethod(method.getName(),method.getParameterTypes());
            cached=methodCache.get(m);

            //底层逻辑，对代理方法进行一个兼容处理
            this.methodCache.put(m,cached);
        }
        return cached;

    }
    private void parse(){
        String pointCut=config.getPoingCut().replace("\\.","\\\\.")
                .replace("\\\\.\\*",".*")
                .replace("\\(","\\\\(")
                .replace("\\)","\\\\)");

        String pointCutForClassRegex=pointCut.substring(0,pointCut.lastIndexOf("\\(")-4);
        pointCutClassPattern=Pattern.compile("class" +pointCutForClassRegex.substring(pointCutForClassRegex.lastIndexOf(" ")+1));


        try {
            methodCache=new HashMap<Method, List<Object>>();
            Pattern pattern=Pattern.compile(pointCut);
            Class aspectClass=Class.forName(this.config.getAspectClass());


            Map<String,Method> aspectMethods=new HashMap<>();
            for(Method m:aspectClass.getMethods()){
                aspectMethods.put(m.getName(),m);
            }
            for(Method m:this.targetClass.getMethods()){
                String methodString =m.toString();
                if(methodString.contains("throws")){
                    methodString=methodString.substring(0,methodString.lastIndexOf("throws")).trim();
                }
                Matcher matcher =pattern.matcher(methodString);
                if(matcher.matches()){
                    List<Object> advices=new LinkedList<>();
                    if(!(null==config.getAspectBefore()||"".equals(config.getAspectBefore()))){
                        advices.add(new MyMethodBeforeAdviceInterceptor(aspectMethods.get(config.getAspectBefore()),aspectClass.newInstance()));

                    }
                    if(!(null==config.getAspectAfter()||"".equals(config.getAspectAfter()))){
                        advices.add(new MyAfterReturningAdviceInterceptor(aspectMethods.get(config.getAspectAfter()),aspectClass.newInstance()));

                    }
                    if(!(null==config.getAspectAfterThrow()||"".equals(config.getAspectAfterThrow()))){
                        MyAfterThrowingAdviceInterceptor throwingAdvice=
                                new MyAfterThrowingAdviceInterceptor(
                                        aspectMethods.get(config.getAspectAfterThrow()),
                                        aspectClass.newInstance());
                        throwingAdvice.setThrowName(config.getAspectAfterThrowingName());
                        advices.add(throwingAdvice);
                    }
                    methodCache.put(m,advices);
                }

            }



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}














