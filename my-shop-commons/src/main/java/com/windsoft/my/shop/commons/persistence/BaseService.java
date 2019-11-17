package com.windsoft.my.shop.commons.persistence;

import com.windsoft.my.shop.commons.dto.BaseResult;
import com.windsoft.my.shop.commons.dto.PageInfo;

import java.util.List;

/**
 * @InterfaceName BaseService
 * @Description
 * @Author RicostTempest
 * @Date 2019/11/17 21:10
 * @Version V1.0
 **/

public interface BaseService<T extends BaseEntity> {
    List<T> selectAll();
    BaseResult save(T entity);
    void delete(Long id);
    T getById(Long id);
    void update(T entity);
    List<T> search(String keyword);

    /**
     * 批量删除
     * @param ids
     */
    void deleteMulti(String[] ids);


    /**
     * 分页查询
     * @param start
     * @param length
     * @param draw
     * @return
     */
    PageInfo<T> page(int start, int length, int draw, T entity);

    /**
     * 查询总数目
     * @return
     */
    int count(T entity);
}