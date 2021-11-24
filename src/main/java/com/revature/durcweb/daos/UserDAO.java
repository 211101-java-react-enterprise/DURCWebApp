package com.revature.durcweb.daos;

import com.revature.boilerplateorm.daos.GenericDAO;
import com.revature.boilerplateorm.models.User;
import com.revature.durcweb.util.ConnectionFactory;

import java.util.List;

public class UserDAO {

    GenericDAO gDao = new GenericDAO(ConnectionFactory.getInstance().getConnection());

    public boolean save(User user) {
        return gDao.save(user);
    }

    public <T> T find(int key, Class<T> type) {
        return gDao.find(key, type);
    }

    public boolean update(int key, User user) {
        return gDao.update(key, user);
    }

    public boolean delete(int key, User user) {
        return gDao.delete(key, user);
    }

    public <T> List<T> findAll(Object key, Class<T> type) {
        return gDao.findAll(key, type);
    }
}
