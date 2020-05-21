package com.djzhgd.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.djzhgd.framework.aspectj.lang.annotation.Excel;
import com.djzhgd.framework.web.domain.BaseEntity;

/**
 * 巡查管理对象 zx_patrol_base
 *
 * @author suenle
 * @date 2020-03-12
 */
public class ZxPatrolBase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 巡查id */
    private Long patrolId;

    /** 巡查标段id */
    @Excel(name = "巡查标段id")
    private Long deptId;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private Long parentId;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String patrolName;

    /** 施工情况 */
    @Excel(name = "施工情况")
    private String constructionStatus;

    /** 天气 */
    @Excel(name = "天气")
    private String weather;

    /** 气温 */
    @Excel(name = "气温")
    private String temperature;

    /** 风速 */
    @Excel(name = "风速")
    private String windSpeed;

    /** 结构层 */
    @Excel(name = "结构层")
    private String constructionStructure;

    /** 单幅总长度 */
    @Excel(name = "单幅总长度")
    private String constructionSingle;

    /** 是否前场巡查 */
    @Excel(name = "是否前场巡查")
    private String isFrontPatrol;

    /** 前场是否安装管控 */
    @Excel(name = "前场是否安装管控")
    private String isFrontGk;

    /** 是否后场巡查 */
    @Excel(name = "是否后场巡查")
    private String isBackPatrol;

    /** 是否后场是否安装管控 */
    @Excel(name = "是否后场是否安装管控")
    private String isBackGk;

    /** 室内试验运行情况 */
    @Excel(name = "室内试验运行情况")
    private String isTestCase;

    /** 实体工程外观情况 */
    @Excel(name = "实体工程外观情况")
    private String isEngineeringAppearance;

    /** 沥青驻场情况 */
    @Excel(name = "沥青驻场情况")
    private String isAsphaltOnSite;

    /** 水稳拌合楼流量标定 */
    @Excel(name = "水稳拌合楼流量标定")
    private String isSwFlowType;

    /** 沥青拌合楼流量标定 */
    @Excel(name = "沥青拌合楼流量标定")
    private String isLqFlowType;

    /** 室内试验资料汇总 */
    @Excel(name = "室内试验资料汇总")
    private String isIndoorProjectInformation;

    /** 现场检测资料汇总 */
    @Excel(name = "现场检测资料汇总")
    private String isSceneProjectInformation;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setPatrolId(Long patrolId)
    {
        this.patrolId = patrolId;
    }

    public Long getPatrolId()
    {
        return patrolId;
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
    public void setConstructionStatus(String constructionStatus)
    {
        this.constructionStatus = constructionStatus;
    }

    public String getConstructionStatus()
    {
        return constructionStatus;
    }
    public void setWeather(String weather)
    {
        this.weather = weather;
    }

    public String getWeather()
    {
        return weather;
    }
    public void setTemperature(String temperature)
    {
        this.temperature = temperature;
    }

    public String getTemperature()
    {
        return temperature;
    }
    public void setWindSpeed(String windSpeed)
    {
        this.windSpeed = windSpeed;
    }

    public String getWindSpeed()
    {
        return windSpeed;
    }
    public void setConstructionStructure(String constructionStructure)
    {
        this.constructionStructure = constructionStructure;
    }

    public String getPatrolName() {
        return patrolName;
    }

    public void setPatrolName(String patrolName) {
        this.patrolName = patrolName;
    }

    public String getConstructionStructure()
    {
        return constructionStructure;
    }
    public void setConstructionSingle(String constructionSingle)
    {
        this.constructionSingle = constructionSingle;
    }

    public String getConstructionSingle()
    {
        return constructionSingle;
    }
    public void setIsFrontPatrol(String isFrontPatrol)
    {
        this.isFrontPatrol = isFrontPatrol;
    }

    public String getIsFrontPatrol()
    {
        return isFrontPatrol;
    }
    public void setIsFrontGk(String isFrontGk)
    {
        this.isFrontGk = isFrontGk;
    }

    public String getIsFrontGk()
    {
        return isFrontGk;
    }
    public void setIsBackPatrol(String isBackPatrol)
    {
        this.isBackPatrol = isBackPatrol;
    }

    public String getIsBackPatrol()
    {
        return isBackPatrol;
    }
    public void setIsBackGk(String isBackGk)
    {
        this.isBackGk = isBackGk;
    }

    public String getIsBackGk()
    {
        return isBackGk;
    }
    public void setIsTestCase(String isTestCase)
    {
        this.isTestCase = isTestCase;
    }

    public String getIsTestCase()
    {
        return isTestCase;
    }
    public void setIsEngineeringAppearance(String isEngineeringAppearance)
    {
        this.isEngineeringAppearance = isEngineeringAppearance;
    }

    public String getIsEngineeringAppearance()
    {
        return isEngineeringAppearance;
    }
    public void setIsAsphaltOnSite(String isAsphaltOnSite)
    {
        this.isAsphaltOnSite = isAsphaltOnSite;
    }

    public String getIsAsphaltOnSite()
    {
        return isAsphaltOnSite;
    }
    public void setIsSwFlowType(String isSwFlowType)
    {
        this.isSwFlowType = isSwFlowType;
    }

    public String getIsSwFlowType()
    {
        return isSwFlowType;
    }
    public void setIsLqFlowType(String isLqFlowType)
    {
        this.isLqFlowType = isLqFlowType;
    }

    public String getIsLqFlowType()
    {
        return isLqFlowType;
    }
    public void setIsIndoorProjectInformation(String isIndoorProjectInformation)
    {
        this.isIndoorProjectInformation = isIndoorProjectInformation;
    }

    public String getIsIndoorProjectInformation()
    {
        return isIndoorProjectInformation;
    }
    public void setIsSceneProjectInformation(String isSceneProjectInformation)
    {
        this.isSceneProjectInformation = isSceneProjectInformation;
    }

    public String getIsSceneProjectInformation()
    {
        return isSceneProjectInformation;
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
            .append("patrolId", getPatrolId())
            .append("deptId", getDeptId())
            .append("parentId", getParentId())
            .append("patrolName", getPatrolName())
            .append("constructionStatus", getConstructionStatus())
            .append("weather", getWeather())
            .append("temperature", getTemperature())
            .append("windSpeed", getWindSpeed())
            .append("constructionStructure", getConstructionStructure())
            .append("constructionSingle", getConstructionSingle())
            .append("isFrontPatrol", getIsFrontPatrol())
            .append("isFrontGk", getIsFrontGk())
            .append("isBackPatrol", getIsBackPatrol())
            .append("isBackGk", getIsBackGk())
            .append("isTestCase", getIsTestCase())
            .append("isEngineeringAppearance", getIsEngineeringAppearance())
            .append("isAsphaltOnSite", getIsAsphaltOnSite())
            .append("isSwFlowType", getIsSwFlowType())
            .append("isLqFlowType", getIsLqFlowType())
            .append("isIndoorProjectInformation", getIsIndoorProjectInformation())
            .append("isSceneProjectInformation", getIsSceneProjectInformation())
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
