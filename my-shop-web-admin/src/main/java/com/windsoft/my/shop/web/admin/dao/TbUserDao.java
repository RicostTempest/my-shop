package com.windsoft.my.shop.web.admin.dao;

import com.windsoft.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 查询用户表全部信息
 * @Author: Ricost
 * @Date: 2019/10/15 12:48
 */
@Repository
public interface TbUserDao {
    List<TbUser> selectAll();
    void insert(TbUser tbUser);
    void delete(Long id);
    TbUser getById(Long id);
    void update(TbUser tbUser);
    List<TbUser> getByUserName(String username);
    TbUser getByEmail(String email);
    List<TbUser>search(TbUser tbUser);
    void deleteMulti(String[] ids);

    /**
     * 分页查询
     * @param params,两个参数，start/记录开始的位置，length/每页记录数
     * @return
     */
    List<TbUser> page(Map<String, Object> params);

    /**
     * 查询总数量
     * @return
     */
    int count();
}
