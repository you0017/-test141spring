package com.yc.service;

import com.yc.AppDataSourceConfig;
import com.yc.bean.Account;
import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppDataSourceConfig.class)
@Log4j
public class BankBizImplTest {
    @Autowired
    private BankBiz bankBiz;

    @Test
    public void openAccount() {
        Account a = bankBiz.openAccount(1000);
        log.info(a);
        Assert.assertNotNull(a);
    }

    @Test
    public void deposite() {
        Account a = bankBiz.deposite(2, 1000);
        log.info(a);
        Assert.assertNotNull(a);
    }

    @Test
    public void withdraw() {
        Account a = bankBiz.withdraw(2, 1000);
        log.info(a);
        Assert.assertNotNull(a);
    }

    @Test
    public void transfer() {
    }

    @Test
    public void findAccount() {
        Account a = bankBiz.findAccount(2);
        log.info(a);
        Assert.assertNotNull(a);
    }
}