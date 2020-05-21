package com.djzhgd.project.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.djzhgd.framework.aspectj.lang.annotation.Excel;
import com.djzhgd.framework.web.domain.BaseEntity;

/**
 * 机械管理对象 zx_mechanical_info
 *
 * @author suenle
 * @date 2020-02-27
 */
public class ZxMechanicalInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 机械id */
    @TableId
    private Long mechanicalId;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private Long parentId;

    /** 机械类型 */
    @Excel(name = "机械类型")
    private String mechanicalType;

    /** 施工层 */
    @Excel(name = "施工层")
    private String userType;

    /** 具体类型 */
    @Excel(name = "具体类型")
    private String specificType;

    /** 型号 */
    @Excel(name = "型号")
    private String mechanicalModel;

    /** 吨位 */
    @Excel(name = "吨位")
    private String power;

    /** 作业方式 */
    @Excel(name = "作业方式")
    private String tpIsPractices;

    /** 是否大厚度 */
    @Excel(name = "是否大厚度")
    private String tpIsBigThick;

    /** 振动形式或者轮胎数量 */
    @Excel(name = "振动形式或者轮胎数量")
    private String ylTonnage;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setMechanicalId(Long mechanicalId)
    {
        this.mechanicalId = mechanicalId;
    }

    public Long getMechanicalId()
    {
        return mechanicalId;
    }
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }
    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Long getParentId()
    {
        return parentId;
    }
    public void setMechanicalType(String mechanicalType)
    {
        this.mechanicalType = mechanicalType;
    }

    public String getMechanicalType()
    {
        return mechanicalType;
    }
    public void setUserType(String userType)
    {
        this.userType = userType;
    }

    public String getUserType()
    {
        return userType;
    }
    public void setSpecificType(String specificType)
    {
        this.specificType = specificType;
    }

    public String getSpecificType()
    {
        return specificType;
    }
    public void setMechanicalModel(String mechanicalModel)
    {
        this.mechanicalModel = mechanicalModel;
    }

    public String getMechanicalModel()
    {
        return mechanicalModel;
    }
    public void setPower(String power)
    {
        this.power = power;
    }

    public String getPower()
    {
        return power;
    }
    public void setTpIsPractices(String tpIsPractices)
    {
        this.tpIsPractices = tpIsPractices;
    }

    public String getTpIsPractices()
    {
        return tpIsPractices;
    }
    public void setTpIsBigThick(String tpIsBigThick)
    {
        this.tpIsBigThick = tpIsBigThick;
    }

    public String getTpIsBigThick()
    {
        return tpIsBigThick;
    }
    public void setYlTonnage(String ylTonnage)
    {
        this.ylTonnage = ylTonnage;
    }

    public String getYlTonnage()
    {
        return ylTonnage;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mechanicalId", getMechanicalId())
            .append("deptId", getDeptId())
            .append("parentId", getParentId())
            .append("mechanicalType", getMechanicalType())
            .append("userType", getUserType())
            .append("specificType", getSpecificType())
            .append("mechanicalModel", getMechanicalModel())
            .append("power", getPower())
            .append("tpIsPractices", getTpIsPractices())
            .append("tpIsBigThick", getTpIsBigThick())
            .append("ylTonnage", getYlTonnage())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
