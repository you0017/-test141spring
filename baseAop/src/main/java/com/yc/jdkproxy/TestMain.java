package com.yc.jdkproxy;

import java.lang.reflect.InvocationHandler;

public class TestMain {
    public static void main(String[] args) {
        //配置环境变量:将Proxy生成的代理类的字节码保存下来
        //System.setProperty("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");


        //目标类的对象
        OrderBiz target = new OrderBizImpl();

        //通过InvocationHandler创建代理类
        CheckRightsInvocationHandler handler = new CheckRightsInvocationHandler(target);
        OrderBiz proxy = (OrderBiz)handler.createProxy();   //先生成代理对象

        //main是客户端，就是调用端，调用目标类对象的方法，现在要调用代理类对象的方法
        //                                             ->调用目标类的方法
        proxy.findAllOrder();

        proxy.saveOrder(100);
    }
}
