package lession2.mini.spring;

import lession2.mini.spring.annotation.MyAutowired;
import lession2.mini.spring.annotation.MyController;
import lession2.mini.spring.annotation.MyRequestMapping;
import lession2.mini.spring.annotation.MyService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyDispatcherService extends javax.servlet.http.HttpServlet {

    Properties configContext = new Properties();
    List<String> classNameList = new ArrayList();
    private Map<String,Object> ioc = new HashMap<String,Object>();
    private List<Handler> handlerMapping = new ArrayList<Handler>();
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            doDispatch(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Handler handler=getHandler(request);
        if(handler == null){

            response.getWriter().write("404");
            return;
        }

        Class<?> [] paramTypes = handler.getMethod().getParameterTypes();
        Object [] paramValues = new Object[paramTypes.length];


        Map<String,String[]> params = request.getParameterMap();
        for (Map.Entry<String, String[]> param : params.entrySet()) {
            String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll(",\\s", ",");

            //如果找到匹配的对象，则开始填充参数值
            if(!handler.getParamIndexMapping().containsKey(param.getKey())){continue;}
            int index = handler.getParamIndexMapping().get(param.getKey());
            paramValues[index] = paramTypes[index].getConstructor(value.getClass()).newInstance(value);
        }


        //设置方法中的request和response对象
        int reqIndex = handler.getParamIndexMapping().get(HttpServletRequest.class.getName());
        paramValues[reqIndex] = request;
        int respIndex = handler.getParamIndexMapping().get(HttpServletResponse.class.getName());
        paramValues[respIndex] = response;

        handler.getMethod().invoke(handler.getController(), paramValues);

    }

    private Handler getHandler(HttpServletRequest request) throws Exception{
        if(handlerMapping.isEmpty()){ return null; }

        String url = request.getRequestURI();
        String contextPath = request.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");

        for (Handler handler : handlerMapping) {
            try{
                Matcher matcher = handler.getPattern().matcher(url);

                if(!matcher.matches()){ continue; }

                return handler;
            }catch(Exception e){
                throw e;
            }
        }
        return null;
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        //1、加载配置
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //2、扫描包下所有配置
        doScanPackage(configContext.getProperty("scanPackage"));

        //3、初始化类实例
        doInstance();
        
        //4、注入
        doAutowired();

        //构造handlerMapping
        initHandlerMapping();
    }

    private void initHandlerMapping() {
        if(ioc.isEmpty()){ return; }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
           Class<?> clazz=entry.getValue().getClass();
           if(!clazz.isAnnotationPresent(MyController.class)){continue;}

           String url="";
           if(clazz.isAnnotationPresent(MyRequestMapping.class)){
               MyRequestMapping requestMapping =clazz.getAnnotation(MyRequestMapping.class);
               url=requestMapping.value();
           }
            Method[] methods=clazz.getMethods();
           for(Method method:methods){
               if(!method.isAnnotationPresent(MyRequestMapping.class)){continue;}

               MyRequestMapping requestMapping=method.getAnnotation(MyRequestMapping.class);
               String regex = ("/" + url + requestMapping.value()).replaceAll("/+", "/");
               Pattern pattern = Pattern.compile(regex);
               handlerMapping.add(new Handler(entry.getValue(),method,pattern));
               System.out.println("mapping " + regex + "," + method);


           }
        }

    }

    private void doAutowired() {

        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(MyAutowired.class)) {
                    return;
                }

                MyAutowired autowired = field.getAnnotation(MyAutowired.class);
                String beanName = autowired.value().trim();
                if ("".equals(beanName)) {
                    beanName = field.getType().getName();
                }
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(), ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doInstance() {
        if( classNameList.size()==0){return;}

            try {
                for(String className:classNameList){
                Class<?> clazz=Class.forName(className);
                    if(clazz.isAnnotationPresent(MyController.class)){
                        String beanName=clazz.getSimpleName();
                        beanName=beanName.substring(0,1).toLowerCase()+beanName.substring(1,beanName.length());
                        ioc.put(beanName,clazz.newInstance());
                    }else if (clazz.isAnnotationPresent(MyService.class)){
                        MyService service=clazz.getAnnotation(MyService.class);
                        String beanName=service.value();
                        if(!"".equals(beanName.trim())){
                            ioc.put(beanName,clazz.newInstance());
                            continue;
                        }
                        Class<?>[] interfaces=clazz.getInterfaces();
                        for(Class<?> i:interfaces){
                            ioc.put(i.getName(),clazz.newInstance());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    private void doScanPackage(String packageName) {

        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));
        File directory = new File(url.getFile());
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    doScanPackage(packageName+"."+file.getName());
                }else{
                    classNameList.add(packageName+"."+file.getName().replace(".class","").trim());
                }
            }
        }
    }

    private void doLoadConfig(String initParameter) {

        InputStream is = null;

        try {

            is = this.getClass().getClassLoader().getResourceAsStream(initParameter);
            configContext.load(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
