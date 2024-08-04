package com.yc.project1.dao;

import com.yc.project1.bean.User;

import java.util.List;

public interface UserDao {
    public List<User> findAll();
    public User findById(String id);
    public void update(User user);
}
