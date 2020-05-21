package com.djzhgd.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.djzhgd.framework.aspectj.lang.annotation.Excel;
import com.djzhgd.framework.web.domain.TreeEntity;
import java.util.Date;

/**
 * 测试对象 jl_partition
 * 
 * @author suenle
 * @date 2020-03-12
 */
public class JlPartition extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long proId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String proName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long secId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String secName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String secCode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String unitName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String unitCode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long unitParentId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long unitLevel;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String unitLevelName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long unitOrder;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long finishState;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String cuName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String cpName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String ccpName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String ckName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String cckName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer disabled;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long createUserid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String createUsername;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date createDatetime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String createIp;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long updateUserid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String updateUsername;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date updateDatetime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String updateIp;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProId(Long proId) 
    {
        this.proId = proId;
    }

    public Long getProId() 
    {
        return proId;
    }
    public void setProName(String proName) 
    {
        this.proName = proName;
    }

    public String getProName() 
    {
        return proName;
    }
    public void setSecId(Long secId) 
    {
        this.secId = secId;
    }

    public Long getSecId() 
    {
        return secId;
    }
    public void setSecName(String secName) 
    {
        this.secName = secName;
    }

    public String getSecName() 
    {
        return secName;
    }
    public void setSecCode(String secCode) 
    {
        this.secCode = secCode;
    }

    public String getSecCode() 
    {
        return secCode;
    }
    public void setUnitName(String unitName) 
    {
        this.unitName = unitName;
    }

    public String getUnitName() 
    {
        return unitName;
    }
    public void setUnitCode(String unitCode) 
    {
        this.unitCode = unitCode;
    }

    public String getUnitCode() 
    {
        return unitCode;
    }
    public void setUnitParentId(Long unitParentId) 
    {
        this.unitParentId = unitParentId;
    }

    public Long getUnitParentId() 
    {
        return unitParentId;
    }
    public void setUnitLevel(Long unitLevel) 
    {
        this.unitLevel = unitLevel;
    }

    public Long getUnitLevel() 
    {
        return unitLevel;
    }
    public void setUnitLevelName(String unitLevelName) 
    {
        this.unitLevelName = unitLevelName;
    }

    public String getUnitLevelName() 
    {
        return unitLevelName;
    }
    public void setUnitOrder(Long unitOrder) 
    {
        this.unitOrder = unitOrder;
    }

    public Long getUnitOrder() 
    {
        return unitOrder;
    }
    public void setFinishState(Long finishState) 
    {
        this.finishState = finishState;
    }

    public Long getFinishState() 
    {
        return finishState;
    }
    public void setCuName(String cuName) 
    {
        this.cuName = cuName;
    }

    public String getCuName() 
    {
        return cuName;
    }
    public void setCpName(String cpName) 
    {
        this.cpName = cpName;
    }

    public String getCpName() 
    {
        return cpName;
    }
    public void setCcpName(String ccpName) 
    {
        this.ccpName = ccpName;
    }

    public String getCcpName() 
    {
        return ccpName;
    }
    public void setCkName(String ckName) 
    {
        this.ckName = ckName;
    }

    public String getCkName() 
    {
        return ckName;
    }
    public void setCckName(String cckName) 
    {
        this.cckName = cckName;
    }

    public String getCckName() 
    {
        return cckName;
    }
    public void setDisabled(Integer disabled) 
    {
        this.disabled = disabled;
    }

    public Integer getDisabled() 
    {
        return disabled;
    }
    public void setCreateUserid(Long createUserid) 
    {
        this.createUserid = createUserid;
    }

    public Long getCreateUserid() 
    {
        return createUserid;
    }
    public void setCreateUsername(String createUsername) 
    {
        this.createUsername = createUsername;
    }

    public String getCreateUsername() 
    {
        return createUsername;
    }
    public void setCreateDatetime(Date createDatetime) 
    {
        this.createDatetime = createDatetime;
    }

    public Date getCreateDatetime() 
    {
        return createDatetime;
    }
    public void setCreateIp(String createIp) 
    {
        this.createIp = createIp;
    }

    public String getCreateIp() 
    {
        return createIp;
    }
    public void setUpdateUserid(Long updateUserid) 
    {
        this.updateUserid = updateUserid;
    }

    public Long getUpdateUserid() 
    {
        return updateUserid;
    }
    public void setUpdateUsername(String updateUsername) 
    {
        this.updateUsername = updateUsername;
    }

    public String getUpdateUsername() 
    {
        return updateUsername;
    }
    public void setUpdateDatetime(Date updateDatetime) 
    {
        this.updateDatetime = updateDatetime;
    }

    public Date getUpdateDatetime() 
    {
        return updateDatetime;
    }
    public void setUpdateIp(String updateIp) 
    {
        this.updateIp = updateIp;
    }

    public String getUpdateIp() 
    {
        return updateIp;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("proId", getProId())
            .append("proName", getProName())
            .append("secId", getSecId())
            .append("secName", getSecName())
            .append("secCode", getSecCode())
            .append("unitName", getUnitName())
            .append("unitCode", getUnitCode())
            .append("unitParentId", getUnitParentId())
            .append("unitLevel", getUnitLevel())
            .append("unitLevelName", getUnitLevelName())
            .append("unitOrder", getUnitOrder())
            .append("finishState", getFinishState())
            .append("cuName", getCuName())
            .append("cpName", getCpName())
            .append("ccpName", getCcpName())
            .append("ckName", getCkName())
            .append("cckName", getCckName())
            .append("disabled", getDisabled())
            .append("createUserid", getCreateUserid())
            .append("createUsername", getCreateUsername())
            .append("createDatetime", getCreateDatetime())
            .append("createIp", getCreateIp())
            .append("updateUserid", getUpdateUserid())
            .append("updateUsername", getUpdateUsername())
            .append("updateDatetime", getUpdateDatetime())
            .append("updateIp", getUpdateIp())
            .toString();
    }
}
