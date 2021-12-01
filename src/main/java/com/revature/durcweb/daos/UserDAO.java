package com.revature.durcweb.daos;

import com.revature.boilerplateorm.daos.GenericDAO;
import com.revature.durcweb.models.User;
import com.revature.durcweb.util.datasource.ConnectionPool;

import java.sql.Connection;
import java.util.List;

public class UserDAO{

    ConnectionPool pool;
    GenericDAO gDao;
    Connection conn;

    public UserDAO(ConnectionPool pool) {
        this.pool = pool;
        conn = pool.getConnection();
        gDao = new GenericDAO(conn);
    }

    public boolean save(User user) {

        boolean result = gDao.save(user);
        pool.releaseConnection(conn);
        return result;
    }

    public User find(Class<User> type, int key) {
        User foundUser = gDao.find(type, key);
        pool.releaseConnection(conn);
        return foundUser;
    }

    public boolean update(User user, int key) {
        boolean result = gDao.update(user,key);
        pool.releaseConnection(conn);
        return result;
    }

    public boolean delete(User user, int key) {
        boolean result = gDao.delete(user,key);
        pool.releaseConnection(conn);
        return result;
    }

    public List<User> getAll(Class<User> type){
        List<User> userList = gDao.getAll(type);
        pool.releaseConnection(conn);
        return userList;
    }

    public List<User> findAll(Class<User> type, Object key) {
        List<User> userList = gDao.findAll(type, key);
        pool.releaseConnection(conn);
        return userList;
    }

    public User findByUsernameAndPassword(String username, String password) {
        User foundUser = gDao.find(User.class, username, password);
        pool.releaseConnection(conn);
        return foundUser;
    }

    public User findByUsername(String username) {
        User foundUser = gDao.find(User.class, username);
        pool.releaseConnection(conn);
        return foundUser;
    }

    public User findByEmail(String email) {
        User foundUser = gDao.find(User.class, email);
        pool.releaseConnection(conn);
        return foundUser;
    }
}
