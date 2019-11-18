package com.windsoft.my.shop.web.admin.abstracts;

import com.windsoft.my.shop.commons.persistence.BaseEntity;
import com.windsoft.my.shop.commons.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName AbstractBaseController
 * @Description
 * @Author RicostTempest
 * @Date 2019/11/18 12:31
 * @Version V1.0
 **/

public abstract class AbstractBaseController<T extends BaseEntity, S extends BaseService<T>> {
    /**
     * 注入业务逻辑层
     */
    @Autowired
    protected S service;

    /**
     * 跳转到列表页
     * @return
     */
    public abstract String list();
}
