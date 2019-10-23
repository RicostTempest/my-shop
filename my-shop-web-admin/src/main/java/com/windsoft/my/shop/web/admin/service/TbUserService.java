package com.windsoft.my.shop.web.admin.service;

import com.windsoft.my.shop.domain.TbUser;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Author: Ricost
 * @Date: 2019/10/15 12:50
 */
public interface TbUserService {
    public List<TbUser> selectAll();
    public void save(TbUser tbUser);
    public void delete(Long id);
    public TbUser getById(Long id);
    public void update(TbUser tbUser);
    public List<TbUser> getByUserName(String username);
    public TbUser login(String email, String password);
}
