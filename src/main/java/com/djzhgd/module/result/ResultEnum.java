package com.djzhgd.module.result;

import lombok.Getter;

/**
 * @PROJECT_NAME: dj_zhgd 东交智慧品控项目
 * @ClassName: ResultEnum 前后端数据交互返回成功、失败的枚举类
 * @Author: zhangheng
 * @DATE: 2020/5/13 10:51
 * @Version 1.0
 **/
@Getter
public enum ResultEnum {

    /**
     * 系统本身web接口调用返回的数据
     */
    RESULT_OK("200", "请求成功"),
    RESULT_ERROR("201", "请求失败"),
    RESULT_INSERT_OK("200", "新增数据成功"),
    RESULT_INSERT_ERROR("201", "新增数据失败"),
    RESULT_UPDATE_OK("200", "修改数据成功"),
    RESULT_UPDATE_VERSION("202", "数据已被修改"),
    RESULT_UPDATE_ERROR("201", "修改数据失败"),
    RESULT_DELETE_OK("200", "删除数据成功"),
    RESULT_DELETE_ERROR("201", "删除数据失败");
    private String code;
    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
