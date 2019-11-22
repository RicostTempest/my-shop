package com.windsoft.my.shop.web.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName RegisterController
 * @Description 注册控制器
 * @Author Ricost
 * @Date 2019/11/22 10:01
 * @Version V1.0
 **/
@Controller
public class RegisterController {
    @RequestMapping(value = "register")
    public String register(){
        return "register";
    }
}