package com.windsoft.my.shop.web.admin.dao;

import com.windsoft.my.shop.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @Author: Ricost
 * @Date: 2019/10/13 14:52
 */
public interface UserDao {
    public User getUserByEmailAndPassword(String email, String password);
}
