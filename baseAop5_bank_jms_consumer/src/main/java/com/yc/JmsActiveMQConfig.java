package com.yc;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms //在 Spring 应用程序中启用 JMS（Java Message Service）相关的功能和配置
@PropertySource(value = "classpath:jms.properties")
public class JmsActiveMQConfig {

    @Value("${brokerURL}")
    private String brokerUrl;

    // 配置连接工厂
    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);// ActiveMQ 代理地址
        return  connectionFactory;
    }

    // 配置 JmsTemplate jms模板：消息发送 消息接收  消息转换 连接和会话管理
    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate =  new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
//        jmsTemplate.setPubSubDomain(true); // 默认为 点对点（Queue）模式  设置为true表示发布/订阅（Topic）模式
        return jmsTemplate;
    }

    // 配置消息监听器容器工厂
    /*@Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
//        factory.setPubSubDomain(true); // 默认为点对点（Queue）模式  设置为true表示发布/订阅（Topic）模式
        return factory;
    }*/
}
