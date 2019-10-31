package com.windsoft.my.shop.web.admin.service;

import com.windsoft.my.shop.commons.dto.BaseResult;
import com.windsoft.my.shop.commons.dto.PageInfo;
import com.windsoft.my.shop.domain.TbUser;

import java.util.List;

/**
 * @Author: Ricost
 * @Date: 2019/10/15 12:50
 */
public interface TbUserService {
    List<TbUser> selectAll();
    BaseResult save(TbUser tbUser);
    void delete(Long id);
    TbUser getById(Long id);
    void update(TbUser tbUser);
    List<TbUser> getByUserName(String username);
    TbUser login(String email, String password);
    List<TbUser> search(String keyword);

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
    PageInfo<TbUser> page(int start, int length, int draw, TbUser tbUser);

    /**
     * 查询总数目
     * @return
     */
    int count(TbUser tbUser);
}
