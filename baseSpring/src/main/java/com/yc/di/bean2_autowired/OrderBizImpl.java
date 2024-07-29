package com.yc.di.bean2_autowired;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

@Service
@RequiredArgsConstructor
public class OrderBizImpl implements OrderBiz{
    //@Autowired
    //Qualifier("orderOceanDao")

    //@Resource(name = "orderOceanDao")  这个等于@Au + @Qu

    /*@Inject
    @Named("orderOceanDao") 这两个等于@Au + @In
    private OrderDao orderDao;*/
    private final OrderDao orderDao;
    @Override
    public void makeOrder() {
        System.out.println("8080");
        orderDao.addOrder();
    }
}
