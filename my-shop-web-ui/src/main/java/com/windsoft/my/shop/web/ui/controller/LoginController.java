package com.windsoft.my.shop.web.ui.controller;

import com.windsoft.my.shop.commons.dto.BaseResult;
import com.windsoft.my.shop.web.ui.api.UsersApi;
import com.windsoft.my.shop.web.ui.dto.TbUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName LoginController
 * @Description 登录控制器
 * @Author Ricost
 * @Date 2019/11/22 9:57
 * @Version V1.0
 **/
@Controller
public class LoginController {
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(TbUser tbUser, Model model, HttpServletRequest request) throws Exception{
        TbUser user = UsersApi.login(tbUser);

        if (user == null){
            model.addAttribute("baseResult", BaseResult.fail("用户名或密码错误"));

            return "login";
        }
        else{
            request.getSession().setAttribute("tbUser",user);
            return "redirect:/index";
        }

    }
}