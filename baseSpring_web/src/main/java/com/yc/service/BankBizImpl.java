package com.yc.service;


import com.yc.bean.Account;
import com.yc.bean.OpRecord;
import com.yc.bean.OpType;
import com.yc.dao.AccountDao;
import com.yc.dao.OpRecordDao;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        timeout = -1,
        readOnly = false,
        rollbackFor = {RuntimeException.class}
)
public class BankBizImpl implements BankBiz{
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private OpRecordDao opRecordDao;


    @Override
    public Account openAccount(double money) {
        //操作：开户
        int accountid = accountDao.insert(money);
        //操作2：流水记录
        OpRecord record = new OpRecord();
        record.setAccountid(accountid);
        record.setOpmoney(money);
        record.setOptype(OpType.DEPOSITE);
        opRecordDao.insertOpRecord(record);
        //操作3：构建返回值
        Account a = new Account();
        a.setAccountid(accountid);
        a.setBalance(money);

        log.info("开户"+accountid+",存入"+money);
        return a;
    }

    @Override
    public Account deposite(int accountid, double money) {
        Account a = null;
        try{
            a = this.accountDao.findById(accountid);
        }catch (Exception e){
            log.error("账户不存在:"+accountid);
            throw new RuntimeException("账户不存在:"+accountid+",无法完成存款操作");
        }
        a.setBalance(a.getBalance()+money);
        accountDao.update(accountid,a.getBalance());
        //操作2：流水记录
        OpRecord record = new OpRecord();
        record.setAccountid(accountid);
        record.setOpmoney(money);
        record.setOptype(OpType.DEPOSITE);
        opRecordDao.insertOpRecord(record);
        return a;

    }

    @Override
    //@Transactional
    public Account withdraw(int accountid, double money) {
        Account a = null;
        try{
            a = this.accountDao.findById(accountid);
        }catch (Exception e){
            log.error("账户不存在:"+accountid);
            throw new RuntimeException("账户不存在:"+accountid+",无法完成存款操作");
        }


        //操作2：流水记录
        OpRecord record = new OpRecord();
        record.setAccountid(accountid);
        record.setOpmoney(money);
        record.setOptype(OpType.WITHDRAW);
        opRecordDao.insertOpRecord(record);

        //TODO:要判断余额是否足够,利用数据库中的约束来完成金额的检查
        a.setBalance(a.getBalance()-money);
        accountDao.update(accountid,money);
        return a;
    }

    @Override
    public Account transfer(int accountid, double money, int toAccountId) {
        this.deposite(accountid,money);
        Account a = this.withdraw(toAccountId, money);
        return a;
    }

    @Override
    public Account findAccount(int accountid) {
        return this.accountDao.findById(accountid);
    }
}
