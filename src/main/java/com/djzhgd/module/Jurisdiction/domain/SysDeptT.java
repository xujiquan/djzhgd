package com.djzhgd.module.Jurisdiction.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: SysDeptT
 * @Author: zhangheng
 * @DATE: 2020/5/19 11:23
 * @Version 1.0
 **/
@Data
@TableName("sys_dept")
public class SysDeptT implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 部门ID */
    @TableId("dept_id")
    private Long deptId;

    /** 父部门ID */
    private Long parentId;

    /** 祖级列表 */
    private String ancestors;

    /** 部门名称 */
    private String deptName;

    /** 显示顺序 */
    private String orderNum;

    /** 负责人 */
    private String leader;

    /** 联系电话 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 部门状态:0正常,1停用 */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    // 部门类型
    private String deptType;
    // 部门编码
    private String deptCode;


}
