package com.revature.durcweb.web.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.durcweb.models.User;

public class UserRequest {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    public UserRequest() {

    }

    public UserRequest(int id, String firstName, String lastName, String email, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UserRequest(User userIn){
        this.id = userIn.getId();
        this.firstName = userIn.getFirstName();
        this.lastName = userIn.getLastName();
        this.email = userIn.getEmail();
        this.username = userIn.getUsername();
        this.password = userIn.getPassword();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
