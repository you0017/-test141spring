package com.yc.dao;

import com.yc.bean.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Repository//这是一个Dao持久层类，spring提供了自动化异常类型 类型转换功能  将SQLException异常转为RuntimeException异常
public class AccountDaoImpl implements AccountDao{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int insert(double money) {
        String sql = "insert into accounts(balance) values(?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator pc;
        int result = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql,new String[]{"accountid"});
            ps.setString(1,money+"");
            return ps;
        },keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void update(int accountid, double money) {
        String sql = "update accounts set balance = balance + ? where accountid = ?";
        jdbcTemplate.update(sql,money,accountid);
    }

    @Override
    public void delete(int accountid) {
        String sql = "delete from accounts where accountid = ?";
        jdbcTemplate.update(sql,accountid);
    }

    @Override
    public int findCount() {
        return jdbcTemplate.queryForObject("select count(*) from accounts",Integer.class);
    }

    @Override
    public List<Account> findAll() {
        String sql = "select * from accounts";
        List<Account> list = this.jdbcTemplate.query(sql,(rs,rowNum)->{
            Account account = new Account();
            account.setAccountid(rs.getInt(1));
            account.setBalance(rs.getDouble(2));
            return account;
        });
        return list;
    }

    @Override
    public Account findById(int accountid) {
        String sql = "select * from accounts where accountid = ?";
        Account a = this.jdbcTemplate.queryForObject(sql,(rs,rowNum)->{
            Account account = new Account();
            account.setAccountid(rs.getInt(1));
            account.setBalance(rs.getDouble(2));
            return account;
        },accountid);
        return a;
    }
}
