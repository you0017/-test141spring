package com.yc.di.bean1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan

public class App1 {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(App1.class);
        Product bean = ac.getBean(Product.class);
        System.out.println(bean);
    }


}
