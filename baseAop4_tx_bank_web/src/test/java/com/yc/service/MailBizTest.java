package com.yc.service;


import com.yc.AppDataSourceConfig;
import com.yc.EmailConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes =  {AppDataSourceConfig.class, EmailConfig.class})
public class MailBizTest {

    @Autowired
    private MailBiz mailBiz;
    @Test
    public void sendMail() {
        mailBiz.sendMail("2092425375@qq.com", "测试邮件", "测试邮件内容");
    }
}