package com.yc.service;

import com.google.gson.Gson;
import com.yc.bean.MessageBean;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Log4j
public class JmsMessageConsumer {

    @Autowired
    private MailBiz mailBiz;
    @Autowired
    private VelocityTemplateBizImpl velocityTemplateBiz;

    @JmsListener(destination = "bankMessages")//监听myQueue消息队列
    public void receiverMessage(String message){
        System.out.println("接收到消息：" + message);
        Gson gson = new Gson();
        MessageBean mb = gson.fromJson(message, MessageBean.class);
        //产生要发送的邮件内容
        String context = velocityTemplateBiz.genEmailContent(mb.getOpType(),mb.getAccount(),mb.getMoney(),mb.getToAccountid());
        mailBiz.sendMail(mb.getAccount().getEmail(),"账户变动通知",context);
    }
}
