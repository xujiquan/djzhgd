package com.djzhgd.module.result;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * PageResult
 * 分页数据包装类
 * @author zhangh
 * @data 2019/12/4
 */
@Data
public class PageResult<T> implements Serializable {

    // 页码
    int page = 1;

    // 每页显示的数量
    int limit = 10;

    // 查询的总条数
    long count;

    // 返回码
    String code;

    // 返回的内容
    String msg;

    // 返回的数据集合
    List<T> data;

    // 页面传回的查询条件
    T example;

}
