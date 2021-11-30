package com.revature.durcweb.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.durcweb.exceptions.AuthenticationException;
import com.revature.durcweb.exceptions.InvalidRequestException;
import com.revature.durcweb.models.User;
import com.revature.durcweb.services.UserService;
import com.revature.durcweb.web.dtos.Credentials;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    Logger logger = LogManager.getLogger();
    private final UserService userService;
    private final ObjectMapper mapper;

    public AuthServlet(UserService userService, ObjectMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    //login, maps json value to credentials object and authenticates with it
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Credentials creds = mapper.readValue(req.getInputStream(), Credentials.class);
            User user = userService.authenticateUser(creds.getUsername(), creds.getPassword());
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            logger.info("User {} successfully logged in", user.getUsername());
            resp.setStatus(204);
        } catch (InvalidRequestException e) {
            logger.warn(e.getMessage());
            resp.setStatus(400);
        } catch(AuthenticationException e) {
            logger.warn(e.getMessage());
            resp.setStatus(401);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            resp.setStatus(500);
        }
    }

    //logout, invalidate current session
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Closing session");
        req.getSession().invalidate();
    }
}
