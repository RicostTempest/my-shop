package com.windsoft.my.shop.web.admin.service.impl;

import com.windsoft.my.shop.commons.dto.BaseResult;
import com.windsoft.my.shop.commons.dto.PageInfo;
import com.windsoft.my.shop.commons.validator.BeanValidator;
import com.windsoft.my.shop.domain.TbUser;
import com.windsoft.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.windsoft.my.shop.web.admin.dao.TbUserDao;
import com.windsoft.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Ricost
 * @Date: 2019/10/15 12:51
 */
@Service
public class TbUserServiceImpl extends AbstractBaseServiceImpl<TbUser, TbUserDao> implements TbUserService {

    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = getDao().getByEmail(email);

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

    @Override
    public BaseResult save(TbUser tbUser) {
        String validator = BeanValidator.validator(tbUser);
        if(validator != null){
            return BaseResult.fail(validator);
        }
        //通过验证
        else{
            tbUser.setUpdated(new Date());
            //增加用户
            if(tbUser.getId() == null){
                tbUser.setCreated(new Date());
                tbUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
                getDao().insert(tbUser);
            }
            //编辑用户
            else {
                update(tbUser);
            }

            return BaseResult.success("保存信息成功");
        }
    }

    @Override
    public List<TbUser> search(String keyword) {
        TbUser tbUser = new TbUser();
        tbUser.setEmail(keyword);
        tbUser.setPhone(keyword);
        tbUser.setUsername(keyword);
        return getDao().search(tbUser);
    }
}
