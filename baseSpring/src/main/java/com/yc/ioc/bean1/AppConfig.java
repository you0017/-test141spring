package com.yc.ioc.bean1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//这是一个配置类，作用相当于beans.xml
//@ComponentScan({"com.yc.bean1"})
public class AppConfig {
    @Autowired
    private ApplicationContext ac;

    //<bean id="addr" class="com.yc.bean1.Address">
    @Bean
    public Address addr()
    {
        return new Address("北京","朝阳区","朝阳公园");
    }

    //@Bean
    public Student s1(Address addr) {
        System.out.println(ac);
        return new Student("张三",1,addr);
    }
}
