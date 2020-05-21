package com.djzhgd.module.people.domain;

import lombok.Data;

import java.util.Date;
@Data
public class ZhgdAttendance {
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

    private Integer userId;

    private Integer checkId;

    private String checkName;

    private Date checkTime;

    private String checkSn;

    private String filePath;

    private Integer dataType;

    private String companyName;

    private String postName;

    private Integer zhgdUserId;

    private String remark;

    private String photoPath;

    private String fileName;


}