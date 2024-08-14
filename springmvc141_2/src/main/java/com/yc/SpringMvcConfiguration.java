package com.yc;

import com.yc.Interceptors.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class SpringMvcConfiguration {

    //springmvc的配置支持两种:xml 和 java注解配置

    @Autowired
    private HandlerInterceptor[] interceptors;//可能有多个拦截器
    @Bean
    WebMvcConfigurer createWebMvcConfigurer(){
        //WebMvcConfigurer  springmvc的配置类，java代码来配置，它的每个方法都对应一个xml配置项目
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                for (HandlerInterceptor interceptor : interceptors) {
                    registry.addInterceptor(interceptor);//这个拦截器加到所有请求上
                }
            }
            //配置静态访问资源
            //相当于<mvc:resource mapping="/statics/**" location="classpath:/statics/"/>

            //相当于把resource下的statics目录复制到webappxia
            //相当于webapp下的静态页面了
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/statics/**")
                        .addResourceLocations("classpath:/statics/");
            }

            /**
             * DispatcherServlet破坏了Servlet的一个特性(根目录下的文件可以直接访问
             * DefaultServletHttpRequestHandler 是帮助
             */
            @Override
            public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
                //在XML中配置默认的Servlet处理器
                //<mvc:default-servlet-handler/>
                //指定默认的Servlet名称
                //<mvc:default-servlet-handler default-servlet-name="defaultServletName"/>
                configurer.enable();
                //
            }
        };
    }


    /**
     * 用于解析jsp和html等资源文件
     * @return
     */
    //只针对控制层,经由控制层跳转的页面会拼接这段路径访问
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }
}
