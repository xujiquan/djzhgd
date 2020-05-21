package com.djzhgd.module.device.domain;

import lombok.Data;

import java.util.Date;
@Data
public class ZhgdDevice {
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

    private String orgId;

    private String deviceTypeCode;

    private String useLocationCode;

    private String number;

    private String model;

    private String catgegroy;

    private String isRent;

    private String inTime;

    private String outTime;

    private String inOutStatus;

    private String remark;

    private String acceptStatus;

    private String attachmentId;

    private String imageId;

    private String licensePlate;

    private Integer zhgdDeptId;

    private String zhgdDeptName;

    private Integer zhgdUserId;

    private String qrCodePicture;


}