package com.yc.di.bean2_autowired;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class OrderOceanDao implements OrderDao{

    @Override
    public void addOrder() {

    }
}
