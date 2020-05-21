package com.djzhgd.module.people.domain;

import lombok.Data;

import java.util.Date;
@Data
public class ZhgdAttendanceData {
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

    private String checkData;

    private String checkSn;


}