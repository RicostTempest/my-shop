package com.windsoft.my.shop.web.api.dao;

import com.windsoft.my.shop.domain.TbContent;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @InterfaceName TbContentDao
 * @Description
 * @Author Ricost
 * @Date 2019/11/20 7:55
 * @Version V1.0
 **/

@Repository
public interface TbContentDao {
    List<TbContent> selectByCategoryId(TbContent tbContent);

}