package com.yc.project1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public void showAll(){
        userDao.findAll().forEach(System.out::println);
    }
}
