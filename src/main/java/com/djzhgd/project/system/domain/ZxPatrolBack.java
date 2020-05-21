package com.djzhgd.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.djzhgd.framework.aspectj.lang.annotation.Excel;
import com.djzhgd.framework.web.domain.BaseEntity;

/**
 * 后场巡查对象 zx_patrol_back
 *
 * @author suenle
 * @date 2020-03-12
 */
public class ZxPatrolBack extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 后场巡查id */
    private Long patrolBackId;

    /** 巡查标段id */
    @Excel(name = "巡查标段id")
    private Long deptId;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private Long parentId;

    /** 巡查id */
    @Excel(name = "巡查id")
    private Long patrolId;

    /** 施工桩号 */
    @Excel(name = "施工桩号")
    private String pileNo;


    /** 施工桩号 */
    @Excel(name = "巡查名称")
    private String patrolFrontName;

    /** 施工距离 */
    @Excel(name = "施工距离")
    private String distance;

    /** 施工结构层 */
    @Excel(name = "施工结构层")
    private String constructionStructure;

    /** 级配类型 */
    @Excel(name = "级配类型")
    private String setWithType;

    /** 自卸汽车是否覆盖 */
    @Excel(name = "自卸汽车是否覆盖")
    private String isCover;

    /** 覆盖详情 */
    @Excel(name = "覆盖详情")
    private String coverDetails;

    /** 运输车辆到场温度 */
    @Excel(name = "运输车辆到场温度")
    private String isTransportTemperature;

    /** 温度详情 */
    @Excel(name = "温度详情")
    private String transportTemperatureDetails;

    /** 摊铺机摊铺方式 */
    @Excel(name = "摊铺机摊铺方式")
    private String pavingWay;

    /** 摊铺机找平方式 */
    @Excel(name = "摊铺机找平方式")
    private String balancedWay;

    /** 摊铺机间距控制 */
    @Excel(name = "摊铺机间距控制")
    private String isSpacing;

    /** 间距详情 */
    @Excel(name = "间距详情")
    private String spacingDetails;

    /** 摊铺机摊铺温度 */
    @Excel(name = "摊铺机摊铺温度")
    private String pavingTemperature;

    /** 是否经常停机 */
    @Excel(name = "是否经常停机")
    private String isPavingSituation;

    /** 停机问题详情 */
    @Excel(name = "停机问题详情")
    private String pavingDetails;

    /** 摊铺后铺面巡查 */
    @Excel(name = "摊铺后铺面巡查")
    private String isPavingResults;


    /** 摊铺后铺面巡查 */
    @Excel(name = "摊铺后铺面巡查详情")
    private String  pavingResultsDetails;

    /** 初压温度 */
    @Excel(name = "初压温度")
    private String oneRccTemperature;

    /** 复压温度 */
    @Excel(name = "复压温度")
    private String twoRccTemperature;

    /** 终压温度 */
    @Excel(name = "终压温度")
    private String threeRccTemperature;

    /** 初压速度 */
    @Excel(name = "初压速度")
    private String oneRccSpeed;

    /** 复压速度 */
    @Excel(name = "复压速度")
    private String twoRccSpeed;

    /** 终压速度 */
    @Excel(name = "终压速度")
    private String threeRccSpeed;

    /** 初压遍数 */
    @Excel(name = "初压遍数")
    private String oneRccPass;

    /** 复压遍数 */
    @Excel(name = "复压遍数")
    private String twoRccPass;

    /** 终压遍数 */
    @Excel(name = "终压遍数")
    private String threeRccPass;

    /** 物联网数据分析 */
    @Excel(name = "物联网数据分析")
    private String dataAnalysis;

    /** 存在问题及处理意见 */
    @Excel(name = "存在问题及处理意见")
    private String patrolBackIssue;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public String getPavingResultsDetails() {
        return pavingResultsDetails;
    }

    public void setPavingResultsDetails(String pavingResultsDetails) {
        this.pavingResultsDetails = pavingResultsDetails;
    }

    public String getPatrolFrontName() {
        return patrolFrontName;
    }

    public void setPatrolFrontName(String patrolFrontName) {
        this.patrolFrontName = patrolFrontName;
    }

    public void setPatrolBackId(Long patrolBackId)
    {
        this.patrolBackId = patrolBackId;
    }

    public Long getPatrolBackId()
    {
        return patrolBackId;
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
    public void setPatrolId(Long patrolId)
    {
        this.patrolId = patrolId;
    }

    public Long getPatrolId()
    {
        return patrolId;
    }
    public void setPileNo(String pileNo)
    {
        this.pileNo = pileNo;
    }

    public String getPileNo()
    {
        return pileNo;
    }
    public void setDistance(String distance)
    {
        this.distance = distance;
    }

    public String getDistance()
    {
        return distance;
    }
    public void setConstructionStructure(String constructionStructure)
    {
        this.constructionStructure = constructionStructure;
    }

    public String getConstructionStructure()
    {
        return constructionStructure;
    }
    public void setSetWithType(String setWithType)
    {
        this.setWithType = setWithType;
    }

    public String getSetWithType()
    {
        return setWithType;
    }
    public void setIsCover(String isCover)
    {
        this.isCover = isCover;
    }

    public String getIsCover()
    {
        return isCover;
    }
    public void setCoverDetails(String coverDetails)
    {
        this.coverDetails = coverDetails;
    }

    public String getCoverDetails()
    {
        return coverDetails;
    }
    public void setIsTransportTemperature(String isTransportTemperature)
    {
        this.isTransportTemperature = isTransportTemperature;
    }

    public String getIsTransportTemperature()
    {
        return isTransportTemperature;
    }
    public void setTransportTemperatureDetails(String transportTemperatureDetails)
    {
        this.transportTemperatureDetails = transportTemperatureDetails;
    }

    public String getTransportTemperatureDetails()
    {
        return transportTemperatureDetails;
    }
    public void setPavingWay(String pavingWay)
    {
        this.pavingWay = pavingWay;
    }

    public String getPavingWay()
    {
        return pavingWay;
    }
    public void setBalancedWay(String balancedWay)
    {
        this.balancedWay = balancedWay;
    }

    public String getBalancedWay()
    {
        return balancedWay;
    }
    public void setIsSpacing(String isSpacing)
    {
        this.isSpacing = isSpacing;
    }

    public String getIsSpacing()
    {
        return isSpacing;
    }
    public void setSpacingDetails(String spacingDetails)
    {
        this.spacingDetails = spacingDetails;
    }

    public String getSpacingDetails()
    {
        return spacingDetails;
    }
    public void setPavingTemperature(String pavingTemperature)
    {
        this.pavingTemperature = pavingTemperature;
    }

    public String getPavingTemperature()
    {
        return pavingTemperature;
    }
    public void setIsPavingSituation(String isPavingSituation)
    {
        this.isPavingSituation = isPavingSituation;
    }

    public String getIsPavingSituation()
    {
        return isPavingSituation;
    }
    public void setPavingDetails(String pavingDetails)
    {
        this.pavingDetails = pavingDetails;
    }

    public String getPavingDetails()
    {
        return pavingDetails;
    }
    public void setIsPavingResults(String isPavingResults)
    {
        this.isPavingResults = isPavingResults;
    }

    public String getIsPavingResults()
    {
        return isPavingResults;
    }
    public void setOneRccTemperature(String oneRccTemperature)
    {
        this.oneRccTemperature = oneRccTemperature;
    }

    public String getOneRccTemperature()
    {
        return oneRccTemperature;
    }
    public void setTwoRccTemperature(String twoRccTemperature)
    {
        this.twoRccTemperature = twoRccTemperature;
    }

    public String getTwoRccTemperature()
    {
        return twoRccTemperature;
    }
    public void setThreeRccTemperature(String threeRccTemperature)
    {
        this.threeRccTemperature = threeRccTemperature;
    }

    public String getThreeRccTemperature()
    {
        return threeRccTemperature;
    }
    public void setOneRccSpeed(String oneRccSpeed)
    {
        this.oneRccSpeed = oneRccSpeed;
    }

    public String getOneRccSpeed()
    {
        return oneRccSpeed;
    }
    public void setTwoRccSpeed(String twoRccSpeed)
    {
        this.twoRccSpeed = twoRccSpeed;
    }

    public String getTwoRccSpeed()
    {
        return twoRccSpeed;
    }
    public void setThreeRccSpeed(String threeRccSpeed)
    {
        this.threeRccSpeed = threeRccSpeed;
    }

    public String getThreeRccSpeed()
    {
        return threeRccSpeed;
    }
    public void setOneRccPass(String oneRccPass)
    {
        this.oneRccPass = oneRccPass;
    }

    public String getOneRccPass()
    {
        return oneRccPass;
    }
    public void setTwoRccPass(String twoRccPass)
    {
        this.twoRccPass = twoRccPass;
    }

    public String getTwoRccPass()
    {
        return twoRccPass;
    }
    public void setThreeRccPass(String threeRccPass)
    {
        this.threeRccPass = threeRccPass;
    }

    public String getThreeRccPass()
    {
        return threeRccPass;
    }
    public void setDataAnalysis(String dataAnalysis)
    {
        this.dataAnalysis = dataAnalysis;
    }

    public String getDataAnalysis()
    {
        return dataAnalysis;
    }
    public void setPatrolBackIssue(String patrolBackIssue)
    {
        this.patrolBackIssue = patrolBackIssue;
    }

    public String getPatrolBackIssue()
    {
        return patrolBackIssue;
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
            .append("patrolBackId", getPatrolBackId())
            .append("deptId", getDeptId())
            .append("parentId", getParentId())
            .append("patrolId", getPatrolId())
            .append("pileNo", getPileNo())
            .append("distance", getDistance())
            .append("constructionStructure", getConstructionStructure())
            .append("setWithType", getSetWithType())
            .append("isCover", getIsCover())
            .append("coverDetails", getCoverDetails())
            .append("isTransportTemperature", getIsTransportTemperature())
            .append("transportTemperatureDetails", getTransportTemperatureDetails())
            .append("pavingWay", getPavingWay())
            .append("balancedWay", getBalancedWay())
            .append("isSpacing", getIsSpacing())
            .append("spacingDetails", getSpacingDetails())
            .append("pavingTemperature", getPavingTemperature())
            .append("isPavingSituation", getIsPavingSituation())
            .append("pavingDetails", getPavingDetails())
            .append("isPavingResults", getIsPavingResults())
            .append("oneRccTemperature", getOneRccTemperature())
            .append("twoRccTemperature", getTwoRccTemperature())
            .append("threeRccTemperature", getThreeRccTemperature())
            .append("oneRccSpeed", getOneRccSpeed())
            .append("twoRccSpeed", getTwoRccSpeed())
            .append("threeRccSpeed", getThreeRccSpeed())
            .append("oneRccPass", getOneRccPass())
            .append("twoRccPass", getTwoRccPass())
            .append("threeRccPass", getThreeRccPass())
            .append("dataAnalysis", getDataAnalysis())
            .append("patrolBackIssue", getPatrolBackIssue())
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
