package com.windsoft.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName UploadController
 * @Description
 * @Author RicostTempest
 * @Date 2019/11/17 13:07
 * @Version V1.0
 **/
@Controller
public class UploadController {

    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Map<String, Object> upload(MultipartFile dropFile,
                                      HttpServletRequest request){
        System.out.println(dropFile.getOriginalFilename());
        System.out.println(request.getSession().getServletContext().getRealPath("/static/upload"));
        Map<String, Object> result = new HashMap<>();

        //文件存放路径
        String filePath = request.getSession().getServletContext().getRealPath("/static/upload");
        //文件名
        String fileName = dropFile.getOriginalFilename();
        // 获取文件后缀
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());

        File file = new File(filePath);
        //判断文件路径是否存在
        if(!file.exists()){
            file.mkdir();
        }

        // 重新设置文件名为 UUID，以确保唯一
        file = new File(filePath, UUID.randomUUID() + fileSuffix);

        try {
            dropFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 返回 JSON 数据，这里只带入了文件名
        result.put("fileName", file.getName());

        return result;
    }
}
