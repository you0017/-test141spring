package com.yc.aspect;

import com.yc.bean.Account;
import com.yc.service.BankBiz;
import com.yc.service.MailBiz;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
@Order(1)
public class BankBizEmailAspect {

    @Pointcut("execution(* com.yc.service.BankBizImpl.deposit(..))")
    public void deposit() {
    }

    @Pointcut("execution(* com.yc.service.BankBizImpl.withdraw(..))")
    public void withdraw() {
    }

    @Pointcut("execution(* com.yc.service.BankBizImpl.transfer(..))")
    public void transfer() {
    }

    @Autowired
    private MailBiz mailBiz;

    @Autowired
    private BankBiz bankBiz;

    @After(value = "deposit() || withdraw() || transfer()")
    public void sendEmail(JoinPoint joinPoint) {
        int accountid = (int) joinPoint.getArgs()[0];
        double money = (double) joinPoint.getArgs()[1];
        int toAccountId = 0;
        if (joinPoint.getSignature().getName().equals("transfer")){
            toAccountId = (int) joinPoint.getArgs()[2];
        }
        Account account = bankBiz.findAccount(accountid);
        String email = account.getEmail();
        String info;
        String methodName = joinPoint.getSignature().getName();
        if (methodName.equals("deposit")){
            info = deposit(account, money);
        }else if (methodName.equals("withdraw")){
            info = withdraw(account, money);
        }else if (methodName.equals("transfer")){
            info = transfer(account, money, toAccountId);
        } else {
            info = "";
        }
        new Thread(() -> {
            mailBiz.sendMail(email, "银行通知", info);
        }).start();
    }

    private String deposit(Account account, double money) {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
        String dateStr = df.format(date);
        String s = "尊敬的%s先生:\n\t您的账户%s于%s存入了%s元,当前余额为%s元,\n\t\t\t\t中国银行";
        s = String.format(s, account.getAccountid(), account.getAccountid(), dateStr, money, account.getBalance());
        return s;
    }

    private String withdraw(Account account, double money) {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
        String dateStr = df.format(date);
        String s = "尊敬的%s先生:\n\t您的账户%s于%s取出了%s元,当前余额为%s元,\n\t\t\t\t中国银行";
        s = String.format(s, account.getAccountid(), account.getAccountid(), dateStr, money, account.getBalance());
        return s;
    }

    private String transfer(Account account, double money, int toAccountId) {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
        String dateStr = df.format(date);
        String s = "尊敬的%s先生:\n\t您的账户%s于%s向账户%s转入了%s元,当前余额为%s元,\n\t\t\t\t中国银行";
        s = String.format(s, account.getAccountid(), account.getAccountid(), dateStr, toAccountId, money, account.getBalance());
        return s;
    }
}
