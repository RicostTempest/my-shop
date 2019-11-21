package com.windsoft.my.shop.commons.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

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
    public static String doGet(String url){
        return createHttpClient(url, GET,null);
    }

    /**
     * Get请求
     * @param url
     * @param cookie
     * @return
     */
    public static String doGet(String url, String cookie){
        return createHttpClient(url, GET,cookie);
    }

    /**
     * Post 请求
     * @param url 请求地址
     * @param params 请求参数（可选）
     * @return
     */
    public static String doPost(String url, BasicNameValuePair... params){
        return createHttpClient(url,POST, null, params);
    }

    /**
     * Post请求
     * @param url
     * @param cookie
     * @param params
     * @return
     */
    public static String doPost(String url, String cookie, BasicNameValuePair... params){
        return createHttpClient(url,POST, cookie, params);
    }

    /**
     * 创建请求
     * @param url 请求地址
     * @param requestMethod 方式
     * @param cookie Cookie
     * @param params 参数
     * @return
     */
    public static String createHttpClient(String url, String requestMethod, String cookie,BasicNameValuePair... params){
        //创建链接
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //请求方式
        HttpPost httpPost = null;
        HttpGet httpGet = null;

        //响应
        CloseableHttpResponse httpResponse = null;

        String result = null;

        try{
            if(GET.equals(requestMethod)){
                httpGet = new HttpGet(url);
                httpGet.setHeader("Connection",REQUEST_HEADER_CONNECTION);
                httpGet.setHeader("User-Agent",REQUEST_HEADER_USER_AGENT);
                httpGet.setHeader("Cookie","cookie");

                httpResponse = httpClient.execute(httpGet);
            }else if(POST.equals(requestMethod)){
                httpPost = new HttpPost(url);
                httpPost.setHeader("Connection",REQUEST_HEADER_CONNECTION);
                httpPost.setHeader("User-Agent",REQUEST_HEADER_USER_AGENT);
                httpPost.setHeader("Cookie","cookie");

                if(params != null && params.length>0){
                    httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(params),"UTF-8"));
                    httpResponse = httpClient.execute(httpPost);
                }
            }

            HttpEntity httpEntity = httpResponse.getEntity();

            result = EntityUtils.toString(httpEntity);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (httpClient!=null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
