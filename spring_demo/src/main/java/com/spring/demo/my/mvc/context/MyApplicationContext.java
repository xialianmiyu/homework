package com.spring.demo.my.mvc.context;

import com.spring.demo.my.mvc.beans.MyBeanWrapper;
import com.spring.demo.my.mvc.beans.config.MyBeanDefinition;
import com.spring.demo.my.mvc.beans.support.MyBeanDefinitionReader;
import com.spring.demo.my.mvc.beans.support.MyDefaultListableBeanFactory;
import com.spring.demo.my.mvc.core.MyBeanFactory;

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

    private void doRegisterBeanDefinition(List<MyBeanDefinition> definitions) {


    }

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
    private void getBean(String name) {
    }
}
