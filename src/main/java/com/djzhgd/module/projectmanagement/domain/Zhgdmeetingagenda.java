package com.djzhgd.module.projectmanagement.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@Data
@TableName("ZHGD_MEETING_AGENDA")
public class Zhgdmeetingagenda{
    private Long id;

    private Long deptId;

    private Long tenantId;

    private String leader;

    private String filePath;

    private String node;

    private String title;

    private String parentId;

    private String leaderId;
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer disabled;

    private String createUserid;

    private String createUsername;

    private String createIp;
    @TableField(fill = FieldFill.INSERT)
    private Date createDatetime;

    private String updateUserid;

    private String updateUsername;

    private String updateIp;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDatetime;

    private String content;

    private String zhgdDeptCode;
    private String zhgdDeptName;
    private String zhgdDeptId;


}
