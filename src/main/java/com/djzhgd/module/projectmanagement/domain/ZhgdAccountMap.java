package com.djzhgd.module.projectmanagement.domain;

import java.math.BigDecimal;
import java.util.Date;

public class ZhgdAccountMap {
    private BigDecimal id;

    private BigDecimal deptId;

    private BigDecimal tenantId;

    private String userId;

    private String otherSystem;

    private String otherSysName;

    private String otherLoginId;

    private String otherPwd;

    private String username;

    private String otherUsername;

    private String loginId;

    private Long disabled;

    private String createUserid;

    private String createUsername;

    private String createIp;

    private Date createDatetime;

    private String updateUserid;

    private String updateUsername;

    private String updateIp;

    private Date updateDatetime;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getDeptId() {
        return deptId;
    }

    public void setDeptId(BigDecimal deptId) {
        this.deptId = deptId;
    }

    public BigDecimal getTenantId() {
        return tenantId;
    }

    public void setTenantId(BigDecimal tenantId) {
        this.tenantId = tenantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getOtherSystem() {
        return otherSystem;
    }

    public void setOtherSystem(String otherSystem) {
        this.otherSystem = otherSystem == null ? null : otherSystem.trim();
    }

    public String getOtherSysName() {
        return otherSysName;
    }

    public void setOtherSysName(String otherSysName) {
        this.otherSysName = otherSysName == null ? null : otherSysName.trim();
    }

    public String getOtherLoginId() {
        return otherLoginId;
    }

    public void setOtherLoginId(String otherLoginId) {
        this.otherLoginId = otherLoginId == null ? null : otherLoginId.trim();
    }

    public String getOtherPwd() {
        return otherPwd;
    }

    public void setOtherPwd(String otherPwd) {
        this.otherPwd = otherPwd == null ? null : otherPwd.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getOtherUsername() {
        return otherUsername;
    }

    public void setOtherUsername(String otherUsername) {
        this.otherUsername = otherUsername == null ? null : otherUsername.trim();
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId == null ? null : loginId.trim();
    }

    public Long getDisabled() {
        return disabled;
    }

    public void setDisabled(Long disabled) {
        this.disabled = disabled;
    }

    public String getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid == null ? null : createUserid.trim();
    }

    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername == null ? null : createUsername.trim();
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp == null ? null : createIp.trim();
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUpdateUserid() {
        return updateUserid;
    }

    public void setUpdateUserid(String updateUserid) {
        this.updateUserid = updateUserid == null ? null : updateUserid.trim();
    }

    public String getUpdateUsername() {
        return updateUsername;
    }

    public void setUpdateUsername(String updateUsername) {
        this.updateUsername = updateUsername == null ? null : updateUsername.trim();
    }

    public String getUpdateIp() {
        return updateIp;
    }

    public void setUpdateIp(String updateIp) {
        this.updateIp = updateIp == null ? null : updateIp.trim();
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}