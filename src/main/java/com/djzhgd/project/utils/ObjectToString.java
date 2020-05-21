package com.djzhgd.project.utils;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: ObjectToString 其它类型转String
 * @Author: zhangheng
 * @DATE: 2020/5/13 11:58
 * @Version 1.0
 **/
public class ObjectToString {

    // String 转String,(如果参数不为空且不为NULL,则返回参数,否则返回空 "")
    public static String othTrunString(String strValue){
        String value = "";
        if(strValue != null && !"".equals(strValue.trim())){
            value = strValue;
        }
        return value;
    }
    // Long   转成 String(如果参数转String后不为空且不为NULL,则返回参数,否则返回空 "")
    public static String othTrunString(Long strValue){
        String value = "";
        if(strValue != null){
            value = strValue.toString();
        }
        return value;
    }
    // Integer 转成 String(如果参数转String后不为空且不为NULL,则返回参数,否则返回空 "")
    public static String othTrunString(Integer strValue){
        String value = "";
        if(strValue != null){
            value = strValue.toString();
        }
        return value;
    }
    // Double 转成 String(如果参数转String后不为空且不为NULL,则返回参数,否则返回空 "")
    public static String othTrunString(Double strValue){
        String value = "";
        if(strValue != null){
            value = strValue.toString();
        }
        return value;
    }
    // 判断字符串是否为null或者空，不为null且不为空返回true
    public static boolean strIsNotNull(String strValue){
        boolean value = false;
        if(strValue != null && !"".equals(strValue.trim())){
            value = true;
        }
        return value;
    }

}
