package com.yc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class MailBiz {
    @Value("${smtp.from}")
    private String from;
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String to, String subject, String content) {
        //SimpleMailMessage mail = new SimpleMailMessage();//不包括附件
        MimeMessage mm = mailSender.createMimeMessage();//可以包括附件

        MimeMessageHelper message = new MimeMessageHelper(mm,"utf-8");
        try {
            message.setFrom(from);//谁发
            message.setTo(to);//发给谁
            message.setSubject(subject);//主题
            message.setText(content);//内容
            mailSender.send(mm);//发送
        }catch(Exception e){
            e.printStackTrace();
        }


    }
}
