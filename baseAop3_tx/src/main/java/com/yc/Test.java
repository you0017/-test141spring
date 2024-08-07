package com.yc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppDataSourceConfig.class);
        JdbcTemplate jdbcTemplate = ac.getBean("jdbcTemplate", JdbcTemplate.class);
//        DruidDataSource bean = (DruidDataSource) ac.getBean("dataSource");
//        String sql = "select * from accounts ";
//        List<Account> accountList = new ArrayList<>();
//        try (
//                DruidPooledConnection connection = bean.getConnection();
//                PreparedStatement statement = connection.prepareStatement(sql);
//                ResultSet resultSet = statement.executeQuery()
//        ) {
//            while (resultSet.next()) {
//                Account user = new Account();
//                user.setAccountid(resultSet.getInt("accountid"));
//                user.setBalance(resultSet.getDouble("balance"));
//                // Set other properties based on the columns in your table
//                accountList.add(user);
//            }
//
//
//            for (Account account : accountList) {
//                System.out.println(account.getAccountid() + " " + account.getBalance());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Account user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Account.class), 2);
//        System.out.println(user.getAccountid());
    }
}
