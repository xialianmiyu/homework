package lession2.mini.spring;

import lession2.mini.spring.annotation.MyRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Handler {


    private Object controller;
    private Method method;
    private Pattern pattern;
    private Map<String,Integer> paramIndexMapping;

    public Map<String, Integer> getParamIndexMapping() {
        return paramIndexMapping;
    }

    public void setParamIndexMapping(Map<String, Integer> paramIndexMapping) {
        this.paramIndexMapping = paramIndexMapping;
    }

    public Handler(Object controller, Method method, Pattern pattern) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
        paramIndexMapping=new HashMap<String, Integer>();
        putParamIndexMapping(method);
    }

    private void putParamIndexMapping(Method method) {
        Annotation[][] ans=method.getParameterAnnotations();
        for (int i = 0; i < ans.length; i++) {
            for (Annotation a:ans[i]) {
                if(a instanceof MyRequestParam){
                    String paramName=((MyRequestParam) a).value();
                    if(!"".equals(paramName.trim())){
                        paramIndexMapping.put(paramName,i);
                    }
                }
            }
        }
        Class<?>[] paramsType=method.getParameterTypes();
        for (int i = 0; i < paramsType.length; i++) {
            Class<?> type=paramsType[i];
            if(type== HttpServletRequest.class||type==HttpServletResponse.class){
                paramIndexMapping.put(type.getCanonicalName(),i);
            }
        }

    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

}
