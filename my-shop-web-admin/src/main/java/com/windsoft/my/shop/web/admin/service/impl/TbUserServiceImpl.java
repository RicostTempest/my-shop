package com.windsoft.my.shop.web.admin.service.impl;

import com.windsoft.my.shop.commons.dto.BaseResult;
import com.windsoft.my.shop.domain.TbUser;
import com.windsoft.my.shop.web.admin.dao.TbUserDao;
import com.windsoft.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
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
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserDao tbUserDao;
    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    @Override
    public BaseResult save(TbUser tbUser) {
        BaseResult baseResult = checkTbUser(tbUser);
        if(baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            tbUser.setUpdated(new Date());
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

            baseResult.setMessage("保存用户成功");
        }
        return baseResult;
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

    @Override
    public List<TbUser> search(String keyword) {
        TbUser tbUser = new TbUser();
        tbUser.setEmail(keyword);
        tbUser.setPhone(keyword);
        tbUser.setUsername(keyword);
        return tbUserDao.search(tbUser);
    }

    @Override
    public void deleteMulti(String[] ids) {
        tbUserDao.deleteMulti(ids);
    }

    /**
     * 分页查询
     * @param start
     * @param length
     * @return
     */
    @Override
    public List<TbUser> page(int start, int length) {
        Map<String, Object> params = new HashMap<>();
        params.put("start",start);
        params.put("length",length);
        return tbUserDao.page(params);
    }

    @Override
    public int count() {
        return tbUserDao.count();
    }

    private BaseResult checkTbUser(TbUser tbUser){
        BaseResult baseResult = BaseResult.success();

        if (StringUtils.isBlank(tbUser.getUsername())){
            baseResult = BaseResult.fail("姓名不能为空");
        }else if(StringUtils.isBlank(tbUser.getEmail())) {
            baseResult = BaseResult.fail("邮箱不能为空");
        }

        return baseResult;
    }
}
