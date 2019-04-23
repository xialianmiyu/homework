package com.spring.demo.my.mvc.annotation;

import java.lang.annotation.*;


@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestMapping {
	String value() default "";
}
