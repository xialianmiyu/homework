package com.spring.demo.my.mvc.servlet;

import java.io.File;
import java.util.Locale;

public class MyViewResolver {
    private final String DEFAULT_TEMPLATE_SUFFX = ".html";
    private File templateRootDir;

    public MyViewResolver(String path) {
        this.templateRootDir = new File(this.getClass().getClassLoader().getResource(path).getFile());

    }
    public MyView resolveViewName(String viewName, Locale locale)throws Exception{
        if(null==viewName||"".equals(viewName.trim())){
            return  null;
        }
        viewName=viewName.endsWith(DEFAULT_TEMPLATE_SUFFX)?viewName:viewName+DEFAULT_TEMPLATE_SUFFX;
        File templateFile=new File(templateRootDir.getPath()+"/"+viewName.replaceAll("/+","/"));
        return new MyView(templateFile);
    }
}










