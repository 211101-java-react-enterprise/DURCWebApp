package com.revature.durcweb.daos;

import com.revature.boilerplateorm.daos.GenericDAO;
import com.revature.durcweb.models.User;

import java.util.List;

public class UserDAO{

    GenericDAO gDao;

    public UserDAO(GenericDAO genericDAO) {
        gDao = genericDAO;
    }

    public boolean save(User user) {

        return gDao.save(user);
    }

    public User find(Class<User> type, int key) {
        return gDao.find(type, key);
    }

    public boolean update(User user, int key) {
        return gDao.update(user,key);
    }

    public boolean delete(User user, int key) {
        return gDao.delete(user,key);
    }

    public List<User> getAll(Class<User> type){
        return gDao.getAll(type);
    }

    public List<User> findAll(Class<User> type, Object key) {
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
