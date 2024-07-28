package com.yc.bean4_ioc_factorybean;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class App_FactoryBean {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(App_FactoryBean.class);
        //FruitFactoryBean fruitFactoryBean = (FruitFactoryBean) ac.getBean("fruitFactoryBean");
        FruitFactoryBean fruitFactoryBean = (FruitFactoryBean) ac.getBean("&fruitFactoryBean");
        //加&就是取工厂，否则是取产品
        System.out.println(fruitFactoryBean);

        //获取spring容器中所有bean的名称
        String[] beanNames = ac.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

    @Bean
    public FruitFactoryBean fruitFactoryBean() {
        return new FruitFactoryBean();
    }
}
