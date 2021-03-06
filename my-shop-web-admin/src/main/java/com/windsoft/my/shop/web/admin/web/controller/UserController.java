package com.windsoft.my.shop.web.admin.web.controller;

import com.windsoft.my.shop.commons.dto.BaseResult;
import com.windsoft.my.shop.commons.dto.PageInfo;
import com.windsoft.my.shop.domain.TbUser;
import com.windsoft.my.shop.web.admin.abstracts.AbstractBaseController;
import com.windsoft.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Ricost
 * @Date: 2019/10/15 21:14
 */
@Controller
@RequestMapping(value = "user")
public class UserController extends AbstractBaseController<TbUser,TbUserService> {
    /**
     * 默认在页面的Model中添加一个tbUser对象
     * @param id
     * @return
     */
    @ModelAttribute
    public TbUser getTbUser(Long id){
        TbUser tbUser = null;
        if(id != null){
            tbUser = service.getById(id);
        }
        else
            tbUser = new TbUser();
        return tbUser;
    }

    @Override
    @RequestMapping(value = "list",method =RequestMethod.GET)
    public String list(){
        return "user_list";
    }

    @RequestMapping(value = "form")
    public String form(){
        return "user_form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbUser tbUser, RedirectAttributes redirectAttributes, Model model){
        BaseResult baseResult = service.save(tbUser);
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
        List<TbUser> tbUsers = service.search(keyword);
        model.addAttribute("tbUsers",tbUsers);

        return "user_list";
    }

    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult = BaseResult.fail("未知错误");
        if (StringUtils.isNotBlank(ids)){
            String[] idArray = ids.split(",");
            service.deleteMulti(idArray);
            baseResult = BaseResult.success("数据删除成功");
        }
        else {
            baseResult = BaseResult.fail("数据删除失败");
        }
        return baseResult;
    }

    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public PageInfo<TbUser> page(HttpServletRequest request, TbUser tbUser){
        Map<String, Object> result = new HashMap<>();

        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 :Integer.parseInt(strDraw);
        int start = strStart == null ? 0 :Integer.parseInt(strStart);
        int length = strLength == null ? 10 :Integer.parseInt(strLength);

        PageInfo<TbUser> pageInfo = service.page(start, length, draw, tbUser);

        return pageInfo;
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(TbUser tbUser){
        return "user_detail";
    }
}
