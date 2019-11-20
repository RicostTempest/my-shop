package com.windsoft.my.shop.web.api.web.controller;

import com.windsoft.my.shop.domain.TbContent;
import com.windsoft.my.shop.domain.TbContentCategory;
import com.windsoft.my.shop.web.api.service.TbContentService;
import com.windsoft.my.shop.web.api.web.dto.TbContentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TbContentController
 * @Description
 * @Author Ricost
 * @Date 2019/11/20 8:10
 * @Version V1.0
 **/

@RestController  //此注解标识的累返回类型都是JSON
@RequestMapping(value = "content")
public class TbContentController {
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
        if(id == null){
            tbContent = new TbContent();
        }

        return tbContent;
    }

    @RequestMapping(value = "findContentByCategoryId", method = RequestMethod.GET)
    public List<TbContentDTO> findContentByCategoryId(Long categoryId){
        List<TbContentDTO> tbContentDTOS = null;
        List<TbContent> tbContents = tbContentService.selectByCategoryId(categoryId);
        if(tbContents != null && tbContents.size() >0){
            tbContentDTOS = new ArrayList<>();

            for(TbContent tbContent :tbContents){
                TbContentDTO tbContentDTO = new TbContentDTO();
                //使用反射机制进行数据拷贝注入 由Spring提供
                BeanUtils.copyProperties(tbContent,tbContentDTO);
                tbContentDTOS.add(tbContentDTO);
            }
        }

        return tbContentDTOS;
    }
}