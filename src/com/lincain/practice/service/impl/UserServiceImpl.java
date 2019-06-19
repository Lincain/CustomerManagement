package com.lincain.practice.service.impl;

import com.lincain.practice.dao.UserDao;
import com.lincain.practice.dao.impl.UserDaoImpl;
import com.lincain.practice.domain.User;
import com.lincain.practice.service.UserService;

public class UserServiceImpl implements UserService{

    private UserDao userDao = new UserDaoImpl();

    @Override
    public User login(User user) {
        return userDao.selectUserByUserNameAndPassword(user);
    }
}
