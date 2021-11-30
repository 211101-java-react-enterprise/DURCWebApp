package com.revature.durcweb.web.servlets;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.revature.durcweb.exceptions.InvalidRequestException;
import com.revature.durcweb.models.User;
import com.revature.durcweb.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

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
            User newUser = mapper.readValue(req.getInputStream(), User.class);
            userService.registerUser(newUser);
            resp.setStatus(201);
        } catch (InvalidRequestException | JsonParseException | UnrecognizedPropertyException e) {
            System.out.println(e.getMessage());
            resp.setStatus(400);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }
    }
}
