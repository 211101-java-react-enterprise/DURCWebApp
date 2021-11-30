package com.revature.durcweb.daos;

import com.revature.boilerplateorm.daos.GenericDAO;
import com.revature.durcweb.models.User;
import com.revature.util.ConnectionFactory;
import java.sql.Connection;
import java.util.List;

public class UserDAO{

    Connection conn = ConnectionFactory.getInstance().getConnection();
    GenericDAO gDao = new GenericDAO(conn);

    public boolean save(User user) {
        return gDao.save(user);
    }

    public <T> T find(int key, Class<T> type) {
        return gDao.find(type, key);
    }

    public boolean update(int key, User user) {
        return gDao.update(key, user);
    }

    public boolean delete(int key, User user) {
        return gDao.delete(user, key);
    }

    public <T> List<T> getAll(Class<T> type){
        return gDao.getAll(type);
    }

    public <T> List<T> findAll(Object key, Class<T> type) {
        return gDao.findAll(type, key);
    }

    public User findByUsernameAndPassword(String username, String password) {
        return gDao.find(User.class, username, password);
    }

    public User findByUsername(String username) {
        return gDao.find(User.class, username);
    }

    public User findByEmail(String email) {
        return gDao.find(User.class, email);
    }
}
