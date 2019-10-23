package com.windsoft.my.shop.web.admin.dao;

import com.windsoft.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 查询用户表全部信息
 * @Author: Ricost
 * @Date: 2019/10/15 12:48
 */
@Repository
public interface TbUserDao {
    public List<TbUser> selectAll();
    public void insert(TbUser tbUser);
    public void delete(Long id);
    public TbUser getById(Long id);
    public void update(TbUser tbUser);
    public List<TbUser> getByUserName(String username);
    public TbUser getByEmail(String email);
}
