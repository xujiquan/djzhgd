package com.djzhgd.project.system.service.impl;

import java.util.List;
import com.djzhgd.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.djzhgd.project.system.mapper.ZxMechanicalInfoMapper;
import com.djzhgd.project.system.domain.ZxMechanicalInfo;
import com.djzhgd.project.system.service.IZxMechanicalInfoService;

/**
 * 机械管理Service业务层处理
 *
 * @author suenle
 * @date 2020-02-27
 */
@Service
public class ZxMechanicalInfoServiceImpl implements IZxMechanicalInfoService
{
    @Autowired
    private ZxMechanicalInfoMapper zxMechanicalInfoMapper;

    /**
     * 查询机械管理
     *
     * @param mechanicalId 机械管理ID
     * @return 机械管理
     */
    @Override
    public ZxMechanicalInfo selectZxMechanicalInfoById(Long mechanicalId)
    {
        return zxMechanicalInfoMapper.selectZxMechanicalInfoById(mechanicalId);
    }

    /**
     * 查询机械管理列表
     *
     * @param zxMechanicalInfo 机械管理
     * @return 机械管理
     */
    @Override
    public List<ZxMechanicalInfo> selectZxMechanicalInfoList(ZxMechanicalInfo zxMechanicalInfo)
    {
        return zxMechanicalInfoMapper.selectZxMechanicalInfoList(zxMechanicalInfo);
    }

    /**
     * 新增机械管理
     *
     * @param zxMechanicalInfo 机械管理
     * @return 结果
     */
    @Override
    public int insertZxMechanicalInfo(ZxMechanicalInfo zxMechanicalInfo)
    {
        zxMechanicalInfo.setCreateTime(DateUtils.getNowDate());
        return zxMechanicalInfoMapper.insertZxMechanicalInfo(zxMechanicalInfo);
    }

    /**
     * 修改机械管理
     *
     * @param zxMechanicalInfo 机械管理
     * @return 结果
     */
    @Override
    public int updateZxMechanicalInfo(ZxMechanicalInfo zxMechanicalInfo)
    {
        zxMechanicalInfo.setUpdateTime(DateUtils.getNowDate());
        return zxMechanicalInfoMapper.updateZxMechanicalInfo(zxMechanicalInfo);
    }

    /**
     * 批量删除机械管理
     *
     * @param mechanicalIds 需要删除的机械管理ID
     * @return 结果
     */
    @Override
    public int deleteZxMechanicalInfoByIds(Long[] mechanicalIds)
    {
        return zxMechanicalInfoMapper.deleteZxMechanicalInfoByIds(mechanicalIds);
    }

    /**
     * 删除机械管理信息
     *
     * @param mechanicalId 机械管理ID
     * @return 结果
     */
    @Override
    public int deleteZxMechanicalInfoById(Long mechanicalId)
    {
        return zxMechanicalInfoMapper.deleteZxMechanicalInfoById(mechanicalId);
    }
}
