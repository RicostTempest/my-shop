package com.windsoft.my.shop.web.admin.web.controller;

import com.windsoft.my.shop.commons.dto.BaseResult;
import com.windsoft.my.shop.commons.dto.PageInfo;
import com.windsoft.my.shop.domain.TbContent;
import com.windsoft.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 内容管理
 */
@Controller
@RequestMapping(value = "content")
public class ContentController {
    @Autowired
    private TbContentService tbContentService;

    /**
     * 默认在页面的Model中添加一个tbUser对象
     * @param id
     * @return
     */
    @ModelAttribute
    public TbContent getTbUser(Long id){
        TbContent tbContent = null;
        if(id != null){
            tbContent = tbContentService.getById(id);
        }
        else
            tbContent = new TbContent();
        return tbContent;
    }

    @RequestMapping(value = "list",method =RequestMethod.GET)
    public String list(Model model){
        return "content_list";
    }

    @RequestMapping(value = "form")
    public String form(Model model){
        return "content_form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContent tbContent, RedirectAttributes redirectAttributes, Model model){
        BaseResult baseResult = tbContentService.save(tbContent);
        //保存成功
        if(baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/content/list";
        }
        //保存失败
        else{
            model.addAttribute("baseResult", baseResult);
            return "content_form";
        }
    }


    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult = BaseResult.fail("未知错误");
        if (StringUtils.isNotBlank(ids)){
            String[] idArray = ids.split(",");
            tbContentService.deleteMulti(idArray);
            baseResult = BaseResult.success("内容删除成功");
        }
        else {
            baseResult = BaseResult.fail("内容删除失败");
        }
        return baseResult;
    }

    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public PageInfo<TbContent> page(HttpServletRequest request, TbContent tbContent){

        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 :Integer.parseInt(strDraw);
        int start = strStart == null ? 0 :Integer.parseInt(strStart);
        int length = strLength == null ? 10 :Integer.parseInt(strLength);

        PageInfo<TbContent> pageInfo = tbContentService.page(start, length, draw, tbContent);

        return pageInfo;
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(TbContent tbContent){
        return "content_detail";
    }
}
