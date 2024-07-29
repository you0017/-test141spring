package com.yc.ioc.bean5_ioc_import;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App1_import {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig_1.class);
        //获取容器中所有的bean
        String[] names = ac.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
        Object bean = ac.getBean("com.yc.bean5_ioc_import.other.Apple");
        System.out.println(bean);
    }
}
