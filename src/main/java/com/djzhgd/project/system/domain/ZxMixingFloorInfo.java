package com.djzhgd.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.djzhgd.framework.aspectj.lang.annotation.Excel;
import com.djzhgd.framework.web.domain.BaseEntity;

/**
 * 拌合楼管理对象 zx_mixing_floor_info
 * 
 * @author suenle
 * @date 2020-02-27
 */
public class ZxMixingFloorInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 拌合楼id */
    private Long floorId;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

    /** 备用字段 */
    private Long parentId;

    /** 祖级列表 */
    @Excel(name = "祖级列表")
    private Integer floorType;

    /** 品牌名称 */
    @Excel(name = "品牌名称")
    private String floorName;

    /** 生产能力 */
    @Excel(name = "生产能力")
    private String productionCapacity;

    /** 集料仓数量 */
    @Excel(name = "集料仓数量")
    private String bunkerNum;

    /** 集料仓是否有防雨措施 */
    @Excel(name = "集料仓是否有防雨措施")
    private String isBunkerRain;

    /** 集料仓隔板高度是否满足要求 */
    @Excel(name = "集料仓隔板高度是否满足要求")
    private String isBunkerHight;

    /** 加装管控系统 */
    @Excel(name = "加装管控系统")
    private String isGk;

    /** 拌缸形式 */
    @Excel(name = "拌缸形式")
    private String swMixingCylinder;

    /** 水泥罐容量 */
    @Excel(name = "水泥罐容量")
    private String swCementCapacity;

    /** 燃料类型 */
    @Excel(name = "燃料类型")
    private String lqFuelType;

    /** 普通沥青储存罐-数量 */
    @Excel(name = "普通沥青储存罐-数量")
    private String lqAsphaltNum;

    /** 普通沥青储存罐-容量 */
    @Excel(name = "普通沥青储存罐-容量")
    private String lqAsphaltCapacity;

    /** 改性沥青储存罐-数量 */
    @Excel(name = "改性沥青储存罐-数量")
    private String lqSbsNum;

    /** 改性沥青储存罐-容量 */
    @Excel(name = "改性沥青储存罐-容量")
    private String lqSbsCapacity;

    /** 搅拌设备 */
    @Excel(name = "搅拌设备")
    private String lqIsStir;

    /** 热料仓数量 */
    @Excel(name = "热料仓数量")
    private String lqHotBinNum;

    /** 矿粉罐数量 */
    @Excel(name = "矿粉罐数量")
    private String lqKfNum;

    /** 矿粉罐容量 */
    @Excel(name = "矿粉罐容量")
    private String lqKfCapacity;

    /** 使用回收粉 */
    @Excel(name = "使用回收粉")
    private String lqIsRecycling;

    /** 筛网规格 */
    @Excel(name = "筛网规格")
    private String lqScreenMesh;

    /** 是否与料场筛网匹配 */
    @Excel(name = "是否与料场筛网匹配")
    private String lqIsScreen;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setFloorId(Long floorId) 
    {
        this.floorId = floorId;
    }

    public Long getFloorId() 
    {
        return floorId;
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
    public void setFloorType(Integer floorType) 
    {
        this.floorType = floorType;
    }

    public Integer getFloorType() 
    {
        return floorType;
    }
    public void setFloorName(String floorName) 
    {
        this.floorName = floorName;
    }

    public String getFloorName() 
    {
        return floorName;
    }
    public void setProductionCapacity(String productionCapacity) 
    {
        this.productionCapacity = productionCapacity;
    }

    public String getProductionCapacity() 
    {
        return productionCapacity;
    }
    public void setBunkerNum(String bunkerNum) 
    {
        this.bunkerNum = bunkerNum;
    }

    public String getBunkerNum() 
    {
        return bunkerNum;
    }
    public void setIsBunkerRain(String isBunkerRain) 
    {
        this.isBunkerRain = isBunkerRain;
    }

    public String getIsBunkerRain() 
    {
        return isBunkerRain;
    }
    public void setIsBunkerHight(String isBunkerHight) 
    {
        this.isBunkerHight = isBunkerHight;
    }

    public String getIsBunkerHight() 
    {
        return isBunkerHight;
    }
    public void setIsGk(String isGk) 
    {
        this.isGk = isGk;
    }

    public String getIsGk() 
    {
        return isGk;
    }
    public void setSwMixingCylinder(String swMixingCylinder) 
    {
        this.swMixingCylinder = swMixingCylinder;
    }

    public String getSwMixingCylinder() 
    {
        return swMixingCylinder;
    }
    public void setSwCementCapacity(String swCementCapacity) 
    {
        this.swCementCapacity = swCementCapacity;
    }

    public String getSwCementCapacity() 
    {
        return swCementCapacity;
    }
    public void setLqFuelType(String lqFuelType) 
    {
        this.lqFuelType = lqFuelType;
    }

    public String getLqFuelType() 
    {
        return lqFuelType;
    }
    public void setLqAsphaltNum(String lqAsphaltNum) 
    {
        this.lqAsphaltNum = lqAsphaltNum;
    }

    public String getLqAsphaltNum() 
    {
        return lqAsphaltNum;
    }
    public void setLqAsphaltCapacity(String lqAsphaltCapacity) 
    {
        this.lqAsphaltCapacity = lqAsphaltCapacity;
    }

    public String getLqAsphaltCapacity() 
    {
        return lqAsphaltCapacity;
    }
    public void setLqSbsNum(String lqSbsNum) 
    {
        this.lqSbsNum = lqSbsNum;
    }

    public String getLqSbsNum() 
    {
        return lqSbsNum;
    }
    public void setLqSbsCapacity(String lqSbsCapacity) 
    {
        this.lqSbsCapacity = lqSbsCapacity;
    }

    public String getLqSbsCapacity() 
    {
        return lqSbsCapacity;
    }
    public void setLqIsStir(String lqIsStir) 
    {
        this.lqIsStir = lqIsStir;
    }

    public String getLqIsStir() 
    {
        return lqIsStir;
    }
    public void setLqHotBinNum(String lqHotBinNum) 
    {
        this.lqHotBinNum = lqHotBinNum;
    }

    public String getLqHotBinNum() 
    {
        return lqHotBinNum;
    }
    public void setLqKfNum(String lqKfNum) 
    {
        this.lqKfNum = lqKfNum;
    }

    public String getLqKfNum() 
    {
        return lqKfNum;
    }
    public void setLqKfCapacity(String lqKfCapacity) 
    {
        this.lqKfCapacity = lqKfCapacity;
    }

    public String getLqKfCapacity() 
    {
        return lqKfCapacity;
    }
    public void setLqIsRecycling(String lqIsRecycling) 
    {
        this.lqIsRecycling = lqIsRecycling;
    }

    public String getLqIsRecycling() 
    {
        return lqIsRecycling;
    }
    public void setLqScreenMesh(String lqScreenMesh) 
    {
        this.lqScreenMesh = lqScreenMesh;
    }

    public String getLqScreenMesh() 
    {
        return lqScreenMesh;
    }
    public void setLqIsScreen(String lqIsScreen) 
    {
        this.lqIsScreen = lqIsScreen;
    }

    public String getLqIsScreen() 
    {
        return lqIsScreen;
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
            .append("floorId", getFloorId())
            .append("deptId", getDeptId())
            .append("parentId", getParentId())
            .append("floorType", getFloorType())
            .append("floorName", getFloorName())
            .append("productionCapacity", getProductionCapacity())
            .append("bunkerNum", getBunkerNum())
            .append("isBunkerRain", getIsBunkerRain())
            .append("isBunkerHight", getIsBunkerHight())
            .append("isGk", getIsGk())
            .append("swMixingCylinder", getSwMixingCylinder())
            .append("swCementCapacity", getSwCementCapacity())
            .append("lqFuelType", getLqFuelType())
            .append("lqAsphaltNum", getLqAsphaltNum())
            .append("lqAsphaltCapacity", getLqAsphaltCapacity())
            .append("lqSbsNum", getLqSbsNum())
            .append("lqSbsCapacity", getLqSbsCapacity())
            .append("lqIsStir", getLqIsStir())
            .append("lqHotBinNum", getLqHotBinNum())
            .append("lqKfNum", getLqKfNum())
            .append("lqKfCapacity", getLqKfCapacity())
            .append("lqIsRecycling", getLqIsRecycling())
            .append("lqScreenMesh", getLqScreenMesh())
            .append("lqIsScreen", getLqIsScreen())
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
