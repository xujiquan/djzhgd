package com.djzhgd.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.djzhgd.framework.aspectj.lang.annotation.Excel;
import com.djzhgd.framework.web.domain.BaseEntity;

/**
 * 功能室管理对象 zx_sy_function_room_info
 * 
 * @author suenle
 * @date 2020-03-01
 */
public class ZxSyFunctionRoomInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 功能室id */
    private Long roomId;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private Long parentId;

    /** 所属试验室 */
    @Excel(name = "所属试验室")
    private String laboratoryId;

    /** 功能室名称 */
    @Excel(name = "功能室名称")
    private String roomName;

    /** 功能室类型（备用） */
    @Excel(name = "功能室类型", readConverterExp = "备=用")
    private String roomType;

    /** 功能室面积 */
    @Excel(name = "功能室面积")
    private String area;

    /** 试验室地址 */
    @Excel(name = "试验室地址")
    private String testItems;

    /** 功能室环境是否满足要求 */
    @Excel(name = "功能室环境是否满足要求")
    private String isEnvironment;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setRoomId(Long roomId) 
    {
        this.roomId = roomId;
    }

    public Long getRoomId() 
    {
        return roomId;
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
    public void setLaboratoryId(String laboratoryId) 
    {
        this.laboratoryId = laboratoryId;
    }

    public String getLaboratoryId() 
    {
        return laboratoryId;
    }
    public void setRoomName(String roomName) 
    {
        this.roomName = roomName;
    }

    public String getRoomName() 
    {
        return roomName;
    }
    public void setRoomType(String roomType) 
    {
        this.roomType = roomType;
    }

    public String getRoomType() 
    {
        return roomType;
    }
    public void setArea(String area) 
    {
        this.area = area;
    }

    public String getArea() 
    {
        return area;
    }
    public void setTestItems(String testItems) 
    {
        this.testItems = testItems;
    }

    public String getTestItems() 
    {
        return testItems;
    }
    public void setIsEnvironment(String isEnvironment) 
    {
        this.isEnvironment = isEnvironment;
    }

    public String getIsEnvironment() 
    {
        return isEnvironment;
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
            .append("roomId", getRoomId())
            .append("deptId", getDeptId())
            .append("parentId", getParentId())
            .append("laboratoryId", getLaboratoryId())
            .append("roomName", getRoomName())
            .append("roomType", getRoomType())
            .append("area", getArea())
            .append("testItems", getTestItems())
            .append("isEnvironment", getIsEnvironment())
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
