package com.djzhgd.module.material.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class ZhgdMaterielDesignUsage   implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String materialCode;

    private String materialName;

    private BigDecimal number;

    private String createUserid;

    private String createUsername;

    private Date createTime;

    private Integer disabled;

    private Integer tenantId;
    private Long deptId;
}
