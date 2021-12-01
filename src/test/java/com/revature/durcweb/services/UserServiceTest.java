package com.revature.durcweb.services;

import com.revature.durcweb.daos.UserDAO;
import com.revature.durcweb.exceptions.AuthenticationException;
import com.revature.durcweb.exceptions.InvalidRequestException;
import com.revature.durcweb.models.User;
import com.revature.durcweb.web.dtos.NewUserRequest;
import com.revature.durcweb.web.dtos.UserRequest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class UserServiceTest {
    UserService sut;
    UserDAO mockUserDAO;

    @Before
    public void testCaseSetup() {
        mockUserDAO = mock(UserDAO.class);
        sut = new UserService(mockUserDAO);
    }

    @After
    public void testCaseCleanUp() {
        sut = null;
    }

    @Test
    public void test_authenticateUser_ValidUandP(){
        // Arrange
        User validUser = new User(1, "valid", "valid", "valid", "valid","valid");
        when(mockUserDAO.findByUsernameAndPassword(anyString(), anyString())).thenReturn(validUser);
        // Act
        User actualResult = sut.authenticateUser("valid", "valid");
        // Assert
        Assert.assertEquals(validUser,actualResult);
    }


    @Test(expected = InvalidRequestException.class)
    public void test_authenticateUser_InValidUBlank(){
        // Arrange

        // Act
        User actualResult = sut.authenticateUser("", "valid");
        // Assert
    }

    @Test(expected = InvalidRequestException.class)
    public void test_authenticateUser_InValidUNull(){
        // Arrange

        // Act
        User actualResult = sut.authenticateUser(null, "valid");
        // Assert
    }
    @Test(expected = InvalidRequestException.class)
    public void test_authenticateUser_InValidPNull(){
        // Arrange

        // Act
        User actualResult = sut.authenticateUser("valid", null);
        // Assert
    }

    @Test(expected = InvalidRequestException.class)
    public void test_authenticateUser_InValidPBlank(){
        // Arrange

        // Act
        User actualResult = sut.authenticateUser("valid", "");
        // Assert
    }

    @Test(expected = AuthenticationException.class)
    public void test_authenticateUser_NoUserFound(){
        // Arrange
        when(mockUserDAO.findByUsernameAndPassword(anyString(), anyString())).thenReturn(null);
        // Act
        User actualResult = sut.authenticateUser("valid", "valid");
        // Assert
    }

    @Test
    public void test_registerUser_Valid(){
        // Arrange
        NewUserRequest user = new NewUserRequest();
        user.setEmail("valid");
        user.setUsername("valid");
        user.setFirstName("valid");
        user.setLastName("valid");
        user.setPassword("valid");
        user.setId(1);

        when(mockUserDAO.save(any())).thenReturn(true);
        when(mockUserDAO.findByUsername(anyString())).thenReturn(null);
        when(mockUserDAO.findByEmail(anyString())).thenReturn(null);
        // Act
        boolean actualResult = sut.registerUser(user);
        // Assert
        Assert.assertTrue(actualResult);
    }

    @Test(expected = InvalidRequestException.class)
    public void test_registerUser_InvalidPNull(){
        // Arrange
        NewUserRequest user = new NewUserRequest();
        user.setEmail("valid");
        user.setUsername("valid");
        user.setFirstName("valid");
        user.setLastName("valid");
        user.setPassword(null);
        user.setId(1);
        // Act
        boolean actualResult = sut.registerUser(user);
        // Assert
    }

    @Test(expected = InvalidRequestException.class)
    public void test_registerUser_InvalidPBlank(){
        // Arrange
        NewUserRequest user = new NewUserRequest();
        user.setEmail("valid");
        user.setUsername("valid");
        user.setFirstName("valid");
        user.setLastName("valid");
        user.setPassword("");
        user.setId(1);
        // Act
        boolean actualResult = sut.registerUser(user);
        // Assert
    }
    @Test(expected = InvalidRequestException.class)
    public void test_registerUser_UTaken() {
        // Arrange
        NewUserRequest user = new NewUserRequest();
        user.setEmail("valid");
        user.setUsername("valid");
        user.setFirstName("valid");
        user.setLastName("valid");
        user.setPassword("valid");
        user.setId(1);
        User user1 = new User(user);

        when(mockUserDAO.save(any())).thenReturn(true);
        when(mockUserDAO.findByUsername(anyString())).thenReturn(user1);
        when(mockUserDAO.findByEmail(anyString())).thenReturn(null);
        // Act
        boolean actualResult = sut.registerUser(user);
        // Assert
    }

    @Test(expected = InvalidRequestException.class)
    public void test_registerUser_PTaken() {
        // Arrange
        NewUserRequest user = new NewUserRequest();
        user.setEmail("valid");
        user.setUsername("valid");
        user.setFirstName("valid");
        user.setLastName("valid");
        user.setPassword("valid");
        user.setId(1);
        User user1 = new User(user);

        when(mockUserDAO.save(any())).thenReturn(true);
        when(mockUserDAO.findByUsername(anyString())).thenReturn(null);
        when(mockUserDAO.findByEmail(anyString())).thenReturn(user1);
        // Act
        boolean actualResult = sut.registerUser(user);
        // Assert
    }

    @Test
    public void test_updateUser_Valid(){
        // Arrange
        UserRequest user = new UserRequest();
        user.setEmail("valid");
        user.setUsername("valid");
        user.setFirstName("valid");
        user.setLastName("valid");
        user.setPassword("valid");
        user.setId(1);
        User user1 = new User();
        when(mockUserDAO.update(any(),anyInt())).thenReturn(true);
        // Act
        User actualResult = sut.updateUser(user1,user);
        // Assert
        Assert.assertNotNull(actualResult);
    }

    @Test
    public void test_updateUser_InvalidNull(){
        // Arrange
        UserRequest user = new UserRequest();
        User user1 = new User();
        when(mockUserDAO.update(any(),anyInt())).thenReturn(false);
        // Act
        User actualResult = sut.updateUser(user1,user);
        // Assert
        Assert.assertNull(actualResult);
    }

    @Test
    public void test_updateUser_InvalidBlank(){
        // Arrange
        UserRequest user = new UserRequest();
        user.setEmail("");
        user.setUsername("");
        user.setFirstName("");
        user.setLastName("");
        user.setPassword("");
        user.setId(0);
        User user1 = new User();
        when(mockUserDAO.update(any(),anyInt())).thenReturn(false);
        // Act
        User actualResult = sut.updateUser(user1,user);
        // Assert
        Assert.assertNull(actualResult);
    }

    @Test
    public void test_isUserValid_Invalid(){
        User user = null;
        Assert.assertFalse(sut.isUserValid(user));
        user = new User();
        Assert.assertFalse(sut.isUserValid(user));
        user.setFirstName(null);
        Assert.assertFalse(sut.isUserValid(user));
        user.setFirstName("");
        Assert.assertFalse(sut.isUserValid(user));
        user.setFirstName("valid");
        user.setLastName(null);
        Assert.assertFalse(sut.isUserValid(user));
        user.setLastName("");
        Assert.assertFalse(sut.isUserValid(user));
        user.setLastName("valid");
        user.setEmail("");
        Assert.assertFalse(sut.isUserValid(user));
        user.setEmail(null);
        Assert.assertFalse(sut.isUserValid(user));
        user.setEmail("valid");
        user.setUsername("");
        Assert.assertFalse(sut.isUserValid(user));
        user.setUsername(null);
        Assert.assertFalse(sut.isUserValid(user));
        user.setUsername("valid");
        user.setPassword("");
        Assert.assertFalse(sut.isUserValid(user));
        user.setPassword(null);
        Assert.assertFalse(sut.isUserValid(user));
        user.setPassword("valid");
        Assert.assertTrue(sut.isUserValid(user));
    }
}
