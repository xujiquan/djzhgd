package com.djzhgd.project.system.service.impl;

import java.util.List;
import com.djzhgd.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.djzhgd.project.system.mapper.ZxSyLaboratoryInfoMapper;
import com.djzhgd.project.system.domain.ZxSyLaboratoryInfo;
import com.djzhgd.project.system.service.IZxSyLaboratoryInfoService;

/**
 * 试验室管理Service业务层处理
 * 
 * @author suenle
 * @date 2020-03-01
 */
@Service
public class ZxSyLaboratoryInfoServiceImpl implements IZxSyLaboratoryInfoService 
{
    @Autowired
    private ZxSyLaboratoryInfoMapper zxSyLaboratoryInfoMapper;

    /**
     * 查询试验室管理
     * 
     * @param laboratoryId 试验室管理ID
     * @return 试验室管理
     */
    @Override
    public ZxSyLaboratoryInfo selectZxSyLaboratoryInfoById(Long laboratoryId)
    {
        return zxSyLaboratoryInfoMapper.selectZxSyLaboratoryInfoById(laboratoryId);
    }

    /**
     * 查询试验室管理列表
     * 
     * @param zxSyLaboratoryInfo 试验室管理
     * @return 试验室管理
     */
    @Override
    public List<ZxSyLaboratoryInfo> selectZxSyLaboratoryInfoList(ZxSyLaboratoryInfo zxSyLaboratoryInfo)
    {
        return zxSyLaboratoryInfoMapper.selectZxSyLaboratoryInfoList(zxSyLaboratoryInfo);
    }

    /**
     * 新增试验室管理
     * 
     * @param zxSyLaboratoryInfo 试验室管理
     * @return 结果
     */
    @Override
    public int insertZxSyLaboratoryInfo(ZxSyLaboratoryInfo zxSyLaboratoryInfo)
    {
        zxSyLaboratoryInfo.setCreateTime(DateUtils.getNowDate());
        return zxSyLaboratoryInfoMapper.insertZxSyLaboratoryInfo(zxSyLaboratoryInfo);
    }

    /**
     * 修改试验室管理
     * 
     * @param zxSyLaboratoryInfo 试验室管理
     * @return 结果
     */
    @Override
    public int updateZxSyLaboratoryInfo(ZxSyLaboratoryInfo zxSyLaboratoryInfo)
    {
        zxSyLaboratoryInfo.setUpdateTime(DateUtils.getNowDate());
        return zxSyLaboratoryInfoMapper.updateZxSyLaboratoryInfo(zxSyLaboratoryInfo);
    }

    /**
     * 批量删除试验室管理
     * 
     * @param laboratoryIds 需要删除的试验室管理ID
     * @return 结果
     */
    @Override
    public int deleteZxSyLaboratoryInfoByIds(Long[] laboratoryIds)
    {
        return zxSyLaboratoryInfoMapper.deleteZxSyLaboratoryInfoByIds(laboratoryIds);
    }

    /**
     * 删除试验室管理信息
     * 
     * @param laboratoryId 试验室管理ID
     * @return 结果
     */
    @Override
    public int deleteZxSyLaboratoryInfoById(Long laboratoryId)
    {
        return zxSyLaboratoryInfoMapper.deleteZxSyLaboratoryInfoById(laboratoryId);
    }
}
