package com.djzhgd.module.material.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 物料入库类
 *
 * @Author: lgc
 * @Date: 2020/5/18 11:17
 */
@Data
@Getter
@Setter
public class ZhgdMaterialInstock implements Serializable {
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

    private String batch;

    private String model;

    private String plate;

    private double number;

    private String materialName;

    private String materialType;

    private String unit;

    private String bidsName;


    private String supplier;


    private String produceAddress;


    private String carNumber;

    private String usedPart;


    private String instockTime;


    private String instockUser;


    private String checkUser;


    private String instockWord;


    private String remark;


    private Integer checkNum;


    private Integer taskNode;


    private String errorRemark;

    private String standard;

    private String state;

    private Integer taskNodeStatus;

    private String modelDesc;

    private String materialNameDesc;

    private String materialTypeDesc;

    private Double sumNumber;

    private Integer tenantId;

    private Long deptId;

}
