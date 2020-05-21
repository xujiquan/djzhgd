package com.djzhgd.module.Jurisdiction.vo;

import lombok.Data;

import java.util.List;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: DeptVo
 * @Author: zhangheng
 * @DATE: 2020/5/18 16:11
 * @Version 1.0
 **/
@Data
public class DeptVo {

    // 返回的类型
    private String deptType;
    // 部门ID（平台ID，项目ID，标段ID）
    private String deptId;
    // 部门编码（平台编码，项目编码，标段编码）
    private String deptCode;
    // 部门名称（平台名称，项目名称，标段名称）
    private String deptName;

}
