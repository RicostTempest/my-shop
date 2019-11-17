package com.windsoft.my.shop.web.admin.service;

import com.windsoft.my.shop.commons.persistence.BaseService;
import com.windsoft.my.shop.domain.TbUser;

/**
 * @Author: Ricost
 * @Date: 2019/10/15 12:50
 */
public interface TbUserService extends BaseService<TbUser> {
    TbUser login(String email, String password);
}
