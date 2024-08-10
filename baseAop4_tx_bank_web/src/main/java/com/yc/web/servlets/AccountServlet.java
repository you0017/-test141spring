package com.yc.web.servlets;


import com.yc.bean.Account;
import com.yc.service.BankBiz;
import com.yc.web.servlets.model.JsonModel;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/account.action")
public class AccountServlet extends BaseServlet {
    protected void email(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameter("accountid").equals("")){
            return;
        }
        int accountId = Integer.parseInt(req.getParameter("accountid"));

        ServletContext application = req.getServletContext();
        WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);
        BankBiz bankBiz = (BankBiz) ac.getBean("bankBizImpl");
        Account account = bankBiz.email(accountId);
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setObj(account);
        super.writeJson(jm,resp);
    }

    protected void deposit(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int accountid = Integer.parseInt(req.getParameter("accountid"));
        double money = Double.parseDouble(req.getParameter("money"));

        //从spring容器中获取业务对象来操作
        ServletContext application = req.getServletContext();
        WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);
        BankBiz bankBiz = (BankBiz) ac.getBean("bankBizImpl");

        Account a = bankBiz.deposit(accountid, money);
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setObj(a);
        super.writeJson(jm,resp);
    }

    protected void withdraw(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int accountid = Integer.parseInt(req.getParameter("accountid"));
        double money = Double.parseDouble(req.getParameter("money"));

        //从spring容器中获取业务对象来操作
        ServletContext application = req.getServletContext();
        WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);
        BankBiz bankBiz = (BankBiz) ac.getBean("bankBizImpl");

        Account a = bankBiz.withdraw(accountid, money);
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setObj(a);
        super.writeJson(jm,resp);
    }

    protected void openAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException {
        double money = Double.parseDouble(req.getParameter("money"));

        //从spring容器中获取业务对象来操作
        ServletContext application = req.getServletContext();
        WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);
        BankBiz bankBiz = (BankBiz) ac.getBean("bankBizImpl");

        Account a = bankBiz.openAccount( money);
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setObj(a);
        super.writeJson(jm,resp);
    }

    protected void query(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int accountid = Integer.parseInt(req.getParameter("accountid"));

        //从spring容器中获取业务对象来操作
        ServletContext application = req.getServletContext();
        WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);
        BankBiz bankBiz = (BankBiz) ac.getBean("bankBizImpl");

        Account a = bankBiz.findAccount( accountid);
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setObj(a);
        super.writeJson(jm,resp);
    }

    protected void transfer(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int accountid = Integer.parseInt(req.getParameter("accountid"));
        double money = Double.parseDouble(req.getParameter("money"));
        int toaccountid = Integer.parseInt(req.getParameter("toaccountid"));

        //从spring容器中获取业务对象来操作
        ServletContext application = req.getServletContext();
        WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);
        BankBiz bankBiz = (BankBiz) ac.getBean("bankBizImpl");

        Account a = bankBiz.transfer( accountid,money,toaccountid);
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setObj(a);
        super.writeJson(jm,resp);
    }
}
