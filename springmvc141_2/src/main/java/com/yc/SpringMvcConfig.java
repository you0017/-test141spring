package com.yc;

import com.yc.Interceptors.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class SpringMvcConfig /*extends WebMvcConfigurationSupport*/ {
    //拦截器

    /*@Autowired
    protected LogInterceptor paramsInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(paramsInterceptor).addPathPatterns("/chapter1");//这个拦截器加到所有请求上
    }*/


}
