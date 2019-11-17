package com.windsoft.my.shop.commons.persistence;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName BaseDao
 * @Description 所有数据访问层的基类
 * @Author RicostTempest
 * @Date 2019/11/17 21:06
 * @Version V1.0
 **/

public interface BaseDao<T extends BaseEntity> {
    List<T> selectAll();
    void insert(T entity);
    void delete(Long id);
    T getById(Long id);
    void update(T entity);
    List<T>search(T entity);
    void deleteMulti(String[] ids);

    /**
     * 分页查询
     * @param params 两个参数，start/记录开始的位置，length/每页记录数
     * @return
     */
    List<T> page(Map<String, Object> params);

    /**
     * 查询总数量
     * @return
     */
    int count(T entity);
}