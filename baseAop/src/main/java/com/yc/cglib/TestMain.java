package com.yc.cglib;

import com.yc.jdkproxy.OrderBiz;
import com.yc.jdkproxy.OrderBizImplOOP;
import net.sf.cglib.core.DebuggingClassWriter;

public class TestMain {
    public static void main(String[] args) {
        //jdk要11或8，17太高了module有变化，有保护机制了

        //字节码生成位置
        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\cglib_proxy");
        OrderBizImpl target = new OrderBizImpl();  //不面向接口
        MyInterceptor interceptor = new MyInterceptor(target);
        //创建代理对象
        OrderBizImpl proxy = (OrderBizImpl)interceptor.createProxy();
        proxy.saveOrder(100);
        proxy.findAllOrder();
    }
}
