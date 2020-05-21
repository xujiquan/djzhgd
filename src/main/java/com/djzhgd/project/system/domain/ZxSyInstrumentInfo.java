package com.djzhgd.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.djzhgd.framework.aspectj.lang.annotation.Excel;
import com.djzhgd.framework.web.domain.BaseEntity;

/**
 * 关键仪器管理对象 zx_sy_instrument_info
 * 
 * @author suenle
 * @date 2020-03-01
 */
public class ZxSyInstrumentInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 仪器id */
    private Long instrumentId;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private Long parentId;

    /** 所属试验室 */
    @Excel(name = "所属试验室")
    private Long laboratoryId;

    /** 所属功能室 */
    @Excel(name = "所属功能室")
    private String roomId;

    /** 功能室类型（备用） */
    @Excel(name = "功能室类型", readConverterExp = "备=用")
    private String instrumentType;

    /** 仪器名称 */
    @Excel(name = "仪器名称")
    private String instrumentName;

    /** 用途 */
    @Excel(name = "用途")
    private String instrumentUse;

    /** 精度/量程 */
    @Excel(name = "精度/量程")
    private String precisions;

    /** 功能正常 */
    @Excel(name = "功能正常")
    private String isFunction;

    /** 使用说明书 */
    @Excel(name = "使用说明书")
    private String isInstructions;

    /** 安装合理 */
    @Excel(name = "安装合理")
    private String isInstallation;

    /** 是否检定 */
    @Excel(name = "是否检定")
    private String isIdentify;

    /** 使用台账 */
    @Excel(name = "使用台账")
    private String isParameter;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setInstrumentId(Long instrumentId) 
    {
        this.instrumentId = instrumentId;
    }

    public Long getInstrumentId() 
    {
        return instrumentId;
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
    public void setLaboratoryId(Long laboratoryId) 
    {
        this.laboratoryId = laboratoryId;
    }

    public Long getLaboratoryId() 
    {
        return laboratoryId;
    }
    public void setRoomId(String roomId) 
    {
        this.roomId = roomId;
    }

    public String getRoomId() 
    {
        return roomId;
    }
    public void setInstrumentType(String instrumentType) 
    {
        this.instrumentType = instrumentType;
    }

    public String getInstrumentType() 
    {
        return instrumentType;
    }
    public void setInstrumentName(String instrumentName) 
    {
        this.instrumentName = instrumentName;
    }

    public String getInstrumentName() 
    {
        return instrumentName;
    }
    public void setInstrumentUse(String instrumentUse) 
    {
        this.instrumentUse = instrumentUse;
    }

    public String getInstrumentUse() 
    {
        return instrumentUse;
    }
    public void setPrecisions(String precisions) 
    {
        this.precisions = precisions;
    }

    public String getPrecisions() 
    {
        return precisions;
    }
    public void setIsFunction(String isFunction) 
    {
        this.isFunction = isFunction;
    }

    public String getIsFunction() 
    {
        return isFunction;
    }
    public void setIsInstructions(String isInstructions) 
    {
        this.isInstructions = isInstructions;
    }

    public String getIsInstructions() 
    {
        return isInstructions;
    }
    public void setIsInstallation(String isInstallation) 
    {
        this.isInstallation = isInstallation;
    }

    public String getIsInstallation() 
    {
        return isInstallation;
    }
    public void setIsIdentify(String isIdentify) 
    {
        this.isIdentify = isIdentify;
    }

    public String getIsIdentify() 
    {
        return isIdentify;
    }
    public void setIsParameter(String isParameter) 
    {
        this.isParameter = isParameter;
    }

    public String getIsParameter() 
    {
        return isParameter;
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
            .append("instrumentId", getInstrumentId())
            .append("deptId", getDeptId())
            .append("parentId", getParentId())
            .append("laboratoryId", getLaboratoryId())
            .append("roomId", getRoomId())
            .append("instrumentType", getInstrumentType())
            .append("instrumentName", getInstrumentName())
            .append("instrumentUse", getInstrumentUse())
            .append("precisions", getPrecisions())
            .append("isFunction", getIsFunction())
            .append("isInstructions", getIsInstructions())
            .append("isInstallation", getIsInstallation())
            .append("isIdentify", getIsIdentify())
            .append("isParameter", getIsParameter())
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
