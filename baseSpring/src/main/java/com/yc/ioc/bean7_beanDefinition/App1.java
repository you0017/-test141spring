package com.yc.ioc.bean7_beanDefinition;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class App1 {
    public static void main(String[] args) {
        //只创建容器的化，在prototype下，示例不会创建
        ApplicationContext ac = new AnnotationConfigApplicationContext(App1.class);
        Person p1 = ac.getBean(Person.class);
        //Person p2 = ac.getBean(Person.class);
        ((AnnotationConfigApplicationContext)ac).close();
    }
}
