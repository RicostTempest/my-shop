package com.windsoft.my.shop.web.api.service.impl;

import com.windsoft.my.shop.domain.TbUser;
import com.windsoft.my.shop.web.api.dao.TbUserDao;
import com.windsoft.my.shop.web.api.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @ClassName TbUserServiceImpl
 * @Description
 * @Author Ricost
 * @Date 2019/11/22 12:02
 * @Version V1.0
 **/
@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    TbUserDao tbUserDao;

    @Override
    public TbUser login(TbUser tbUser) {
        TbUser user = tbUserDao.login(tbUser);

        if(user != null){
            String password = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
            if(password.equals(user.getPassword())){
                return user;
            }
        }

        return null;
    }
}