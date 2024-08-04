package com.yc.myproxy;

import com.yc.jdkproxy.CheckRightsInvocationHandler;

import java.lang.reflect.Method;


public class Test {
    public static void main(String[] args) {
        YcJdkProxy ycJdkProxy = new YcJdkProxy();
        OrderBizImpl orderBiz = new OrderBizImpl();
        //CheckRightsInvocationHandler handler = new CheckRightsInvocationHandler(orderBiz);
        OrderBiz o = (OrderBiz) ycJdkProxy.newProxyInstance(new YcClassLoader(), orderBiz.getClass().getInterfaces(), new YcInvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("3306");
                Object o = method.invoke(orderBiz, args);
                return o;
            }
        });
        o.saveOrder(1);
    }
}