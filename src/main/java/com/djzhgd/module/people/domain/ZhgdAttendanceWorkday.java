package com.djzhgd.module.people.domain;

import lombok.Data;

import java.util.Date;
@Data
public class ZhgdAttendanceWorkday {
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

    private Integer workYear;

    private Integer workMonth;

    private Integer workDay;

    private Date workDate;

    private Integer workType;

    private String workName;

    private String workCode;

    private Integer dataScope;


}