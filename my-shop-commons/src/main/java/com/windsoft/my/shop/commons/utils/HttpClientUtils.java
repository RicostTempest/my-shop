package com.windsoft.my.shop.commons.utils;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @ClassName HttpClientUtils
 * @Description
 * @Author RicostTempest
 * @Date 2019/11/21 12:41
 * @Version V1.0
 **/

public class HttpClientUtils {

    public static final String GET = "get";
    public static final String POST = "post";
    public static final String REQUEST_HEADER_CONNECTION = "keep-alive";
    public static final String REQUEST_HEADER_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36";

    /**
     * Get  请求
     * @return
     */
    public static String doGet(){
        return null;
    }

    /**
     * Post 请求
     * @return
     */
    public static String doPost(){
        return null;
    }

    public static CloseableHttpClient createHttpClient(String requestMethod){
        //创建链接
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = null;
        HttpGet httpGet = null;

        if(GET.equals(requestMethod)){
            httpGet = new HttpGet();
            httpGet.setHeader("Connection",REQUEST_HEADER_CONNECTION);
            httpGet.setHeader("User-Agent",REQUEST_HEADER_USER_AGENT);
            httpGet.setHeader("Cookie","");
        }else if(POST.equals(requestMethod)){
            httpPost = new HttpPost();
            httpPost.setHeader("Connection",REQUEST_HEADER_CONNECTION);
            httpPost.setHeader("User-Agent",REQUEST_HEADER_USER_AGENT);
            httpPost.setHeader("Cookie","");
        }

        return httpClient;
    }
}
