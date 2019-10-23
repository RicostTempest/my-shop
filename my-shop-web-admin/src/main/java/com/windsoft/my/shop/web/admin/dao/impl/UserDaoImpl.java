package com.windsoft.my.shop.web.admin.dao.impl;

import com.windsoft.my.shop.domain.User;
import com.windsoft.my.shop.web.admin.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @Author: Ricost
 * @Date: 2019/10/14 19:19
 */
@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    public User getUserByEmailAndPassword(String email, String password) {
        logger.debug("调用getUser,参数{}{}",email,password);

        User user = null;

        if("ricost@foxmail.com".equals(email)){
            if ("admin".equals(password)){
                user = new User();
                user.setEmail("ricost@foxmail.com");
                user.setUsername("Ricost");

                logger.info("获取\"{}\"的用户信息成功",user.getEmail());
            }else{
                logger.warn("获取\"{}\"的用户信息失败",email);
            }
        }
        return user;
    }
}
