package com.windsoft.my.shop.web.admin.dao;

import com.windsoft.my.shop.domain.TbContentCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbContentCategoryDao {
    List<TbContentCategory> selectAll();

    /**
     * 根据父节点Id查询子节点
     * @param id
     * @return
     */
    List<TbContentCategory> selectByPid(Long id);
}
