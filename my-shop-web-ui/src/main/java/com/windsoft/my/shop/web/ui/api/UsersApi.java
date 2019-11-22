package com.windsoft.my.shop.web.ui.api;

import com.windsoft.my.shop.commons.utils.HttpClientUtils;
import com.windsoft.my.shop.commons.utils.MapperUtils;
import com.windsoft.my.shop.web.ui.dto.TbUser;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UsersApi
 * @Description 会员管理接口
 * @Author RicostTempest
 * @Date 2019/11/22 22:12
 * @Version V1.0
 **/

public class UsersApi {
    public static TbUser login(TbUser tbUser) throws Exception{
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", tbUser.getUsername()));
        params.add(new BasicNameValuePair("email", tbUser.getUsername()));
        params.add(new BasicNameValuePair("phone", tbUser.getUsername()));
        params.add(new BasicNameValuePair("password", tbUser.getPassword()));

        String json = HttpClientUtils.doPost(API.API_USERS_LOGIN, params.toArray(new BasicNameValuePair[params.size()]));
        TbUser user = MapperUtils.json2pojoByTree(json, "data", TbUser.class);

        return user;
    }
}
