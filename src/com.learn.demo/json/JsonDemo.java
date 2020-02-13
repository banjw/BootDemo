package com.learn.demo.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonDemo {
    public static void main(String[] args) {
        String jsonStr = "{\"status\":0,\"result\":{\"location\":{\"lng\":106.7071935262671,\"lat\":26.595402294167639},\"formatted_address\":\"贵州省贵阳市云岩区北新区路143号\",\"business\":\"北京路,黔灵公园,威清路\",\"addressComponent\":{\"country\":\"中国\",\"country_code\":0,\"country_code_iso\":\"CHN\",\"country_code_iso2\":\"CN\",\"province\":\"贵州省\",\"city\":\"贵阳市\",\"city_level\":2,\"district\":\"云岩区\",\"town\":\"\",\"town_code\":\"\",\"adcode\":\"520103\",\"street\":\"北新区路\",\"street_number\":\"143号\",\"direction\":\"附近\",\"distance\":\"9\"},\"pois\":[],\"roads\":[],\"poiRegions\":[],\"sematic_description\":\"\",\"cityCode\":146}}";
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        JSONObject result = jsonObject.getJSONObject("result");
        String formatted_address = result.getString("formatted_address");
        JSONObject addressComponent = result.getJSONObject("addressComponent");
        String province = addressComponent.getString("province");
        String city = addressComponent.getString("city");
        String district = addressComponent.getString("district");
        System.out.println("province="+province);
        System.out.println("city="+city);
        System.out.println("district="+district);
        System.out.println("formatted_address="+formatted_address);
    }
}
