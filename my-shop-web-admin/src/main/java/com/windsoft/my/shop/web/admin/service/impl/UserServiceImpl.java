package com.windsoft.my.shop.web.admin.service.impl;

import com.windsoft.my.shop.domain.User;
import com.windsoft.my.shop.web.admin.dao.UserDao;
import com.windsoft.my.shop.web.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Ricost
 * @Date: 2019/10/13 15:03
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User login(String email, String password) {

        return userDao.getUserByEmailAndPassword(email, password);
    }
}

