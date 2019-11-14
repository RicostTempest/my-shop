package com.windsoft.my.shop.web.admin.service;

import com.windsoft.my.shop.domain.TbContentCategory;

import java.util.List;

public interface TbContentCategoryService {
    public List<TbContentCategory> selectAll();

    /**
     * 根据父节点Id查询子节点
     * @param id
     * @return
     */
    List<TbContentCategory> selectByPid(Long id);
}
