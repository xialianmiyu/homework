package com.spring.demo.my.mvc.beans.support;

import com.spring.demo.my.mvc.beans.config.MyBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 对配置文件进行查找、读取、解析
 */
public class MyBeanDefinitionReader {

    private List<String> registyBeanClasses = new ArrayList<String>();
    private Properties config = new Properties();

    private final String SCAN_PACKAGE = "scanPackage";

    public MyBeanDefinitionReader(String... locations) {

        for (String file : locations) {

            try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(file.replace("classpath:", ""))) {
                config.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }

            doScanner(config.getProperty(SCAN_PACKAGE));

        }
    }

    private void doScanner(String path) {
        URL url = this.getClass().getClassLoader().getResource("/" + path.replace("\\.", "/"));
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScanner(path + "." + file.getName());
            } else {
                if (file.getName().endsWith(".class")) {
                    String className = path + "." + file.getName().replace(".class", "");
                    registyBeanClasses.add(className);
                }
            }
        }

    }

    public Properties getConfig() {
        return this.config;
    }

    public List<MyBeanDefinition> loadBeanDefinitions() {
        List<MyBeanDefinition> list = new ArrayList<>();
        try {
            for (String className : registyBeanClasses) {

                Class<?> beanClass = Class.forName(className);
                if (beanClass.isInterface()) {
                    continue;
                }
                String lowerClass = toLower(beanClass.getSimpleName());

                MyBeanDefinition beanDefinition = doCreateBeanDefinition(lowerClass, beanClass.getName());
                list.add(beanDefinition);
                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> i : interfaces) {
                    list.add(doCreateBeanDefinition(i.getName(), beanClass.getName()));
                }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    private MyBeanDefinition doCreateBeanDefinition(String factoryBeanName, String beanClassName) {
        MyBeanDefinition beanDefinition = new MyBeanDefinition();
        beanDefinition.setBeanClassName(beanClassName);
        beanDefinition.setFactoryBeanName(factoryBeanName);
        return beanDefinition;
    }

    private String toLower(String name) {
        return name.substring(0, 1).toLowerCase() + name.substring(1, name.length());
    }
}
