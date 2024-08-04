package com.yc.myproxy;

import java.lang.reflect.Method;

/**
 * 回调方法
 */
public interface YcInvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
