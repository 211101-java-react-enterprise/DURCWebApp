package com.revature.durcweb.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.durcweb.models.Account;
import com.revature.durcweb.models.User;
import com.revature.durcweb.services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AccountServlet extends HttpServlet {

    AccountService accountService;
    ObjectMapper mapper;

    public AccountServlet(AccountService accountService, ObjectMapper mapper) {
        this.accountService = accountService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");


        HttpSession session = req.getSession(false);
        if(session == null) {
            //todo user is not logged in
        } else {
            User user = (User) session.getAttribute("user");
            List<Account> accounts = accountService.findMyAccounts(user.getId());
            //todo display accounts
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //create an account for current session user
        resp.setContentType("application/json");
    }

    //maybe move this to another page
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //delete an account for current session user
    }
}
