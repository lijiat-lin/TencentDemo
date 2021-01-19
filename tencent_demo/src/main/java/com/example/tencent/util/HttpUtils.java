package com.example.tencent.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.http.RequestEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @program: tencent_demo
 * @description: Http工具类
 * @author: zhangjialin
 * @create: 2021-01-19 17:20
 */
public class HttpUtils {

    /**
     * 使用JDK原生提供的net
     * @param urlParam
     * @param requestType
     * @return
     */
    public static String sendRequestHttpUrl(String urlParam ,String requestType){
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = null;

        try{
            URL url = new URL(urlParam);
            //得到连接对象
            connection = (HttpURLConnection) url.openConnection();
            //设置请求类型
            connection.setRequestMethod(requestType);
            //设置请求需要返回的数据类型和字符集类型
            connection.setRequestProperty("Content-type","application/json;charset=GBK");
            //允许写出
            connection.setDoOutput(true);
            //允许写入
            connection.setDoInput(true);
            //不使用缓存
            connection.setUseCaches(false);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){
                //得到相应流
                InputStream inputStream = connection.getInputStream();
                //将响应流转换成字符串
                resultBuffer = new StringBuffer();
                String line;
                reader = new BufferedReader(new InputStreamReader(inputStream,"GBK"));
                while ((line = reader.readLine())!=null){
                    resultBuffer.append(line);
                }
                return resultBuffer.toString();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * HttpClient 的POST请求
     * @param urlParam
     * @return
     * @throws IOException
     */
    public static String sendPost(String urlParam) throws IOException {
        // 创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建post请求方法实例对象
        PostMethod postMethod = new PostMethod(urlParam);
        // 设置post请求超时时间
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        postMethod.addRequestHeader("Content-Type", "application/json");

        httpClient.executeMethod(postMethod);

        String result = postMethod.getResponseBodyAsString();
        postMethod.releaseConnection();
        return result;
    }

    /**
     * HttpClient  的Get请求
     * @param urlParam
     * @return
     * @throws IOException
     */
    public static String sendGet(String urlParam) throws  IOException {
        // 创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建GET请求方法实例对象
        GetMethod getMethod = new GetMethod(urlParam);
        // 设置post请求超时时间
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        getMethod.addRequestHeader("Content-Type", "application/json");

        httpClient.executeMethod(getMethod);

        String result = getMethod.getResponseBodyAsString();
        getMethod.releaseConnection();
        return result;
    }




}
