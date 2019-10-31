package com.windsoft.my.shop.web.admin.service;

import com.windsoft.my.shop.commons.dto.BaseResult;
import com.windsoft.my.shop.domain.TbUser;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Author: Ricost
 * @Date: 2019/10/15 12:50
 */
public interface TbUserService {
    public List<TbUser> selectAll();
    public BaseResult save(TbUser tbUser);
    public void delete(Long id);
    public TbUser getById(Long id);
    public void update(TbUser tbUser);
    public List<TbUser> getByUserName(String username);
    public TbUser login(String email, String password);
    public List<TbUser> search(String keyword);

    /**
     * 批量删除
     * @param ids
     */
    public void deleteMulti(String[] ids);

    public List<TbUser> page(int start, int length);

    int count();
}
