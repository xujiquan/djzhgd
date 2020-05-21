package com.djzhgd.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.djzhgd.framework.aspectj.lang.annotation.Excel;
import com.djzhgd.framework.web.domain.BaseEntity;

/**
 * 文件管理对象 zx_file_info
 * 
 * @author suenle
 * @date 2020-03-21
 */
public class ZxFileInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文件ID */
    private Long fileId;

    /** 标段id */
    @Excel(name = "标段id")
    private Long deptId;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private Long parentId;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 文件地址 */
    @Excel(name = "文件地址")
    private String filePath;

    /** 文件类型（1图片） */
    @Excel(name = "文件类型", readConverterExp = "1=图片")
    private String fileType;

    /** 文件缩略地址 */
    @Excel(name = "文件缩略地址")
    private String fileThumbPath;

    /** 文件缩略名字 */
    @Excel(name = "文件缩略名字")
    private String fileThumbName;

    /** 属于的表的ID */
    @Excel(name = "属于的表的ID")
    private String connectKey;

    /** 属于哪张表 */
    @Excel(name = "属于哪张表")
    private String connectType;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setFileId(Long fileId) 
    {
        this.fileId = fileId;
    }

    public Long getFileId() 
    {
        return fileId;
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
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }
    public void setFileType(String fileType) 
    {
        this.fileType = fileType;
    }

    public String getFileType() 
    {
        return fileType;
    }
    public void setFileThumbPath(String fileThumbPath) 
    {
        this.fileThumbPath = fileThumbPath;
    }

    public String getFileThumbPath() 
    {
        return fileThumbPath;
    }
    public void setFileThumbName(String fileThumbName) 
    {
        this.fileThumbName = fileThumbName;
    }

    public String getFileThumbName() 
    {
        return fileThumbName;
    }
    public void setConnectKey(String connectKey) 
    {
        this.connectKey = connectKey;
    }

    public String getConnectKey() 
    {
        return connectKey;
    }
    public void setConnectType(String connectType) 
    {
        this.connectType = connectType;
    }

    public String getConnectType() 
    {
        return connectType;
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
            .append("fileId", getFileId())
            .append("deptId", getDeptId())
            .append("parentId", getParentId())
            .append("fileName", getFileName())
            .append("filePath", getFilePath())
            .append("fileType", getFileType())
            .append("fileThumbPath", getFileThumbPath())
            .append("fileThumbName", getFileThumbName())
            .append("connectKey", getConnectKey())
            .append("connectType", getConnectType())
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
