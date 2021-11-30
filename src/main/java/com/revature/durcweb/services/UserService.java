package com.revature.durcweb.services;

import com.revature.durcweb.daos.UserDAO;
import com.revature.durcweb.exceptions.AuthenticationException;
import com.revature.durcweb.exceptions.InvalidRequestException;
import com.revature.durcweb.models.User;
import com.revature.durcweb.web.dtos.NewUserRequest;
import com.revature.durcweb.web.dtos.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

   UserDAO userDAO = new UserDAO();

    public User authenticateUser(String username, String password) {

        if(username == null || username.equals("") || password == null || password.equals("")) {
            throw new InvalidRequestException("Invalid credentials provided");
        }

        User user = userDAO.findByUsernameAndPassword(username, password);

        if(user == null) {
            throw new AuthenticationException("No user was found with those credentials");
        }
        System.out.println(user);
        return user;
    }

    public boolean registerUser(NewUserRequest userIn) {

        User newUser = new User(userIn);

        if(!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid user data provided");
        }
        if(isUsernameTaken(newUser.getUsername())) {
            throw new InvalidRequestException("Username is already taken");
        }
        if(isEmailTaken(newUser.getEmail())) {
            throw new InvalidRequestException("Email is already taken");
        }

        return userDAO.save(newUser);
    }

    public List<UserResponse> getAllUsers(){
        return userDAO.getAll(User.class).stream().map(UserResponse::new).collect(Collectors.toList());
    }

    public boolean isUserValid(User user) {
        if (user == null) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if (user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if (user.getEmail() == null || user.getEmail().trim().equals("")) return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        return user.getPassword() != null && !user.getPassword().trim().equals("");
    }

    public boolean isUsernameTaken(String username) {
        return userDAO.findByUsername(username) != null;
    }

    public boolean isEmailTaken(String email) {
        return userDAO.findByEmail(email) != null;
    }

    public boolean deleteUser(User user){
        return userDAO.delete(user.getId(), user);
    }
}
