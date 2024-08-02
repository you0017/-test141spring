package com.yc.project2.yc;

import com.yc.project2.org.springframework.annotation.YcRepository;
import com.yc.project2.org.springframework.annotation.YcResource;

@YcRepository
public class UserDao {
    public void showAll() {
        System.out.println("showAll");
    }
}
