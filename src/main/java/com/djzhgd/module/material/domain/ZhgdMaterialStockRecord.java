package com.djzhgd.module.material.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 物料入库记录实体
 * @Author: lgc
 * @Date: 2020/5/18 11:39
 */
@Data
public class ZhgdMaterialStockRecord  implements Serializable {

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

    private String model;

    private String materialName;

    private String materialType;

    private String bidsName;

    private String bidsNameDesc;

    private double number;

    private String unit;

    private double sumGj;

    private double sumSn;

    private double sumSs;

    private double sumHs;

    private String materialNameDesc;

    private String materialTypeDesc;

    private Double sumNumber;
    private Integer tenantId;
    private Long deptId;
}
