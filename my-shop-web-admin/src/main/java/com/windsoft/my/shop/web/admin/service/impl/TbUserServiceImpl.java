package com.windsoft.my.shop.web.admin.service.impl;

import com.windsoft.my.shop.domain.TbUser;
import com.windsoft.my.shop.web.admin.dao.TbUserDao;
import com.windsoft.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author: Ricost
 * @Date: 2019/10/15 12:51
 */
@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserDao tbUserDao;
    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    @Override
    public void save(TbUser tbUser) {
        //增加用户
        if(tbUser.getId() == null){
            tbUser.setCreated(new Date());
            tbUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
            tbUserDao.insert(tbUser);
        }
        //编辑用户
        else {
            tbUserDao.update(tbUser);
        }

    }

    @Override
    public void delete(Long id) {
        tbUserDao.delete(id);
    }

    @Override
    public TbUser getById(Long id) {
        return tbUserDao.getById(id);
    }

    @Override
    public void update(TbUser tbUser) {
        tbUserDao.update(tbUser);
    }

    @Override
    public List<TbUser> getByUserName(String username) {
        return tbUserDao.getByUserName(username);
    }

    @Override
    public TbUser login(String email, String password) {

        TbUser tbUser = tbUserDao.getByEmail(email);

        if (tbUser != null){
            //md5加密
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());

            //判断密文密码时否正确
            if(md5Password.equals(tbUser.getPassword())){
                return tbUser;
            }
        }

        return null;
    }

}
