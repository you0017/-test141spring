package com.yc.project1.service;

import com.yc.project1.bean.User;
import com.yc.project1.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    public void showAll(){
        /*if (true){
            throw new RuntimeException("132");
        }*/
        userDao.findAll().forEach(System.out::println);
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public int update(User user) {
        userDao.update(user);
        Random r = new Random();
        /*if (true){
            throw new RuntimeException("你猜");
        }*/
        return r.nextInt(100);
    }
}
