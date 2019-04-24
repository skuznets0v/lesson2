package com.firstlinesoftware.lesson2.annotations.custom;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {

    // method name
    String value() default "";

    boolean needCheckTime() default false;
}
