package com.djzhgd.project.system.service;

import com.djzhgd.project.system.domain.ZxProjectInfo;
import java.util.List;

/**
 * 项目信息Service接口
 * 
 * @author suenle
 * @date 2020-02-22
 */
public interface IZxProjectInfoService 
{
    /**
     * 查询项目信息
     * 
     * @param deptId 项目信息ID
     * @return 项目信息
     */
    public ZxProjectInfo selectZxProjectInfoById(Long deptId);

    /**
     * 查询项目信息列表
     * 
     * @param zxProjectInfo 项目信息
     * @return 项目信息集合
     */
    public List<ZxProjectInfo> selectZxProjectInfoList(ZxProjectInfo zxProjectInfo);

    /**
     * 新增项目信息
     * 
     * @param zxProjectInfo 项目信息
     * @return 结果
     */
    public int insertZxProjectInfo(ZxProjectInfo zxProjectInfo);

    /**
     * 修改项目信息
     * 
     * @param zxProjectInfo 项目信息
     * @return 结果
     */
    public int updateZxProjectInfo(ZxProjectInfo zxProjectInfo);

    /**
     * 批量删除项目信息
     * 
     * @param deptIds 需要删除的项目信息ID
     * @return 结果
     */
    public int deleteZxProjectInfoByIds(Long[] deptIds);

    /**
     * 删除项目信息信息
     * 
     * @param deptId 项目信息ID
     * @return 结果
     */
    public int deleteZxProjectInfoById(Long deptId);
}
