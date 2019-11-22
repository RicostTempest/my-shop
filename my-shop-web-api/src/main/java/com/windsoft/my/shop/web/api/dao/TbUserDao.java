package com.windsoft.my.shop.web.api.dao;

import com.windsoft.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName TbUserDao
 * @Description
 * @Author Ricost
 * @Date 2019/11/22 12:01
 * @Version V1.0
 **/
@Repository
public interface TbUserDao {
    public TbUser login(TbUser tbUser);
}