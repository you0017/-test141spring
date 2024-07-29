package com.yc.ioc.bean1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.*;

@Configuration  //这是一个配置类，作用相当于beans.xml
//@ComponentScan({ "com.yc.bean1" })
@PropertySource("classpath:pool.properties")
public class AppConfig2 {

    @Value("${corePoolSize}")
    private int corePoolSize;
    @Value("${maxPoolSize}")
    private int maxPoolSize;
    @Value("${keepAliveTime}")
    private long keepAliveTime;
    //@Value("${unit}")
    private TimeUnit unit = TimeUnit.SECONDS;

    @Bean //id="tpe" class = "ThreadPoolExecutor"
    public ThreadPoolExecutor tpe() {
        /*//核心线程池大小
        int corePoolSize = 3;*/
        //核心线程池最大线程数
        /*int maxPoolSize = 5;
        //线程池最大空闲时间
        long keepAliveTime = 10;*/
        //时间单位
        /*TimeUnit unit = TimeUnit.SECONDS;*/
        //阻塞队列，容量为2  最多允许放入两个空闲任务
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);//同时最大任务量为7   ->  超过7个任务，则拒绝执行

        ThreadPoolExecutor executor = null;
        //推荐的创建线程池的方式
        //不推荐使用现成的api创建线程池
        executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, unit, workQueue, Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        return executor;
    }
}
