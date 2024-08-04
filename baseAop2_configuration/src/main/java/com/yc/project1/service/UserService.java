package com.yc.project1.service;

import com.yc.project1.bean.User;


public interface UserService {

    public void showAll();
    public User findById(String id);
    public int update(User user);
}
