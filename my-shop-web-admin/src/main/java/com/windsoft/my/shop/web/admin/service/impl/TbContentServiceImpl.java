package com.windsoft.my.shop.web.admin.service.impl;

import com.windsoft.my.shop.commons.dto.BaseResult;
import com.windsoft.my.shop.commons.validator.BeanValidator;
import com.windsoft.my.shop.domain.TbContent;
import com.windsoft.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.windsoft.my.shop.web.admin.dao.TbContentDao;
import com.windsoft.my.shop.web.admin.service.TbContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true) //事务开启，全类方法只读数据库
public class TbContentServiceImpl extends AbstractBaseServiceImpl<TbContent, TbContentDao> implements TbContentService {

    @Override
    @Transactional(readOnly = false)  //事务开启，该方法可写数据库
    public BaseResult save(TbContent tbContent) {
        String validator = BeanValidator.validator(tbContent);
        if(validator != null){
            return BaseResult.fail(validator);
        }
        //通过验证
        else{
            tbContent.setUpdated(new Date());
            //增加用户
            if(tbContent.getId() == null){
                tbContent.setCreated(new Date());
                getDao().insert(tbContent);
            }
            //编辑用户
            else {
                update(tbContent);
            }

            return BaseResult.success("保存内容信息成功");
        }
    }

    @Override
    public List<TbContent> search(String keyword) {
        return null;
    }
}
