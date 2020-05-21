package com.djzhgd.project.system.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
@TableName("ZHGD_FILE")
public class ZhgdFile {
    private Integer id;

    private Integer deptId;

    private Integer tenantId;

    private Integer parentId;

    private Integer form;

    private String fileName;

    private String filePath;

    private Integer fileType;

    private String fileThumbPath;

    private String fileThumbName;

    private Integer connectKey;

    private String connectType;

    private String remark;

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