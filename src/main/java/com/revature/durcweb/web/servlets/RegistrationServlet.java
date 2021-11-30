package com.revature.durcweb.web.servlets;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.revature.durcweb.exceptions.InvalidRequestException;
import com.revature.durcweb.services.UserService;
import com.revature.durcweb.web.dtos.NewUserRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    Logger logger = LogManager.getLogger();
    private final UserService userService;
    private final ObjectMapper mapper;

    public RegistrationServlet(UserService userService, ObjectMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        try {
            NewUserRequest newUser = mapper.readValue(req.getInputStream(), NewUserRequest.class);
            userService.registerUser(newUser);
            logger.info("Registered new user: {}", newUser.getUsername());
            resp.setStatus(201);
        } catch (InvalidRequestException | JsonParseException | UnrecognizedPropertyException e) {
            logger.warn(e.getMessage());
            resp.setStatus(400);
        } catch (Exception e) {
            logger.fatal(e.getStackTrace());
            resp.setStatus(500);
        }
    }
}
