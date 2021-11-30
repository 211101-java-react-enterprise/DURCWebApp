package com.revature.durcweb.daos;

import com.revature.boilerplateorm.daos.GenericDAO;

import com.revature.durcweb.models.Account;
import com.revature.util.ConnectionFactory;

public class AccountDAO {

    GenericDAO genericDAO = new GenericDAO(ConnectionFactory.getInstance().getConnection());

    public void findByUserId(int userId) {
        genericDAO.findAll(Account.class, userId);
    }
}
