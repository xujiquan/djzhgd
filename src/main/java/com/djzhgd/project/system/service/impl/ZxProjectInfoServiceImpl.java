package com.djzhgd.project.system.service.impl;

import java.util.List;
import com.djzhgd.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.djzhgd.project.system.mapper.ZxProjectInfoMapper;
import com.djzhgd.project.system.domain.ZxProjectInfo;
import com.djzhgd.project.system.service.IZxProjectInfoService;

/**
 * 项目信息Service业务层处理
 * 
 * @author suenle
 * @date 2020-02-22
 */
@Service
public class ZxProjectInfoServiceImpl implements IZxProjectInfoService 
{
    @Autowired
    private ZxProjectInfoMapper zxProjectInfoMapper;

    /**
     * 查询项目信息
     * 
     * @param deptId 项目信息ID
     * @return 项目信息
     */
    @Override
    public ZxProjectInfo selectZxProjectInfoById(Long deptId)
    {
        return zxProjectInfoMapper.selectZxProjectInfoById(deptId);
    }

    /**
     * 查询项目信息列表
     * 
     * @param zxProjectInfo 项目信息
     * @return 项目信息
     */
    @Override
    public List<ZxProjectInfo> selectZxProjectInfoList(ZxProjectInfo zxProjectInfo)
    {
        return zxProjectInfoMapper.selectZxProjectInfoList(zxProjectInfo);
    }

    /**
     * 新增项目信息
     * 
     * @param zxProjectInfo 项目信息
     * @return 结果
     */
    @Override
    public int insertZxProjectInfo(ZxProjectInfo zxProjectInfo)
    {
        zxProjectInfo.setCreateTime(DateUtils.getNowDate());
        return zxProjectInfoMapper.insertZxProjectInfo(zxProjectInfo);
    }

    /**
     * 修改项目信息
     * 
     * @param zxProjectInfo 项目信息
     * @return 结果
     */
    @Override
    public int updateZxProjectInfo(ZxProjectInfo zxProjectInfo)
    {
        zxProjectInfo.setUpdateTime(DateUtils.getNowDate());
        return zxProjectInfoMapper.updateZxProjectInfo(zxProjectInfo);
    }

    /**
     * 批量删除项目信息
     * 
     * @param deptIds 需要删除的项目信息ID
     * @return 结果
     */
    @Override
    public int deleteZxProjectInfoByIds(Long[] deptIds)
    {
        return zxProjectInfoMapper.deleteZxProjectInfoByIds(deptIds);
    }

    /**
     * 删除项目信息信息
     * 
     * @param deptId 项目信息ID
     * @return 结果
     */
    @Override
    public int deleteZxProjectInfoById(Long deptId)
    {
        return zxProjectInfoMapper.deleteZxProjectInfoById(deptId);
    }
}
