package com.yc.bean3;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.yc.bean2")//扫描这个包读取下面的类，只要有@Component,@Service,@Repository,@Controller,@Configuration等注解的类都会被扫描到
//并交给spring管理
public class AppConfig {
}
