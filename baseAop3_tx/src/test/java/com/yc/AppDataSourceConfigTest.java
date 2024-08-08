package com.yc;

import com.alibaba.druid.pool.DruidDataSource;
import com.yc.bean.OpType;
import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppDataSourceConfig.class})
@Log4j
public class AppDataSourceConfigTest {
    @Autowired
    private DruidDataSource dataSource;

    @Test
    public void test(){
        /*log.info(OpType.WITHDRAW);
        log.info(OpType.DEPOSITE);
        log.info(OpType.TRANSFER);
        log.info(OpType.valueOf("DEPOSITE"));
        log.info(OpType.values());*/
        log.info(OpType.WITHDRAW.getKey());
        log.info(OpType.WITHDRAW.getValue());
        log.info(OpType.WITHDRAW.getKey());
        log.info(OpType.WITHDRAW.getValue());
    }

    @Test
    public void druidDataSource() {
        log.info(dataSource);
        Assert.assertNotNull(dataSource);
    }
}