package com.djzhgd.module.contract.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@Data
@TableName("ZHGD_TENDERING_BIDDING")
public class ZhgdTenderingBidding {
    private Integer id;

    private Integer deptId;

    private Integer tenantId;

    private String assTitle;

    private Integer upUser;

    private String remark;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer disabled;

    private Long createUserid;

    private String createUsername;

    @TableField(fill = FieldFill.INSERT)
    private Date createDatetime;

    private String createIp;

    private Long updateUserid;

    private String updateUsername;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDatetime;

    private String updateIp;

    private Integer version;


}