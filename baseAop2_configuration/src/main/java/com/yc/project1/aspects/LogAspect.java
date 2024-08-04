package com.yc.project1.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@Aspect
public class LogAspect {

    @Pointcut("execution(* com.yc.project1.service.*.update*(..))")
    public void a(){}
    @Pointcut("execution(* com.yc.project1.service.*.find*(..)) || execution(* com.yc.project1.service.*.show*(..))")
    public void b(){}
    @Pointcut("a() || b()")
    public void c(){}

    @Before("b()")
    public void doAccessCheck(){
        System.out.println("权限验证");
    }

    @After("c()")
    public void doLog(){
        System.out.println("日志记录");
    }

    @AfterReturning(value = "a()",returning = "o")
    public void doAfterReturning(Object o){
        System.out.println("返回通知:"+o.toString());
    }

    @AfterThrowing(value = "c()",throwing = "e")
    public void doAfterThrowing(Exception e){
        System.out.println("异常通知:"+e.getMessage());
    }

    @Around("a()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕通知:前置");
        long start = System.currentTimeMillis();
        Object o = pjp.proceed();
        System.out.println("环绕通知:后置");
        System.out.println("耗时:"+(System.currentTimeMillis()-start));
        return o;
    }
}
