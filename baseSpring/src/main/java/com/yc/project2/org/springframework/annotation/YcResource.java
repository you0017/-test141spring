package com.yc.project2.org.springframework.annotation;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, METHOD})
@Retention(RUNTIME)
public @interface YcResource {
    String value() default "";
}