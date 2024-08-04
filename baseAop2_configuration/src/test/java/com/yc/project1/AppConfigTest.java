package com.yc.project1;

import com.alibaba.druid.pool.DruidDataSource;
import junit.framework.TestCase;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@ExtendWith(SpringExtension.class)
//jupiter(junit 5)整合spring的方案


//导入的是junit4.jar  =>  用junit的运行器  SpringJUnit4ClassRunner
//以下两个注解相当于初始化spring 容器 => 支持IOC，di
//junit已经被spring托管
//设置类运行器
@RunWith(SpringJUnit4ClassRunner.class)
//设置Spring环境对应的配置类
@ContextConfiguration(classes = {AppConfig.class})
@Log4j
public class AppConfigTest extends TestCase{

    @Autowired
    private DruidDataSource ds;

    @Test
    public void test() {
        log.debug(ds);
        //System.out.println(ds);
    }
}