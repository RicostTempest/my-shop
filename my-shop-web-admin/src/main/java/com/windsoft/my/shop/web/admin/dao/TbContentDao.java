package com.windsoft.my.shop.web.admin.dao;

import com.windsoft.my.shop.domain.TbContent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbContentDao {
    List<TbContent> selectAll();
    void insert(TbContent tbContent);
    void delete(Long id);
    void update(TbContent tbContent);
    void deleteMulti(String[] ids);

    /**
     * 分页查询
     * @param params 两个参数，start/记录开始的位置，length/每页记录数
     * @return
     */
    List<TbContent> page(Map<String, Object> params);

    /**
     * 查询总数量
     * @return
     */
    int count(TbContent tbContent);
}
