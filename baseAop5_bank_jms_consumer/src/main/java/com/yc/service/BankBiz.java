package com.yc.service;


import com.yc.bean.Account;

public interface BankBiz {
    // 添加Account账号
    public Account openAccount(double money);

    // 修改Account账号money
    public Account deposit( int accountid, double money);

    // 取款
    public Account withdraw( int accountid, double money);

    // 转账
    public Account transfer(int accountid, double money,int toAccountId);


    // 根据id查询Account账号信息
    public Account findAccount( int accountid);

    Account email(int accountId);
}
