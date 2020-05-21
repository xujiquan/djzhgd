package com.djzhgd.module.result;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * PageResultSupple
 * 分页数据包装类 (补充)
 */
@Data
public class PageResultSupple<T> implements Serializable {

    // 页码
    int page;

    // 每页显示的数量
    int limit;

    // 查询的总条数
    long count;

    // 返回码
    String code;

    // 返回的内容
    String msg;

    // 返回的数据集合
    Object data;

    // 页面传回的查询条件
    T example;

}
