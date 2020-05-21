package com.djzhgd.module.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: BeanHelper
 * @Author: zhangheng
 * @DATE: 2020/5/21 11:04
 * @Version 1.0
 **/
public class BeanHelper {

    /**
     * 将NULL转成空字符串
     * @param bean
     * @param <T>
     */
    public static <T>  void  nullToEmpty(T bean){
        Field[] field = bean.getClass().getDeclaredFields();
        for (int j = 0; j < field.length; j++) {
            String name = field[j].getName();    //获取属性的名字
            //将属性的首字符大写，方便构造get，set方法
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            String type = field[j].getGenericType().toString();    //获取属性的类型
            if (type.equals("class java.lang.String")) {   //如果type是类类型，则前面包含"class "，后面跟类名
                try {
                    Method mGet = bean.getClass().getMethod("get" + name);
                    String value = (String) mGet.invoke(bean);    //调用getter方法获取属性值
                    if (value == null || "".equals(value)) {
                        Method mSet = bean.getClass().getMethod("set"+name, new Class[] {String.class});
                        mSet.invoke(bean,new Object[] {new String("")});
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
