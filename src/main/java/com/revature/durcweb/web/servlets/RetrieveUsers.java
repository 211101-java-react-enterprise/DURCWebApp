package com.revature.durcweb.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.durcweb.exceptions.NoUserFoundException;
import com.revature.durcweb.models.User;
import com.revature.durcweb.services.UserService;
import com.revature.durcweb.web.dtos.UserRequest;
import com.revature.durcweb.web.dtos.UserResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RetrieveUsers extends HttpServlet {

    Logger logger = LogManager.getLogger();
    private final UserService userService;
    private final ObjectMapper mapper;

    public RetrieveUsers(UserService userService, ObjectMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    //Retrieves and displays all Users
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String payload;
        try {
            UserRequest user = mapper.readValue(req.getInputStream(), UserRequest.class);
            UserResponse retrievedUser = userService.getUserByName(user.getFirstName(), user.getLastName());
            payload = mapper.writeValueAsString(retrievedUser);
            resp.setStatus(200);
        } catch (NoUserFoundException e) {
            logger.warn(e.getMessage());
            List<UserResponse> users = userService.getAllUsers();
            if(users.isEmpty()) {
                logger.error("There are no users in this database");
                resp.setStatus(404); // no users found
                return;
            }
            payload = mapper.writeValueAsString(users);
            resp.setStatus(400);
        }

        resp.getWriter().write(payload);
    }
}
