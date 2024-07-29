package com.yc.di.bean2_autowired;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
public class OrderMysqlDao implements OrderDao{
    @Override
    public void addOrder() {
        System.out.println("3306");
    }
}
