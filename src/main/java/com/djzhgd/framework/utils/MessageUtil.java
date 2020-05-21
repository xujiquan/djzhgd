package com.djzhgd.framework.utils;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MessageUtil {

    public static HttpClientResult sendMess(String mobiles, String conment) {
    	Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json;charset=utf-8");
        headers.put("source", "gkxt-api");
        headers.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOjE1NTY2MDAyNzYwODQsInVpZCI6MSwiaWF0IjoxNTU2NTEzODc2MDg0fQ.J034Wc5GgMTbLOsWGEp3qGnxSjf_IZHQZmv1kf2Gkfk");
        //phone  多个手机号用英文","分隔
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,String> mapNew = new HashMap<String,String>();
        map.put("phone", "18205184205");//测试手机号
//        map.put("phone", mobiles);//正式手机号 上线时放开 TODO
        map.put("content", conment);//发送内容
        map.put("proName", "[江苏东交]");//短信签名
        for (String string : map.keySet()) {
            mapNew.put(string, map.get(string).toString());
        }
        JSONObject jsonObject=JSONObject.fromObject(mapNew);
        String params = jsonObject.toString();
        HttpClientResult result = new HttpClientResult();
        System.out.println(params);
        try {
			result = HttpClientUtils.doPostForJson("http://new.djzhgd.com:10724/gkxt-api/api/pub/sendSms", headers , params);
			System.out.println(result);
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
    }
    
    
}
