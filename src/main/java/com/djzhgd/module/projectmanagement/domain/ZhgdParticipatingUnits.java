package com.djzhgd.module.projectmanagement.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("ZHGD_PARTICIPATING_UNITS")
public class ZhgdParticipatingUnits {
    private Integer id;

    private Integer deptId;

    private Integer tenantId;

    private String unitName;

    private Integer unitType;

    private String unitDuty;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer disabled;

    private Integer createUserid;

    private String createUsername;

    @TableField(fill = FieldFill.INSERT)
    private Date createDatetime;

    private String createIp;

    private Integer updateUserid;

    private String updateUsername;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDatetime;

    private String updateIp;

    private Integer version;


}