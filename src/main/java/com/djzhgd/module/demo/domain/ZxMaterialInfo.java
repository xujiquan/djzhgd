package com.djzhgd.module.demo.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.djzhgd.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 原材料评估对象 zx_material_info
 *
 * @author suenle
 * @date 2020-03-26
 */
public class ZxMaterialInfo
{
    private static final long serialVersionUID = 1L;

    /** 机械id */
    @TableId
    private Long materialId;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private Long parentId;

    /** 材料类型 */
    @Excel(name = "材料类型")
    private String materialType;

    /** 名称 */
    @Excel(name = "名称")
    private String materialName;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    /** 地址（定位） */
    @Excel(name = "地址", readConverterExp = "定=位")
    private String companyAddress;

    /** 资质营业范围 */
    @Excel(name = "资质营业范围")
    private String businessScope;

    /** 产量（t/d） */
    @Excel(name = "产量", readConverterExp = "t=/d")
    private String production;

    /** 位置 */
    @Excel(name = "位置")
    private String distance;

    /** 其他位置 */
    @Excel(name = "其他位置")
    private String otherDistance;

    /** 运输方式 */
    @Excel(name = "运输方式")
    private String transportation;

    /** 结构 */
    @Excel(name = "结构")
    private String structure;

    /** 类型 */
    @Excel(name = "类型")
    private String detailedType;

    /** 型号 */
    @Excel(name = "型号")
    private String materialModel;

    /** 指标检测 */
    @Excel(name = "指标检测")
    private String indicators;

    /** 附件 */
    @Excel(name = "附件")
    private String attachment;

    /** 项目备注 */
    @Excel(name = "项目备注")
    private String remark;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 创建者 */
    @Excel(name = "创建者")
    private String createBy;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    private Date updateTime;

    public void setMaterialId(Long materialId)
    {
        this.materialId = materialId;
    }

    public Long getMaterialId()
    {
        return materialId;
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
    public void setMaterialType(String materialType)
    {
        this.materialType = materialType;
    }

    public String getMaterialType()
    {
        return materialType;
    }
    public void setMaterialName(String materialName)
    {
        this.materialName = materialName;
    }

    public String getMaterialName()
    {
        return materialName;
    }
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getCompanyName()
    {
        return companyName;
    }
    public void setCompanyAddress(String companyAddress)
    {
        this.companyAddress = companyAddress;
    }

    public String getCompanyAddress()
    {
        return companyAddress;
    }
    public void setBusinessScope(String businessScope)
    {
        this.businessScope = businessScope;
    }

    public String getBusinessScope()
    {
        return businessScope;
    }
    public void setProduction(String production)
    {
        this.production = production;
    }

    public String getProduction()
    {
        return production;
    }
    public void setDistance(String distance)
    {
        this.distance = distance;
    }

    public String getDistance()
    {
        return distance;
    }
    public void setOtherDistance(String otherDistance)
    {
        this.otherDistance = otherDistance;
    }

    public String getOtherDistance()
    {
        return otherDistance;
    }
    public void setTransportation(String transportation)
    {
        this.transportation = transportation;
    }

    public String getTransportation()
    {
        return transportation;
    }
    public void setStructure(String structure)
    {
        this.structure = structure;
    }

    public String getStructure()
    {
        return structure;
    }
    public void setDetailedType(String detailedType)
    {
        this.detailedType = detailedType;
    }

    public String getDetailedType()
    {
        return detailedType;
    }
    public void setMaterialModel(String materialModel)
    {
        this.materialModel = materialModel;
    }

    public String getMaterialModel()
    {
        return materialModel;
    }
    public void setIndicators(String indicators)
    {
        this.indicators = indicators;
    }

    public String getIndicators()
    {
        return indicators;
    }
    public void setAttachment(String attachment)
    {
        this.attachment = attachment;
    }

    public String getAttachment()
    {
        return attachment;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getRemark()
    {
        return remark;
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
    public void setCreateBy(String createBy)
    {
        this.createBy = createBy;
    }

    public String getCreateBy()
    {
        return createBy;
    }
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getCreateTime()
    {
        return createTime;
    }
    public void setUpdateBy(String updateBy)
    {
        this.updateBy = updateBy;
    }

    public String getUpdateBy()
    {
        return updateBy;
    }
    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("materialId", getMaterialId())
            .append("deptId", getDeptId())
            .append("parentId", getParentId())
            .append("materialType", getMaterialType())
            .append("materialName", getMaterialName())
            .append("companyName", getCompanyName())
            .append("companyAddress", getCompanyAddress())
            .append("businessScope", getBusinessScope())
            .append("production", getProduction())
            .append("distance", getDistance())
            .append("otherDistance", getOtherDistance())
            .append("transportation", getTransportation())
            .append("structure", getStructure())
            .append("detailedType", getDetailedType())
            .append("materialModel", getMaterialModel())
            .append("indicators", getIndicators())
            .append("attachment", getAttachment())
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
