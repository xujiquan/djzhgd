package com.djzhgd.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.djzhgd.framework.aspectj.lang.annotation.Excel;
import com.djzhgd.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 标段信息对象 zx_project_info
 * 
 * @author suenle
 * @date 2020-02-25
 */
public class ZxSecInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 部门id */
    private Long deptId;

    /** 父部门id */
    @Excel(name = "父部门id")
    private Long parentId;

    /** 祖级列表 */
    private String ancestors;

    /** 项目信息 */
    @Excel(name = "项目信息")
    private String deptName;

    /** 合同（项目）编号 */
    @Excel(name = "合同", readConverterExp = "项=目")
    private String contractCode;

    /** 甲方信息 */
    private String customerInfo;

    /** 甲方名称 */
    @Excel(name = "甲方名称")
    private String customerName;

    /** 甲方地址 */
    private String customerAddress;

    /** 甲方负责人 */
    @Excel(name = "甲方负责人")
    private String customerLeader;

    /** 甲方联系电话 */
    @Excel(name = "甲方联系电话")
    private String customerPhone;

    /** 项目基本情况 */
    @Excel(name = "项目基本情况")
    private String projectInfo;

    /** 项目负责人 */
    @Excel(name = "项目负责人")
    private String projectLeader;

    /** 项目负责人id */
    private Long projectLeaderId;

    /** 项目参加人 */
    private String projectTeam;

    /** 报告核定信息 */
    private String reportInfo;

    /** 报告编制人 */
    private String reportPrepare;

    /** 报告复核人 */
    private String reportReview;

    /** 报告审核人 */
    private String reportAudit;

    /** 合同金额 */
    private String contractMoney;

    /** 项目周期 */
    @Excel(name = "项目周期")
    private String contractCycle;

    /** 项目开始时间 */
    @Excel(name = "项目开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contractCycleStartTime;

    /** 项目结束时间 */
    @Excel(name = "项目结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contractCycleEndTime;

    /** 显示顺序 */
    private Integer orderNum;

    /** 部门状态（0正常 1停用） */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

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
    public void setAncestors(String ancestors) 
    {
        this.ancestors = ancestors;
    }

    public String getAncestors() 
    {
        return ancestors;
    }
    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }
    public void setContractCode(String contractCode) 
    {
        this.contractCode = contractCode;
    }

    public String getContractCode() 
    {
        return contractCode;
    }
    public void setCustomerInfo(String customerInfo) 
    {
        this.customerInfo = customerInfo;
    }

    public String getCustomerInfo() 
    {
        return customerInfo;
    }
    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
    }
    public void setCustomerAddress(String customerAddress) 
    {
        this.customerAddress = customerAddress;
    }

    public String getCustomerAddress() 
    {
        return customerAddress;
    }
    public void setCustomerLeader(String customerLeader) 
    {
        this.customerLeader = customerLeader;
    }

    public String getCustomerLeader() 
    {
        return customerLeader;
    }
    public void setCustomerPhone(String customerPhone) 
    {
        this.customerPhone = customerPhone;
    }

    public String getCustomerPhone() 
    {
        return customerPhone;
    }
    public void setProjectInfo(String projectInfo) 
    {
        this.projectInfo = projectInfo;
    }

    public String getProjectInfo() 
    {
        return projectInfo;
    }
    public void setProjectLeader(String projectLeader) 
    {
        this.projectLeader = projectLeader;
    }

    public String getProjectLeader() 
    {
        return projectLeader;
    }
    public void setProjectLeaderId(Long projectLeaderId) 
    {
        this.projectLeaderId = projectLeaderId;
    }

    public Long getProjectLeaderId() 
    {
        return projectLeaderId;
    }
    public void setProjectTeam(String projectTeam) 
    {
        this.projectTeam = projectTeam;
    }

    public String getProjectTeam() 
    {
        return projectTeam;
    }
    public void setReportInfo(String reportInfo) 
    {
        this.reportInfo = reportInfo;
    }

    public String getReportInfo() 
    {
        return reportInfo;
    }
    public void setReportPrepare(String reportPrepare) 
    {
        this.reportPrepare = reportPrepare;
    }

    public String getReportPrepare() 
    {
        return reportPrepare;
    }
    public void setReportReview(String reportReview) 
    {
        this.reportReview = reportReview;
    }

    public String getReportReview() 
    {
        return reportReview;
    }
    public void setReportAudit(String reportAudit) 
    {
        this.reportAudit = reportAudit;
    }

    public String getReportAudit() 
    {
        return reportAudit;
    }
    public void setContractMoney(String contractMoney) 
    {
        this.contractMoney = contractMoney;
    }

    public String getContractMoney() 
    {
        return contractMoney;
    }
    public void setContractCycle(String contractCycle) 
    {
        this.contractCycle = contractCycle;
    }

    public String getContractCycle() 
    {
        return contractCycle;
    }
    public void setContractCycleStartTime(Date contractCycleStartTime) 
    {
        this.contractCycleStartTime = contractCycleStartTime;
    }

    public Date getContractCycleStartTime() 
    {
        return contractCycleStartTime;
    }
    public void setContractCycleEndTime(Date contractCycleEndTime) 
    {
        this.contractCycleEndTime = contractCycleEndTime;
    }

    public Date getContractCycleEndTime() 
    {
        return contractCycleEndTime;
    }
    public void setOrderNum(Integer orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() 
    {
        return orderNum;
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
            .append("deptId", getDeptId())
            .append("parentId", getParentId())
            .append("ancestors", getAncestors())
            .append("deptName", getDeptName())
            .append("contractCode", getContractCode())
            .append("customerInfo", getCustomerInfo())
            .append("customerName", getCustomerName())
            .append("customerAddress", getCustomerAddress())
            .append("customerLeader", getCustomerLeader())
            .append("customerPhone", getCustomerPhone())
            .append("projectInfo", getProjectInfo())
            .append("projectLeader", getProjectLeader())
            .append("projectLeaderId", getProjectLeaderId())
            .append("projectTeam", getProjectTeam())
            .append("reportInfo", getReportInfo())
            .append("reportPrepare", getReportPrepare())
            .append("reportReview", getReportReview())
            .append("reportAudit", getReportAudit())
            .append("contractMoney", getContractMoney())
            .append("contractCycle", getContractCycle())
            .append("contractCycleStartTime", getContractCycleStartTime())
            .append("contractCycleEndTime", getContractCycleEndTime())
            .append("remark", getRemark())
            .append("orderNum", getOrderNum())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
