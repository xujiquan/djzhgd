package com.djzhgd.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.djzhgd.framework.aspectj.lang.annotation.Excel;
import com.djzhgd.framework.web.domain.BaseEntity;

/**
 * 试验室管理对象 zx_sy_laboratory_info
 *
 * @author suenle
 * @date 2020-03-01
 */
public class ZxSyLaboratoryInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 机械id */
    private Long laboratoryId;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private Long parentId;

    /** 试验室类型 */
    @Excel(name = "试验室类型")
    private String laboratoryType;

    /** 试验室名称 */
    @Excel(name = "试验室名称")
    private String laboratoryName;

    /** 试验室面积 */
    @Excel(name = "试验室面积")
    private String area;

    /** 试验室人员数量 */
    @Excel(name = "试验室人员数量")
    private String peopleNum;

    /** 试验室地址 */
    @Excel(name = "试验室地址")
    private String laboratoryAddress;

    /** 试验室范围 */
    @Excel(name = "试验室范围")
    private String laboratoryScope;

    /** 试验室等级 */
    @Excel(name = "试验室等级")
    private String level;

    /** 母体试验室 */
    @Excel(name = "母体试验室")
    private String isMother;

    /** 试验室搭建方式 */
    @Excel(name = "试验室搭建方式")
    private String buildingType;

    /** 人员资质是否满足要求 */
    @Excel(name = "人员资质是否满足要求")
    private String isQualification;

    /** 人员数量是否满足要求 */
    @Excel(name = "人员数量是否满足要求")
    private String isPeopleNum;

    /** 试验方法是否满足要求 */
    @Excel(name = "试验方法是否满足要求")
    private String isTestMethod;

    /** 人员防护是否到位 */
    @Excel(name = "人员防护是否到位")
    private String isPeopleSafe;

    /** 开展人员信用评价管理 */
    @Excel(name = "开展人员信用评价管理")
    private String isCredit;

    /** 开展人员考勤管理 */
    @Excel(name = "开展人员考勤管理")
    private String isAttendance;

    /** 开展人员业务培训 */
    @Excel(name = "开展人员业务培训")
    private String isTraining;

    /** 开展人员档案管理 */
    @Excel(name = "开展人员档案管理")
    private String isArchives;

    /** 试验室制度是否合理 */
    @Excel(name = "试验室制度是否合理")
    private String isRules;

    /** 工地试验室岗位职责是否合理 */
    @Excel(name = "工地试验室岗位职责是否合理")
    private String isJobs;

    /** 设备管理制度是否合理 */
    @Excel(name = "设备管理制度是否合理")
    private String isEquipmentRules;

    /** 样品管理制度是否合理　 */
    @Excel(name = "样品管理制度是否合理　")
    private String isSampleRules;

    /** 试验检测报告审核签发管理制度是否合理 */
    @Excel(name = "试验检测报告审核签发管理制度是否合理")
    private String isReportRules;

    /** 外委试验取送样制度否合理 */
    @Excel(name = "外委试验取送样制度否合理")
    private String isInspectionRules;

    /** 不合格品管理制度是否合理 */
    @Excel(name = "不合格品管理制度是否合理")
    private String isUnqualifiedRules;

    /** 试验室安全管理制度是否合理 */
    @Excel(name = "试验室安全管理制度是否合理")
    private String isSafe;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public String getLaboratoryScope() {
        return laboratoryScope;
    }

    public void setLaboratoryScope(String laboratoryScope) {
        this.laboratoryScope = laboratoryScope;
    }

    public void setLaboratoryId(Long laboratoryId)
    {
        this.laboratoryId = laboratoryId;
    }

    public Long getLaboratoryId()
    {
        return laboratoryId;
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
    public void setLaboratoryType(String laboratoryType)
    {
        this.laboratoryType = laboratoryType;
    }

    public String getLaboratoryType()
    {
        return laboratoryType;
    }
    public void setLaboratoryName(String laboratoryName)
    {
        this.laboratoryName = laboratoryName;
    }

    public String getLaboratoryName()
    {
        return laboratoryName;
    }
    public void setArea(String area)
    {
        this.area = area;
    }

    public String getArea()
    {
        return area;
    }
    public void setPeopleNum(String peopleNum)
    {
        this.peopleNum = peopleNum;
    }

    public String getPeopleNum()
    {
        return peopleNum;
    }
    public void setLaboratoryAddress(String laboratoryAddress)
    {
        this.laboratoryAddress = laboratoryAddress;
    }

    public String getLaboratoryAddress()
    {
        return laboratoryAddress;
    }
    public void setLevel(String level)
    {
        this.level = level;
    }

    public String getLevel()
    {
        return level;
    }
    public void setIsMother(String isMother)
    {
        this.isMother = isMother;
    }

    public String getIsMother()
    {
        return isMother;
    }
    public void setBuildingType(String buildingType)
    {
        this.buildingType = buildingType;
    }

    public String getBuildingType()
    {
        return buildingType;
    }
    public void setIsQualification(String isQualification)
    {
        this.isQualification = isQualification;
    }

    public String getIsQualification()
    {
        return isQualification;
    }
    public void setIsPeopleNum(String isPeopleNum)
    {
        this.isPeopleNum = isPeopleNum;
    }

    public String getIsPeopleNum()
    {
        return isPeopleNum;
    }
    public void setIsTestMethod(String isTestMethod)
    {
        this.isTestMethod = isTestMethod;
    }

    public String getIsTestMethod()
    {
        return isTestMethod;
    }
    public void setIsPeopleSafe(String isPeopleSafe)
    {
        this.isPeopleSafe = isPeopleSafe;
    }

    public String getIsPeopleSafe()
    {
        return isPeopleSafe;
    }
    public void setIsCredit(String isCredit)
    {
        this.isCredit = isCredit;
    }

    public String getIsCredit()
    {
        return isCredit;
    }
    public void setIsAttendance(String isAttendance)
    {
        this.isAttendance = isAttendance;
    }

    public String getIsAttendance()
    {
        return isAttendance;
    }
    public void setIsTraining(String isTraining)
    {
        this.isTraining = isTraining;
    }

    public String getIsTraining()
    {
        return isTraining;
    }
    public void setIsArchives(String isArchives)
    {
        this.isArchives = isArchives;
    }

    public String getIsArchives()
    {
        return isArchives;
    }
    public void setIsRules(String isRules)
    {
        this.isRules = isRules;
    }

    public String getIsRules()
    {
        return isRules;
    }
    public void setIsJobs(String isJobs)
    {
        this.isJobs = isJobs;
    }

    public String getIsJobs()
    {
        return isJobs;
    }
    public void setIsEquipmentRules(String isEquipmentRules)
    {
        this.isEquipmentRules = isEquipmentRules;
    }

    public String getIsEquipmentRules()
    {
        return isEquipmentRules;
    }
    public void setIsSampleRules(String isSampleRules)
    {
        this.isSampleRules = isSampleRules;
    }

    public String getIsSampleRules()
    {
        return isSampleRules;
    }
    public void setIsReportRules(String isReportRules)
    {
        this.isReportRules = isReportRules;
    }

    public String getIsReportRules()
    {
        return isReportRules;
    }
    public void setIsInspectionRules(String isInspectionRules)
    {
        this.isInspectionRules = isInspectionRules;
    }

    public String getIsInspectionRules()
    {
        return isInspectionRules;
    }
    public void setIsUnqualifiedRules(String isUnqualifiedRules)
    {
        this.isUnqualifiedRules = isUnqualifiedRules;
    }

    public String getIsUnqualifiedRules()
    {
        return isUnqualifiedRules;
    }
    public void setIsSafe(String isSafe)
    {
        this.isSafe = isSafe;
    }

    public String getIsSafe()
    {
        return isSafe;
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
            .append("laboratoryId", getLaboratoryId())
            .append("deptId", getDeptId())
            .append("parentId", getParentId())
            .append("laboratoryType", getLaboratoryType())
            .append("laboratoryName", getLaboratoryName())
            .append("area", getArea())
            .append("peopleNum", getPeopleNum())
            .append("laboratoryAddress", getLaboratoryAddress())
            .append("level", getLevel())
            .append("isMother", getIsMother())
            .append("buildingType", getBuildingType())
            .append("isQualification", getIsQualification())
            .append("isPeopleNum", getIsPeopleNum())
            .append("isTestMethod", getIsTestMethod())
            .append("isPeopleSafe", getIsPeopleSafe())
            .append("isCredit", getIsCredit())
            .append("isAttendance", getIsAttendance())
            .append("isTraining", getIsTraining())
            .append("isArchives", getIsArchives())
            .append("isRules", getIsRules())
            .append("isJobs", getIsJobs())
            .append("isEquipmentRules", getIsEquipmentRules())
            .append("isSampleRules", getIsSampleRules())
            .append("isReportRules", getIsReportRules())
            .append("isInspectionRules", getIsInspectionRules())
            .append("isUnqualifiedRules", getIsUnqualifiedRules())
            .append("isSafe", getIsSafe())
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
