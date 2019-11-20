package com.windsoft.my.shop.web.api.service;

import com.windsoft.my.shop.domain.TbContent;

import java.util.List;

/**
 * @InterfaceName TbContentService
 * @Description
 * @Author Ricost
 * @Date 2019/11/20 8:06
 * @Version V1.0
 **/

public interface TbContentService {
    List<TbContent> selectByCategoryId(Long categoryId);

}