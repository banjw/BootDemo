package com.learn.demo.http;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HttpClientDemo {
    public static void main(String[] args) throws IOException {
        String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=MnSbbx047qRbCed73suDHuDQxUe1vi1y&output=json&coordtype=wgs84ll&location=26.5928,106.697";
        String http = getHttp(url);
        System.out.println(http);
    }

    public static String getHttp(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //响应体
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        System.out.println("------------------");
        System.out.println(body);
        return body;
    }

    public static void postDemo() throws IOException {
        String url = "http://httpbin.org/post";
        HttpPost httpPost = new HttpPost(url);

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", "vip"));
        params.add(new BasicNameValuePair("password", "secret"));
        HttpEntity postEntity = new UrlEncodedFormEntity(params);
        httpPost.setEntity(postEntity);

        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

        CloseableHttpClient httpClient = HttpClients.createDefault();

        CloseableHttpResponse response = httpClient.execute(httpPost);

        HttpEntity entity = response.getEntity();

        String body = EntityUtils.toString(entity);
        System.out.println("------------------");
        System.out.println(body);
    }


    public static void getDemo() throws IOException {
        String url = "http://www.baidu.com";
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //状态码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("------------------");
        System.out.println(response.getStatusLine());
        //响应头
        Header[] allHeaders = response.getAllHeaders();
        /*for (Header allHeader : allHeaders) {
            System.out.println(allHeader.getName() + ":" + allHeader.getValue());
        }*/
        System.out.println("------------------");
        Arrays.stream(allHeaders).forEach(a -> System.out.println(a.getName() + ":" + a.getValue()));
        //响应体
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        System.out.println("------------------");
        System.out.println(body);
    }
}
