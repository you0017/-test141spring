package com.yc.di.bean1;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class A {
    @Bean
    public Product product() {
        return new Product();
    }
}
