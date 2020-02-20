package com.learn.demo.gis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class LonLatQueryDemo {
    public static void main(String[] args) {
        String uri = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=您的ak&output=json&coordtype=wgs84ll&location=26.5928,106.697";
        BufferedReader in = null;
        try {
            // 将地址转换成utf-8的16进制
            URL tirc = new URL("http://api.map.baidu.com/reverse_geocoding/v3/?ak=MnSbbx047qRbCed73suDHuDQxUe1vi1y&output=json&coordtype=wgs84ll&location=26.5928,106.697");
            in = new BufferedReader(new InputStreamReader(tirc.openStream(), "UTF-8"));
            String res;
            StringBuilder sb = new StringBuilder("");
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }
            String jsonStr = sb.toString();
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            JSONObject result = jsonObject.getJSONObject("result");
            String formatted_address = result.getString("formatted_address");
            JSONObject addressComponent = result.getJSONObject("addressComponent");
            String province = addressComponent.getString("province");
            String city = addressComponent.getString("city");
            String district = addressComponent.getString("district");
            System.out.println(district+","+formatted_address);
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
