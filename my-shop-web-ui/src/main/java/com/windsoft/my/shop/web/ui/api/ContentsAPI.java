package com.windsoft.my.shop.web.ui.api;

import com.windsoft.my.shop.commons.utils.HttpClientUtils;
import com.windsoft.my.shop.commons.utils.MapperUtils;
import com.windsoft.my.shop.web.ui.dto.TbContent;

import java.util.List;

/**
 * @ClassName ContentsAPI
 * @Description 内容管理接口
 * @Author RicostTempest
 * @Date 2019/11/21 20:35
 * @Version V1.0
 **/

public class ContentsAPI {
    public static List<TbContent> ppt(){
        String result =  HttpClientUtils.doGet(API.API_CONTENTS + "89");
        List<TbContent> tbContents = null;
        try {
            tbContents = MapperUtils.json2ListByTree(result,"data",TbContent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbContents;
    }
}
