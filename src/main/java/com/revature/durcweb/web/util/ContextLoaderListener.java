package com.revature.durcweb.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.durcweb.services.UserService;
import com.revature.durcweb.web.servlets.AuthServlet;
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

        AuthServlet authServlet = new AuthServlet(userService, mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Application Shutting Down");
    }
}
