package com.yc.project2.yc;

import com.yc.project2.org.springframework.annotation.YcResource;
import com.yc.project2.org.springframework.annotation.YcService;

@YcService
public class UserBiz {
    @YcResource("userDao")
    private UserDao userDao;

    public void showAll() {
        userDao.showAll();
    }
}
