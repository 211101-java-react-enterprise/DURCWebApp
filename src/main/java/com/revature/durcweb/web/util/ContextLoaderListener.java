package com.revature.durcweb.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.durcweb.services.AccountService;
import com.revature.durcweb.services.UserService;
import com.revature.durcweb.web.servlets.AccountServlet;
import com.revature.durcweb.web.servlets.AuthServlet;
import com.revature.durcweb.web.servlets.RegistrationServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {

    Logger logger = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        logger.info("Application Initializing");

        ObjectMapper mapper = new ObjectMapper();
        UserService userService = new UserService();
        AccountService accountService = new AccountService();

        RegistrationServlet registrationServlet = new RegistrationServlet(userService, mapper);
        AuthServlet authServlet = new AuthServlet(userService, mapper);
        AccountServlet accountServlet = new AccountServlet(accountService, mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("RegistrationServlet", registrationServlet).addMapping("/registration");
        context.addServlet("AccountServlet", accountServlet).addMapping("/account");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Application Shutting Down");
    }
}
