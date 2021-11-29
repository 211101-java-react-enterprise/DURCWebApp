package com.revature.durcweb.web.servlets;

import com.revature.durcweb.models.Account;
import com.revature.durcweb.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AccountServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        List<Account> accounts;

        HttpSession session = req.getSession(false);
        if(session == null) {
            //todo user is not logged in
        } else {
            User user = (User) session.getAttribute("user");
            //todo display accounts
        }

    }
}
