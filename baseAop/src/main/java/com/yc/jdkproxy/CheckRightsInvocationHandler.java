package com.yc.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//回调接口
public class CheckRightsInvocationHandler implements InvocationHandler {
    private Object target;//目标对象的引用

    //目标类的引用的初始化
    public CheckRightsInvocationHandler(Object target) {
        this.target = target;
    }

    //对外提供一个方法用于创建一个代理类对象
    public Object createProxy() {
        //jdk中提供了一个Proxy.newProxyInstance方法来创建代理类对象
        Object proxy = Proxy.newProxyInstance(CheckRightsInvocationHandler.class.getClassLoader(), target.getClass().getInterfaces(), this);
        //this表明: 一个代理对象被调用时，由jvm自动会调用this指向的InvocationHandler的invoke方法
        return proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //1.什么时候调用增强
        if (method.getName().startsWith("save")||method.getName().startsWith("update")||method.getName().startsWith("show")){
            checkRights();
        }

        //2.调用目标类的目标方法
        Object returnValue = method.invoke(target, args);


        if (method.getName().startsWith("save")||method.getName().startsWith("update")||method.getName().startsWith("show")){
            recordLog();
        }


        return returnValue;
    }

    //这是一个增强的功能
    private void checkRights() {
        System.out.println("检查权限");
    }
    private void recordLog() {
        System.out.println("记录日志");
    }
}
