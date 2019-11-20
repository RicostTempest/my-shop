package com.windsoft.my.shop.web.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String index(){
        return "index";
    }
}
