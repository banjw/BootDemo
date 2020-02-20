package com.learn.demo.gis;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class AddressQueryDemo {
    public static void main(String[] args) {
        String uri = "http://api.map.baidu.com/geocoding/v3/?address=贵州省贵阳市白云区云城尚品&output=json&ak=pgaartUoZAe7DanNFlG5MZDfqecY9UTQ";
        BufferedReader in = null;
        try {
            // 将地址转换成utf-8的16进制
            String address = URLEncoder.encode("贵州省贵阳市白云区云城尚品", "UTF-8");
            URL tirc = new URL("http://api.map.baidu.com/geocoding/v3/?address=" + address + "&output=json&ak=pgaartUoZAe7DanNFlG5MZDfqecY9UTQ");
            in = new BufferedReader(new InputStreamReader(tirc.openStream(), "UTF-8"));
            String res;
            StringBuilder sb = new StringBuilder("");
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }
            String str = sb.toString();
            Map map = null;
            if (!StringUtils.isEmpty(str)) {
                int lngStart = str.indexOf("lng\":");
                int lngEnd = str.indexOf(",\"lat");
                int latEnd = str.indexOf("},\"precise");
                if (lngStart > 0 && lngEnd > 0 && latEnd > 0) {
                    String lng = str.substring(lngStart + 5, lngEnd);
                    String lat = str.substring(lngEnd + 7, latEnd);
                    map = new HashMap();
                    map.put("lng", lng);
                    map.put("lat", lat);
                    System.out.println("lng=" + lng);
                    System.out.println("lat=" + lat);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
