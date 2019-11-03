package com.windsoft.my.shop.web.admin.service.test;

import com.windsoft.my.shop.domain.TbUser;
import com.windsoft.my.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author: Ricost
 * @Date: 2019/10/15 12:57
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml","classpath:spring-context-druid.xml","classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {
    @Autowired
    private TbUserService tbUserService;

    @Test
    public void testSelectAll(){
        List<TbUser> tbUsers = tbUserService.selectAll();

        for(TbUser tbUser :tbUsers){
            System.out.println(tbUser.toString());
        }
    }

    @Test
    public void testInsert(){
        TbUser tbUser = new TbUser();
        tbUser.setUsername("Wind");
        tbUser.setEmail("@foxmail.com");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());

        tbUserService.save(tbUser);
    }

    @Test
    public void testDelete(){
        tbUserService.delete(39L);
    }

    @Test
    public void testGetById(){
        System.out.println(tbUserService.getById(36L));
    }

    @Test
    public void testUpdate(){
        TbUser tbUser = tbUserService.getById(37L);
        tbUser.setEmail("@fox");
        tbUserService.update(tbUser);
    }


    @Test
    public void testLogin(){
        TbUser tbUser = tbUserService.login("ricost@foxmail.com","123456");
        if(tbUser != null){
            System.out.println(tbUser);
        }else
            System.out.println("error~~~~~~~~~~~~~~");
    }


}
