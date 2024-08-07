package com.yc.dao;

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
@ContextConfiguration(classes = {AppDataSourceConfig.class})
@Log4j
public class AccountDaoImplTest {
    @Autowired
    private AccountDao accountDao;


    @Test
    public void insert() {
        int accountid = accountDao.insert(1000);
        log.info("accountid:" + accountid);
        Assert.assertTrue(accountid > 0);
    }

    @Test
    public void update() {
        accountDao.update(1, 1000);
    }

    @Test
    public void delete() {
        accountDao.delete(1);
    }

    @Test
    public void findCount() {
        int count = accountDao.findCount();
        log.info("count:" + count);
    }

    @Test
    public void findAll() {
        accountDao.findAll().forEach(account -> {
            log.info(account);
        });
    }

    @Test
    public void findById() {
        Account account = accountDao.findById(2);
        log.info(account);
    }
}