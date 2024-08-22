package com.yc.ioc.bean5_ioc_import;


import com.yc.ioc.bean5_ioc_import.other.Apple;
import com.yc.ioc.bean5_ioc_import.other.Banana;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({Banana.class, Apple.class, FruitImportSelector.class})
public class AppConfig_2 {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig_2.class);
        AppConfig_2 a = (AppConfig_2) ac.getBean("appConfig_2");
        System.out.println(a);
    }
}
