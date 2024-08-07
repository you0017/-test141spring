package com.yc.dao;



import com.yc.bean.Account;

import java.util.List;

public interface AccountDao {

    // 添加Account账号
    public int insert( double money);

    // 修改Account账号money
    public void update( int accountid, double money);

    // 删除Account账号
    public void delete( int accountid);

    // 查询Account账号数量
    public int findCount();

    // 查询所有Account账号信息
    public List<Account> findAll();

    // 根据id查询Account账号信息
    public Account findById( int accountid);




}
