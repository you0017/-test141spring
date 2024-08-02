package com.yc.project2.org.springframework.annotation;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@YcComponent
public @interface YcController {
    String value() default "";
}
