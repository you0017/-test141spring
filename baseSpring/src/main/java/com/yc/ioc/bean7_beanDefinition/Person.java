package com.yc.ioc.bean7_beanDefinition;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
//@Scope(value = "prototype")
//@Lazy//懒加载
public class Person implements InitializingBean, DisposableBean {
    public Person() {
        System.out.println("Person的构造方法");
    }
    @PostConstruct
    public void init(){
        System.out.println("Person的init方法");
    }
    @PreDestroy
    public void preDestroy(){
        System.out.println("Person的destroy方法");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Person的afterPropertiesSet方法");
    }
    @Override
    public void destroy() throws Exception {
        System.out.println("destroy()");
    }
}
