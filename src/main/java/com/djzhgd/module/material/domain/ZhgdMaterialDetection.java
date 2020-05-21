package com.djzhgd.module.material.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 物料检测实体
 *
 * @Author: lgc
 * @Date: 2020/5/18 11:33
 */
@Data
public class ZhgdMaterialDetection implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer disabled;


    private Integer createUserid;


    private String createUsername;


    private Date createDatetime;


    private String createIp;


    private Integer updateUserid;


    private String updateUsername;


    private Date updateDatetime;


    private String updateIp;


    private String instockBatch;


    private String model;


    private String plate;


    private BigDecimal number;


    private String materialName;

    private String engineeringSite;

    private String checkUser;

    private String checkTime;

    private String checkImage;

    private String detectionType;

    private String detectionNo;

    private String remark;

    private String checkResult;

    private String materialNameDesc;

    private Integer count;

    private String unit;

    private Integer num;
    private Integer resultnum;
    private Integer checkNum;
    private Integer tenantId;
    private Long deptId;
}
