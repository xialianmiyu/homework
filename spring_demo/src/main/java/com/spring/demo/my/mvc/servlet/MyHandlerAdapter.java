package com.spring.demo.my.mvc.servlet;

import com.spring.demo.my.mvc.annotation.MyRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MyHandlerAdapter {
    public boolean supports(Object handler){
        return (handler instanceof MyHandlerMapping);
    }

    MyModelAndView handle(HttpServletRequest req, HttpServletResponse resp,Object handler) throws Exception{
        MyHandlerMapping handlerMapping= (MyHandlerMapping) handler;
        Map<String,Integer> paramIndexMapping=new HashMap<>();
        Annotation[][] pa=handlerMapping.getMethod().getParameterAnnotations();
        for (int i = 0; i < pa.length ; i ++) {
            for(Annotation a : pa[i]){
                if(a instanceof MyRequestParam){
                    String paramName = ((MyRequestParam) a).value();
                    if(!"".equals(paramName.trim())){
                        paramIndexMapping.put(paramName, i);
                    }
                }
            }
        }
        Class<?> [] paramsTypes = handlerMapping.getMethod().getParameterTypes();
        for (int i = 0; i < paramsTypes.length ; i ++) {
            Class<?> type = paramsTypes[i];
            if(type == HttpServletRequest.class ||
                    type == HttpServletResponse.class){
                paramIndexMapping.put(type.getName(),i);
            }
        }
        Map<String,String[]> params = req.getParameterMap();
        Object [] paramValues = new Object[paramsTypes.length];

        for (Map.Entry<String, String[]> parm : params.entrySet()) {
            String value = Arrays.toString(parm.getValue()).replaceAll("\\[|\\]","")
                    .replaceAll("\\s",",");

            if(!paramIndexMapping.containsKey(parm.getKey())){continue;}

            int index = paramIndexMapping.get(parm.getKey());
            paramValues[index] = caseStringValue(value,paramsTypes[index]);
        }

        if(paramIndexMapping.containsKey(HttpServletRequest.class.getName())) {
            int reqIndex = paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = req;
        }

        if(paramIndexMapping.containsKey(HttpServletResponse.class.getName())) {
            int respIndex = paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = resp;
        }

        Object result = handlerMapping.getMethod().invoke(handlerMapping.getController(),paramValues);
        if(result == null || result instanceof Void){ return null; }

        boolean isModelAndView = handlerMapping.getMethod().getReturnType() == MyModelAndView.class;
        if(isModelAndView){
            return (MyModelAndView) result;
        }
        return null;
    }



    private Object caseStringValue(String value, Class<?> paramsType) {
        try {
           Constructor cons= paramsType.getConstructor(value.getClass());
           return cons.newInstance(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}
