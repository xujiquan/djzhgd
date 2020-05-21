package com.djzhgd.module.utils;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: UtilCommon 公共方法类
 * @Author: zhangheng
 * @DATE: 2020/5/19 10:11
 * @Version 1.0
 **/
public class UtilCommon {

    /**
     * 校验字符串是否为NULL或者空,如果为NULL或者空,返回false
     * @param strValue
     * @return
     */
    public static boolean excludeEmpty(String strValue){
        boolean value = false;
        if(strValue != null && !"".equals(strValue.trim())){
            value = true;
        }
        return value;
    }
}
