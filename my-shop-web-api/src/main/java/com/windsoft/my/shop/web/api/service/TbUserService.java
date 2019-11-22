package com.windsoft.my.shop.web.api.service;

import com.windsoft.my.shop.domain.TbUser;

/**
 * @InterfaceName TbUserService
 * @Description
 * @Author Ricost
 * @Date 2019/11/22 12:01
 * @Version V1.0
 **/

public interface TbUserService {
    public TbUser login(TbUser  tbUser);
}