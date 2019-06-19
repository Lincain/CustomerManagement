package com.lincain.practice.dao;

import com.lincain.practice.domain.User;

public interface UserDao {

    User selectUserByUserNameAndPassword(User user);
}
