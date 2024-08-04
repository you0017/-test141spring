package com.yc.project1.dao;

import com.yc.project1.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private DataSource ds;


    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        try (Connection con = ds.getConnection();
             PreparedStatement ptsm = con.prepareStatement("select * from user");){
            ResultSet resultSet = ptsm.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setSex(resultSet.getString("sex"));
                user.setTel(resultSet.getString("tel"));
                user.setUsername(resultSet.getString("username"));
                list.add(user);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public User findById(String id) {
        List<User> list = new ArrayList<>();
        try (Connection con = ds.getConnection();
             PreparedStatement ptsm = con.prepareStatement("select * from user where username=?");){
            ptsm.setObject(1,id);
            ResultSet resultSet = ptsm.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setSex(resultSet.getString("sex"));
                user.setTel(resultSet.getString("tel"));
                user.setUsername(resultSet.getString("username"));
                list.add(user);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return list.get(0);
    }

    @Override
    public void update(User user) {
        List<User> list = new ArrayList<>();
        try (Connection con = ds.getConnection();
             PreparedStatement ptsm = con.prepareStatement("update user set password=? where username=?");){
            ptsm.setObject(1,user.getPassword());
            ptsm.setObject(2,user.getUsername());
            ptsm.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
