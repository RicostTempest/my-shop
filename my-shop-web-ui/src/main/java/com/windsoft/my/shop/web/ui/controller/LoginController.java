package com.windsoft.my.shop.web.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName LoginController
 * @Description 登录控制器
 * @Author Ricost
 * @Date 2019/11/22 9:57
 * @Version V1.0
 **/
@Controller
public class LoginController {
    @RequestMapping(value = "login")
    public String login(){
        return "login";
    }
}