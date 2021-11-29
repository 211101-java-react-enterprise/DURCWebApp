package com.revature.durcweb.services;

import com.revature.durcweb.daos.UserDAO;
import com.revature.durcweb.exceptions.AuthenticationException;
import com.revature.durcweb.exceptions.InvalidRequestException;
import com.revature.durcweb.models.User;
import com.revature.durcweb.web.dtos.Credentials;

public class UserService {

   UserDAO userDAO = new UserDAO();

    public User authenticateUser(Credentials cred) {

        if(cred.getUsername() == null || cred.getUsername().equals("") || cred.getPassword() == null || cred.getPassword().equals("")) {
            throw new InvalidRequestException("Invalid credentials provided");
        }

        User user = userDAO.findByUsernameAndPassword(cred);

        if(user == null) {
            throw new AuthenticationException("No user was found with those credentials");
        }
        return user;
    }
}
