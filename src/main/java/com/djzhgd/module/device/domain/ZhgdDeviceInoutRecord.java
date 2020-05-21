package com.djzhgd.module.device.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ZhgdDeviceInoutRecord {
    private Integer id;

    private Integer deptId;

    private Integer tenantId;

    private Integer disabled;

    private Integer createUserid;

    private String createUsername;

    private String createIp;

    private Date createDatetime;

    private Integer updateUserid;

    private String updateUsername;

    private String updateIp;

    private Date updateDatetime;

    private String recordType;

    private String orgId;

    private String recordTime;

    private Integer deviceId;


}