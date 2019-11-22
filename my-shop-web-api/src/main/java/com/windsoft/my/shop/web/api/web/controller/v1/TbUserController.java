package com.windsoft.my.shop.web.api.web.controller.v1;

import com.windsoft.my.shop.commons.dto.BaseResult;
import com.windsoft.my.shop.domain.TbUser;
import com.windsoft.my.shop.web.api.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TbUserController
 * @Description
 * @Author Ricost
 * @Date 2019/11/22 12:17
 * @Version V1.0
 **/
@RestController
@RequestMapping(value = "${api.path.v1}/users")
public class TbUserController {
    @Autowired
    TbUserService tbUserService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public BaseResult login(TbUser tbUser){
        TbUser user = tbUserService.login(tbUser);

        if(user != null){
            return BaseResult.success("登录成功", user);
        }else {
            return BaseResult.fail("帐号或密码错误");
        }
    }
}