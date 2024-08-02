package com.yc.project2.org.springframework.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@YcComponent
public @interface YcConfiguration {
}
