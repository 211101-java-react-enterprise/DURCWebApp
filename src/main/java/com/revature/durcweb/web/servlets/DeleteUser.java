package com.revature.durcweb.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.durcweb.models.User;
import com.revature.durcweb.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteUser extends HttpServlet {
    private final UserService userService;
    private final ObjectMapper mapper;

    public DeleteUser(UserService userService, ObjectMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.setStatus(403);
        } else {
            User user = (User) session.getAttribute("user");
            if(userService.deleteUser(user)){
                session.removeAttribute("user");
                resp.setStatus(200);
            }else{
                resp.setStatus(500);
            }
        }
    }
}
