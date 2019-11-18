package com.windsoft.my.shop.web.admin.abstracts;

import com.windsoft.my.shop.commons.dto.BaseResult;
import com.windsoft.my.shop.commons.dto.PageInfo;
import com.windsoft.my.shop.commons.persistence.BaseDao;
import com.windsoft.my.shop.commons.persistence.BaseEntity;
import com.windsoft.my.shop.commons.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @ClassName AbstractBaseServiceImpl
 * @Description
 * @Author Ricost
 * @Date 2019/11/18 9:27
 * @Version V1.0
 **/

public abstract class AbstractBaseServiceImpl<T extends BaseEntity, D extends BaseDao<T>> implements BaseService<T> {

    //错误原因：spring的内部注入功能无法识别未被编译的泛型标示，作为警告存在，无错误，可忽略。
    @Autowired
    private D dao;

    /**
     * 使用GET方法使之类可以直接使用dao的方法，避免不通用方法重复定义使用
     * @return
     */
    public D getDao() {
        return dao;
    }

    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public T getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public void update(T entity) {
        dao.update(entity);
    }

    /**
     * 多项删除
     * @param ids
     */
    @Override
    public void deleteMulti(String[] ids) {
        dao.deleteMulti(ids);
    }

    /**
     * 计算条目
     * @param entity
     * @return
     */
    @Override
    public int count(T entity) {
        return dao.count(entity);
    }
}