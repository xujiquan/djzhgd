package com.djzhgd.project.system.service;

import java.util.List;
import com.djzhgd.project.system.domain.ZxFileInfo;

/**
 * 文件管理Service接口
 * 
 * @author suenle
 * @date 2020-03-21
 */
public interface IZxFileInfoService 
{
    /**
     * 查询文件管理
     * 
     * @param fileId 文件管理ID
     * @return 文件管理
     */
    public ZxFileInfo selectZxFileInfoById(Long fileId);

    /**
     * 查询文件管理列表
     * 
     * @param zxFileInfo 文件管理
     * @return 文件管理集合
     */
    public List<ZxFileInfo> selectZxFileInfoList(ZxFileInfo zxFileInfo);

    /**
     * 新增文件管理
     * 
     * @param zxFileInfo 文件管理
     * @return 结果
     */
    public int insertZxFileInfo(ZxFileInfo zxFileInfo);

    /**
     * 修改文件管理
     * 
     * @param zxFileInfo 文件管理
     * @return 结果
     */
    public int updateZxFileInfo(ZxFileInfo zxFileInfo);

    /**
     * 批量删除文件管理
     * 
     * @param fileIds 需要删除的文件管理ID
     * @return 结果
     */
    public int deleteZxFileInfoByIds(Long[] fileIds);

    /**
     * 删除文件管理信息
     * 
     * @param fileId 文件管理ID
     * @return 结果
     */
    public int deleteZxFileInfoById(Long fileId);
}
