package com.djzhgd.project.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: DateUtil 日期转换类
 * @Author: zhangheng
 * @DATE: 2020/5/13 11:56
 * @Version 1.0
 **/
public class DateUtil {

    private static final SimpleDateFormat sftYear = new SimpleDateFormat("yyyy");
    private static final SimpleDateFormat sftYMD = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sftYMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 将日期转换成字符串 年份 YYYY
    public static String getYYYY(Date date){
        String strDate = "";
        strDate = sftYear.format(date);
        return strDate;
    }
    // 将字符串转换成日期格式 年份 YYYY
    public static Date getYYYY(String strDate) throws ParseException {
        Date date = sftYear.parse(strDate);
        return date;
    }

    // 将日期转换成字符串 年月日 YYYY-MM-DD
    public static String getYYYYMMDD(Date date){
        String strDate = "";
        strDate = sftYMD.format(date);
        return strDate;
    }
    // 将字符串转换成日期格式 年月日 yyyy-MM-dd
    public static Date getYYYYMMDD(String strDate) throws ParseException {
        Date date = sftYMD.parse(strDate);
        return date;
    }

    // 将日期转换成字符串 年月日时分秒 YYYY-MM-DD HH:mm:ss
    public static String getYYYYMMDDHHMMSS(Date date){
        String strDate = "";
        strDate = sftYMDHMS.format(date);
        return strDate;
    }
    // 将字符串转换成日期格式 年月日时分秒 yyyy-MM-dd HH:mm:ss
    public static Date getYYYYMMDDHHMMSS(String strDate) throws ParseException {
        Date date = sftYMDHMS.parse(strDate);
        return date;
    }

}
