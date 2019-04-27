package com.spring.demo.my.mvc.context;

import com.spring.demo.my.mvc.annotation.MyAutowired;
import com.spring.demo.my.mvc.annotation.MyController;
import com.spring.demo.my.mvc.annotation.MyService;
import com.spring.demo.my.mvc.beans.MyBeanWrapper;
import com.spring.demo.my.mvc.beans.config.MyBeanDefinition;
import com.spring.demo.my.mvc.beans.config.MyBeanPostProcessor;
import com.spring.demo.my.mvc.beans.support.MyBeanDefinitionReader;
import com.spring.demo.my.mvc.beans.support.MyDefaultListableBeanFactory;
import com.spring.demo.my.mvc.core.MyBeanFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyApplicationContext extends MyDefaultListableBeanFactory implements MyBeanFactory {

    private String[] configLocations;
    private MyBeanDefinitionReader reader;

    /**
     * 单例的IOC容器缓存
     */
    private Map<String,Object> singletonObjects=new ConcurrentHashMap<>();
    /**
     * 通用的ioc容器
     */
    private Map<String, MyBeanWrapper> factoryBeanInstanceCache=new ConcurrentHashMap<>();

    public MyApplicationContext(String... configLocations){
        this.configLocations=configLocations;
        try {
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void refresh() throws Exception {
        //1、定位，定位配置文件
        reader=new MyBeanDefinitionReader(this.configLocations);
        //2、加载，加载配置文件，扫描相关类，把他们封装成BeanDefinition

        List<MyBeanDefinition> beanDefinitions=reader.loadBeanDefinitions();
        
        //3、注册，把配置信息放到容器里面（伪ioc容器）
        doRegisterBeanDefinition(beanDefinitions);
        
        //4、初始化，把不是延时加载的类，提前初始化
        doAutowrited();
    }

    private void doRegisterBeanDefinition(List<MyBeanDefinition> definitions) throws Exception {
        for(MyBeanDefinition beanDefinition:definitions){
            if(super.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())){
                throw new Exception("the "+beanDefinition.getFactoryBeanName()+" is exist");
            }
            super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
        }

    }

    /**
     * 只处理非延时加载的情况
     */
    private void doAutowrited() {
        for (Map.Entry<String,MyBeanDefinition> entry:super.beanDefinitionMap.entrySet()){
            String beanName=entry.getKey();
            if(!entry.getValue().isLazyInit()){
                getBean(beanName);
            }
        }

        
    }

    /**
     * 依赖注入，从这里开始，通过读取BeanDefinition中的信息
     * 然后，通过反射机制创建一个实例并返回
     * Spring做法是，不会把最原始的对象放出去，会用一个BeanWrapper来进行一次包装
     *  装饰器模式：
     *  1、保留原来的OOP关系
     *  2、我需要对它进行扩展，增强（为了以后AOP打基础）
     */
    public Object getBean(String beanName) throws Exception {
        MyBeanDefinition myBeanDefinition =this.beanDefinitionMap.get(beanName);
        Object instance=null;
        MyBeanPostProcessor postProcessor=new MyBeanPostProcessor();
        postProcessor.postProcessAfterInitialization(instance,beanName);
        instance=instantiateBean(beanName,myBeanDefinition);
        //3.把这个对象分装到BeanWrapper中
        MyBeanWrapper beanWrapper=new MyBeanWrapper(instance);

        //4.把beanWrapper存到ioc容器里
        this.factoryBeanInstanceCache.put(beanName,beanWrapper);
        postProcessor.postProcessAfterInitialization(instance,beanName);
        //注入
        populateBean(beanName,new MyBeanDefinition(),beanWrapper);
        return this.factoryBeanInstanceCache.get(beanName).getWrapperInstance();
    }

    private Object instantiateBean(String beanName, MyBeanDefinition myBeanDefinition) {
        //1、拿到要实例化的对象的类名
        String className=myBeanDefinition.getBeanClassName();
        //2、反射实例化，得到一个对象
        Object instance=null;
        try {
        if(this.factoryBeanInstanceCache.containsKey(className)){
            instance=this.factoryBeanInstanceCache.get(className);
        }else{
            Class<?> clazz=Class.forName(className);
            instance=clazz.newInstance();
            MyAdvisedSupport config=instantionAopConfig(myBeanDefinition);
            config.setTargetClass(clzss);
            config.setTarget(instance);
            //符合pointcut的规则的话，创建代理对象
            if(config.poingCutMatch()){
                instance=createProxy(config).getProxy();
            }
            this.factoryBeanInstanceCache.put(className,instance);
            this.factoryBeanInstanceCache.put(myBeanDefinition.getFactoryBeanName(),instance);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MyAdvisedSupport instantionAopConfig(MyBeanDefinition myBeanDefinition) {
        MyAopConfig config=new MyAopConfig();
        config.

    }

    private void populateBean(String beanName, MyBeanDefinition myBeanDefinition, MyBeanWrapper beanWrapper) {
        Object instance=beanWrapper.getWrapperInstance();
        Class<?> clazz=beanWrapper.getWrappedClss();
        //判断只有加了注解的类，才执行依赖注入
        if(!(clazz.isAnnotationPresent(MyController.class)||clazz.isAnnotationPresent(MyService.class))){
            return;
        }

        //获取所有的fileds
        Field[] fileds=clazz.getDeclaredFields();
        for(Field field:fileds){
            if(!field.isAnnotationPresent(MyAutowired.class)){
                continue;
            }
            MyAutowired autowired=field.getAnnotation(MyAutowired.class);
            String autowiredBeanName=autowired.value().trim();
            if("".equals(autowiredBeanName)){
                autowiredBeanName=field.getType().getName();
            }
            field.setAccessible(true);
            if(this.factoryBeanInstanceCache.get(autowiredBeanName)==null){
                continue;
            }
            try {
                field.set(instance,this.factoryBeanInstanceCache.get(autowiredBeanName).getWrapperInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


    }

    public Object getBean(Class<?> beanClss) throws Exception {
        return getBean(beanClss.getName());
    }
}
