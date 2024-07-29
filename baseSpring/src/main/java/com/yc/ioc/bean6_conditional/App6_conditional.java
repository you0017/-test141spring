package com.yc.ioc.bean6_conditional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class App6_conditional {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(App6_conditional.class);
        WindowsPath wp = (WindowsPath) ac.getBean("windowsPath");
        System.out.println(wp);
    }
}
