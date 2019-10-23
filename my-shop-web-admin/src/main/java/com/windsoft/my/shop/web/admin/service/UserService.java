package com.windsoft.my.shop.web.admin.service;

import com.windsoft.my.shop.domain.User;

/**
 * Login
 * 邮箱 & 密码
 * @Author: Ricost
 * @Date: 2019/10/13 15:02
 */
public interface UserService {
    public User login(String email, String password);
}

