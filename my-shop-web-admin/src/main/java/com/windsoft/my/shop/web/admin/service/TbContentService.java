package com.windsoft.my.shop.web.admin.service;

import com.windsoft.my.shop.commons.dto.BaseResult;
import com.windsoft.my.shop.commons.dto.PageInfo;
import com.windsoft.my.shop.domain.TbContent;

import java.util.List;

public interface TbContentService {
    List<TbContent> selectAll();
    BaseResult save(TbContent tbContent);
    void delete(Long id);
    TbContent getById(Long id);
    void update(TbContent tbContent);
    void deleteMulti(String[] ids);

    /**
     * 分页查询
     * @param
     * @return
     */
    PageInfo<TbContent> page(int start, int length, int draw, TbContent tbContent);

    /**
     * 查询总数量
     * @return
     */
    int count(TbContent tbContent);
}
