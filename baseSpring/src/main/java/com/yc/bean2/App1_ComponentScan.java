package com.yc.bean2;

import com.yc.bean2.son.Apple;
import com.yc.bean3.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 因为在某个包中存在一些用@Component,@Service,@Repository,@Controller注解的类，
 * spring想托管这些类,所以会扫描这些包,并把这些类找出来,交由spring管理
 */
public class App1_ComponentScan {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        Apple apple = ac.getBean(Apple.class);
        System.out.println(apple);

    }
}
