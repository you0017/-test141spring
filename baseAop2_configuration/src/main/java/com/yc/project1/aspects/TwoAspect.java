package com.yc.project1.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TwoAspect {
    @Pointcut("execution(* com.yc.project1.service.*.show*(..))")
    public void a(){}

    @Around("a()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Two");
        System.out.println("前置通知");
        Object o = pjp.proceed();
        System.out.println("后置通知");
        return o;
    }
}
