package com.van.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.persistence.Inheritance;

@Documented
@Target(ElementType.METHOD)
@Inheritance
@Retention(RetentionPolicy.RUNTIME)
public @interface LogMaster {
	String enable() default "NO";
	String level () default "NORMAL";
}
