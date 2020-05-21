package com.djzhgd.module.contract.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ZhgdDisputeResolution {
    private Integer id;

    private Integer deptId;

    private Integer tenantId;

    private Short disabled;

    private Integer createUserid;

    private String createUsername;

    private String createIp;

    private Date createDatetime;

    private Integer updateUserid;

    private String updateUsername;

    private String updateIp;

    private Date updateDatetime;

    private String title;

    private String content;

    private String treatmentPlan;

    private String handler;

    private Date handleTime;

    private Short status;

}