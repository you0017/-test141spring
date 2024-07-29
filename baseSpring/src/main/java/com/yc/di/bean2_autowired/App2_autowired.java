package com.yc.di.bean2_autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class App2_autowired {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(App2_autowired.class);
        OrderBiz bean = ac.getBean(OrderBiz.class);
        bean.makeOrder();
    }
}
