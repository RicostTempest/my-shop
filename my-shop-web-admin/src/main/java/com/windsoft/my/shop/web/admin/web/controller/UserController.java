package com.windsoft.my.shop.web.admin.web.controller;

import com.windsoft.my.shop.commons.dto.BaseResult;
import com.windsoft.my.shop.domain.TbUser;
import com.windsoft.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @Author: Ricost
 * @Date: 2019/10/15 21:14
 */
@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private TbUserService tbUserService;


    /**
     * 默认在页面的Model中添加一个tbUser对象
     * @param id
     * @return
     */
    @ModelAttribute
    public TbUser getTbUser(Long id){
        TbUser tbUser = null;
        if(id != null){
            tbUser = tbUserService.getById(id);
        }
        else
            tbUser = new TbUser();
        return tbUser;
    }

    @RequestMapping(value = "list",method =RequestMethod.GET)
    public String list(Model model){
        List<TbUser> tbUsers = tbUserService.selectAll();
        model.addAttribute("tbUsers",tbUsers);
        return "user_list";
    }

    @RequestMapping(value = "form")
    public String form(Model model){
        return "user_form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbUser tbUser, RedirectAttributes redirectAttributes, Model model){
        BaseResult baseResult = tbUserService.save(tbUser);
        //保存成功
        if(baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/user/list";
        }
        //保存失败
        else{
            model.addAttribute("baseResult", baseResult);
            return "user_form";
        }
    }

    /**
     * 搜索框
     * @param keyword
     * @return
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(String keyword, Model model){
        List<TbUser> tbUsers = tbUserService.search(keyword);
        model.addAttribute("tbUsers",tbUsers);

        return "user_list";
    }

    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult = BaseResult.success();
        System.out.println(ids);
        return baseResult;
    }
}
