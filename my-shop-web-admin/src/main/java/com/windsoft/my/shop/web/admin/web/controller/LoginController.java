package com.windsoft.my.shop.web.admin.web.controller;

import com.windsoft.my.shop.commons.constant.ConstantUtils;
import com.windsoft.my.shop.commons.utils.CookieUtils;
import com.windsoft.my.shop.domain.TbUser;
import com.windsoft.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Ricost
 * @Date: 2019/10/14 19:28
 */
@Controller
public class LoginController {

    public static final String COOKIE_NAME_USER_INFO = "userInfo";

    @Autowired
    TbUserService tbUserService;

    /**
     * 输入框为空或者是login时跳转到login.jsp页面
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest httpServletRequest){

        String userInfo = CookieUtils.getCookieValue(httpServletRequest,COOKIE_NAME_USER_INFO);

        if(StringUtils.isNotBlank(userInfo)){
            String[] userInfoArray = userInfo.split(":");
            String email = userInfoArray[0];
            String password = userInfoArray[1];
            httpServletRequest.setAttribute("email",email);
            httpServletRequest.setAttribute("password",password);
            httpServletRequest.setAttribute("isRemember",true);
        }


        return "login";
    }

    /**
     * @RequestParam(required = true) 请求时必须带有参数
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email,
                        @RequestParam(required = true) String password,
                        @RequestParam(required = false) String isRemember,
                        HttpServletRequest httpServletRequest,
                        HttpServletResponse httpServletResponse,
                        Model model){

        boolean remember = isRemember == null ? false : true;

        if(!remember){
            CookieUtils.deleteCookie(httpServletRequest,httpServletResponse,COOKIE_NAME_USER_INFO);
        }

        TbUser tbUser = tbUserService.login(email,password);

        //登录失败
        if(tbUser == null){
            model.addAttribute("message","用户名或密码错误，请重新输入");
            return "login";
        }
        //登录成功
        else{
            //记住登录信息
            if(remember){
                CookieUtils.setCookie(httpServletRequest,
                        httpServletResponse,
                        COOKIE_NAME_USER_INFO,String.format("%s:%s",email,password),
                        7*24*60*60);
            }

            //将登陆信息放入会话
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER, tbUser);
            return "redirect:/main";
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().invalidate();
        return "redirect:/login";
    }
}
