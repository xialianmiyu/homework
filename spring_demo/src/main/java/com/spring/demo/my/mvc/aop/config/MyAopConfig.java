package com.spring.demo.my.mvc.aop.config;

import lombok.Data;

@Data
public class MyAopConfig {
    private String poingCut;
    private String aspectBefore;
    private String aspectAfter;
    private String aspectClass;
    private String aspectAfterThrow;
    private String aspectAfterThrowingName;
}
