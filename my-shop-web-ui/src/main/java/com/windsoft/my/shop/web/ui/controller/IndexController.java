package com.windsoft.my.shop.web.ui.controller;

import com.windsoft.my.shop.web.ui.api.ContentsAPI;
import com.windsoft.my.shop.web.ui.dto.TbContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @ClassName IndexController
 * @Description
 * @Author RicostTempest
 * @Date 2019/11/20 21:55
 * @Version V1.0
 **/
@Controller
public class IndexController {
    @RequestMapping(value = {"","index"}, method = RequestMethod.GET)
    public String index(Model model){
        requestContentsPPT(model);
        return "index";
    }

    public void requestContentsPPT(Model model){
        List<TbContent> tbContents = ContentsAPI.ppt();
        model.addAttribute("ppts",tbContents);
    }
}
