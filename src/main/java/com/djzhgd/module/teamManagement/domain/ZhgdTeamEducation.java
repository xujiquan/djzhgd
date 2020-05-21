package com.djzhgd.module.teamManagement.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("zhgd_team_education")
public class ZhgdTeamEducation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long deptId;

    private Long tenantId;

    private String meetName;

    private String teamCode;

    private String teamLeader;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date meetTime;

    private String videoUrl;

    private Integer zhgdUserId;

    private String videoName;

    private String fileName;

    @TableField(fill= FieldFill.INSERT)
    private Integer disabled;

    private Long createUserid;

    private String createUsername;

    @TableField(fill= FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDatetime;

    private String createIp;

    private Long updateUserid;

    private String updateUsername;

    @TableField(fill= FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateDatetime;

    private String updateIp;

}