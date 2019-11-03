package com.windsoft.my.shop.web.admin.web.controller;

import com.windsoft.my.shop.web.admin.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 内容管理
 */
@Controller
public class ContentController {
    @Autowired
    private TbContentService tbContentService;
}
