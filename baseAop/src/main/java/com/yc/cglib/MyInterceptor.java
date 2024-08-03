package com.yc.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyInterceptor implements MethodInterceptor {
    private Object target;
    public MyInterceptor(Object target)
    {
        this.target = target;
    }

    public Object createProxy() {
        //用cglib创建代理对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());//设置父类，而不是接口，作为代理对象的生成模板
        enhancer.setCallback(this);
        return enhancer.create();
    }


    private void checkRights() {
        System.out.println("检查权限");
    }
    private void recordLog() {
        System.out.println("记录日志");
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
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
}
