package com.yc.dao;


import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Repository;
import com.yc.bean.Account;

import java.sql.PreparedStatement;
import java.util.List;

@Repository//这是一个Dao持久层类，spring提供了自动化异常类型 类型转换功能  将SQLException异常转为RuntimeException异常
@Log4j
@ManagedResource(objectName = "com.dao:name=AccountDaoImpl")
public class AccountDaoImpl implements AccountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*@Autowired
    public void init(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }*/

    @Override
    @ManagedOperation
    @ManagedOperationParameter(name = "money", description = "账户金额")
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
    @ManagedOperation
    @ManagedOperationParameter(name = "accountid", description = "账户id")
    @ManagedOperationParameter(name = "money", description = "账户金额")
    public void update(int accountid, double money) {
        String sql = "update accounts set balance = ? where accountid = ?";
        System.out.println("3306");
        jdbcTemplate.update(sql,money,accountid);
    }

    @Override
    public void delete(int accountid) {
        String sql = "delete from accounts where accountid = ?";
        jdbcTemplate.update(sql,accountid);
    }

    @Override
    @ManagedAttribute
    public int findCount() {
        log.debug("3306");
        System.out.println("3306");
        return jdbcTemplate.queryForObject("select count(*) from accounts",Integer.class);
    }

    @Override
    public List<Account> findAll() {
        String sql = "select * from accounts";
        List<Account> list = this.jdbcTemplate.query(sql,(rs, rowNum)->{
            Account account = new Account();
            account.setAccountid(rs.getInt(1));
            account.setBalance(rs.getDouble(2));
            account.setEmail(rs.getString(3));
            return account;
        });
        return list;
    }

    @Override
    public Account findById(int accountid) {
        log.debug("查id");
        String sql = "select * from accounts where accountid = ?";
        Account a = this.jdbcTemplate.queryForObject(sql,(rs,rowNum)->{
            Account account = new Account();
            account.setAccountid(rs.getInt(1));
            account.setBalance(rs.getDouble(2));
            account.setEmail(rs.getString(3));
            return account;
        },accountid);
        if (a==null){
            throw new RuntimeException("账户不存在");//TODO : Dao层的异常会被spring转换为spring的异常DataAccessException
        }
        return a;
    }
}
