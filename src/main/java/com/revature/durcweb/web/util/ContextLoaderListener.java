package com.revature.durcweb.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.boilerplateorm.daos.GenericDAO;
import com.revature.durcweb.daos.UserDAO;
import com.revature.durcweb.services.UserService;
import com.revature.durcweb.util.LoadProperties;
import com.revature.durcweb.web.servlets.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Properties;

public class ContextLoaderListener implements ServletContextListener {

    Logger logger = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        logger.info("Application Initializing");

        ObjectMapper mapper = new ObjectMapper();
        Properties props;
        props = LoadProperties.loadProp();

        GenericDAO genericDAO = new GenericDAO(props);
        UserDAO userDAO = new UserDAO(genericDAO);
        UserService userService = new UserService(userDAO);

        RegistrationServlet registrationServlet = new RegistrationServlet(userService, mapper);
        AuthServlet authServlet = new AuthServlet(userService, mapper);
        RetrieveUsers retrieveUsers = new RetrieveUsers(userService, mapper);
        DeleteUser deleteUser = new DeleteUser(userService, mapper);
        UpdateUser updateUser = new UpdateUser(userService, mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("RegistrationServlet", registrationServlet).addMapping("/registration");
        context.addServlet("RetrieveUsers", retrieveUsers).addMapping("/users");
        context.addServlet("DeleteUser", deleteUser).addMapping("/delete");
        context.addServlet("UpdateUser", updateUser).addMapping("/update");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Application Shutting Down");
    }
}
