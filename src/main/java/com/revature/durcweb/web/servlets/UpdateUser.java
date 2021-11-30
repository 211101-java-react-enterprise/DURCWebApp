package com.revature.durcweb.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.durcweb.models.User;
import com.revature.durcweb.services.UserService;
import com.revature.durcweb.web.dtos.UserRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateUser extends HttpServlet {

    Logger logger = LogManager.getLogger();
    private UserService userService;
    private ObjectMapper mapper;

    public UpdateUser(UserService userService, ObjectMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        if(session == null) {
            logger.warn("You are not logged in.");
            resp.setStatus(401);
        } else {
            UserRequest user = mapper.readValue(req.getInputStream(), UserRequest.class);
            User sessionUser = (User) session.getAttribute("user");
            sessionUser = userService.updateUser(sessionUser, user);
            if(sessionUser == null) {
                logger.fatal("Could not update current user");
                resp.setStatus(500);
                return;
            }
            session.setAttribute("user", sessionUser);
            logger.info("User successfully updated");
            resp.setStatus(204);
        }

    }
}
