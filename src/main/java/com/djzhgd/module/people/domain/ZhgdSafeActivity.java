package com.djzhgd.module.people.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("ZHGD_SAFE_ACTIVITY")
public class ZhgdSafeActivity {
    private Integer id;

    private Integer deptId;

    private Integer tenantId;

    private String deptName;

    private Integer type;

    private String technicalType;

    private String name;

    private String organizer;

    private String site;

    private Date startTime;

    private String speaker;

    private String recorder;

    private String way;

    private String period;

    private String joinDepartment;

    private Integer status;

    private String archiveTime;

    private String content;

    private String filePath;

    private String fileName;

    private String zhgdDeptCode;

    private Integer zhgdDeptId;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer disabled;

    private Long createUserid;

    private String createUsername;

    @TableField(fill = FieldFill.INSERT)
    private Date createDatetime;

    private String createIp;

    private Long updateUserid;

    private String updateUsername;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDatetime;

    private String updateIp;

    private Integer version;


}