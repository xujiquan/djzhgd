package com.djzhgd.module.projectmanagement.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ZhgdAnnouncement {
    private Integer id;

    private Integer deptId;

    private Integer tenantId;

    private String annTitle;

    private String annDate;

    private Integer ifDisplayHome;

    private String annFile;

    private String otherUsername;

    private String annImg;

    private Integer disabled;

    private Integer createUserid;

    private String createUsername;

    private String createIp;

    private Date createDatetime;

    private Integer updateUserid;

    private String updateUsername;

    private String updateIp;

    private Date updateDatetime;

    private String annContent;


}