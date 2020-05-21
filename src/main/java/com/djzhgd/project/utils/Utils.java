package com.djzhgd.project.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @PROJECT_NAME: djzhgd 智慧咨询项目
 * @ClassName: Utils
 * @Author: zhangheng
 * @DATE: 2020/3/23 10:11
 * @Version 1.0
 **/
public class Utils {

    private static SimpleDateFormat aftYMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat aftYMD = new SimpleDateFormat("yyyy-MM-dd");

    // String字符串转String,如果参数为空或者null,则返回""
    public static String othToString(String value){
        String strVal = "";
        if(value != null && !"".equals(value.trim())){
            strVal = value;
        }
        return strVal;
    }
    // Long转String,如果参数为null,则返回""
    public static String othToString(Long value){
        String strVal = "";
        if(value != null){
            strVal = String.valueOf(value);
        }
        return strVal;
    }
    // Integer转String,如果参数为null,则返回""
    public static String othToString(Integer value){
        String strVal = "";
        if(value != null){
            strVal = String.valueOf(value);
        }
        return strVal;
    }
    // Double转String,如果参数为null,则返回""
    public static String othToString(Double value){
        String strVal = "";
        if(value != null){
            strVal = String.valueOf(value);
        }
        return strVal;
    }
    // Date转String,如果参数为null,则返回""
    public static String othToString(Date value){
        String strVal = "";
        if(value != null){
            strVal = aftYMDHMS.format(value);
        }
        return strVal;
    }

    // 生成当天日期
    public static String getToday(){
        Date date = new Date();
        String creatrDate = aftYMD.format(date);
        return creatrDate;
    }

    // 判断字符串是否为空
    public static boolean isNotNull(String value){
        boolean strVal = false;
        if(value != null && !"".equals(value.trim())){
            strVal = true;
        }
        return strVal;
    }

    /**
     * 获取图片宽度
     * @param file  图片文件
     * @return 宽度
     */
    public static int getImgWidth(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            ret = src.getWidth(null); // 得到源图宽
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }


    /**
     * 获取图片高度
     * @param file  图片文件
     * @return 高度
     */
    public static int getImgHeight(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            ret = src.getHeight(null); // 得到源图高
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }


}
