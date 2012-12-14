package com.samplesf2.dao;

import java.util.List;

import com.samplesf2.entity.User;

public class UserDao extends BaseHibernateDao<User> {

    public User getUser(String name) {
        List<User> users = search("from User u where u.username = ?", name);
        if (users.size() == 1) {
            return users.get(0);
        } else {
            return null;
        }
    }

}
