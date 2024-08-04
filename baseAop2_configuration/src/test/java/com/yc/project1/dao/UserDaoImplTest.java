package com.yc.project1.dao;

import com.yc.project1.AppConfig;
import com.yc.project1.AppConfigTest;
import com.yc.project1.bean.User;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

//@ExtendWith(SpringExtension.class)
//jupiter(junit 5)整合spring的方案


//导入的是junit4.jar  =>  用junit的运行器  SpringJUnit4ClassRunner
//以下两个注解相当于初始化spring 容器 => 支持IOC，di
//junit已经被spring托管
//设置类运行器
@RunWith(SpringJUnit4ClassRunner.class)
//设置Spring环境对应的配置类
@ContextConfiguration(classes = {AppConfig.class})
public class UserDaoImplTest extends TestCase {
    @Autowired
    private UserDao userDao;

    @Autowired
    private Environment env;//spring启动时，会读取全部的环境变量

    @Test
    public void test() {
        System.out.println("");
    }


    @Test
    public void findAll() {
        userDao.findAll();
    }

    @Test
    public void findById() {
        User byId = userDao.findById("zhangsan");
        Assert.assertNotNull(byId);
        Assert.assertEquals("zhangsan",byId.getUsername());
    }

    @Test
    public void update() {
        User user = new User();
        user.setUsername("张三");
        user.setPassword("123456");
        userDao.update(user);
    }
}