package com.windsoft.my.shop.web.api.service.impl;

import com.windsoft.my.shop.domain.TbContent;
import com.windsoft.my.shop.domain.TbContentCategory;
import com.windsoft.my.shop.web.api.dao.TbContentDao;
import com.windsoft.my.shop.web.api.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName TbContentServiceImpl
 * @Description
 * @Author Ricost
 * @Date 2019/11/20 8:06
 * @Version V1.0
 **/
@Service
@Transactional(readOnly = true)
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    private TbContentDao tbContentDao;

    @Override
    public List<TbContent> selectByCategoryId(Long categoryId) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setId(categoryId);
        TbContent tbContent = new TbContent();
        tbContent.setTbContentCategory(tbContentCategory);
        return tbContentDao.selectByCategoryId(tbContent);
    }
}