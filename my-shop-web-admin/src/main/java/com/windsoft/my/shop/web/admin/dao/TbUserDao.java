package com.windsoft.my.shop.web.admin.dao;

import com.windsoft.my.shop.commons.persistence.BaseDao;
import com.windsoft.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

/**
 * 查询用户表全部信息
 * @Author: Ricost
 * @Date: 2019/10/15 12:48
 */
@Repository
public interface TbUserDao extends BaseDao<TbUser> {

    TbUser getByEmail(String email);

}
