package com.djzhgd.module.projectmanagement.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("ZHGD_PROJECT_INTRODUCTION")
public class ZhgdProjectIntroduction {
    private Integer id;

    private Integer deptId;

    private Integer tenantId;
    private Integer userId;

    private String projectIntroduction;

    private String addUserId;

    private String addUsername;

    private Date time;

    private Integer disabled;

    private Integer createUserid;

    private String createUsername;

    private Date createDatetime;

    private String createIp;

    private Integer updateUserid;

    private String updateUsername;

    private Date updateDatetime;

    private String updateIp;




}