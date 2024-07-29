package com.yc.ioc.bean1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ThreadPoolExecutor;

public class App4_annotation_threadpool {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig2.class);
        ThreadPoolExecutor tpe = (ThreadPoolExecutor) ac.getBean("tpe");
        tpe.execute( () -> {
            System.out.println("线程池执行任务");
        });
        tpe.shutdown();
    }
}
