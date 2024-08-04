package com.yc.project1;

import com.yc.project1.bean.User;
import com.yc.project1.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
@ComponentScan
public class App_main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(App_main.class);
        //DruidDataSource ds = (DruidDataSource) ac.getBean("druidDataSource");
        //System.out.println(ds);
        //DruidPooledConnection con = ds.getConnection();
        //System.out.println(con);

        UserService us = (UserService) ac.getBean("userServiceImpl");
        us.showAll();
        /*System.out.println("---------------");
        User zhangsan = us.findById("zhangsan");
        System.out.println(zhangsan);
        System.out.println("---------------");
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("3306");
        us.update(user);*/
    }
}
