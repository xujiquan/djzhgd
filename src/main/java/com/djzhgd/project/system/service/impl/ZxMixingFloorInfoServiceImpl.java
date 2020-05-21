package com.djzhgd.project.system.service.impl;

import java.util.List;
import com.djzhgd.common.utils.DateUtils;
import com.djzhgd.framework.aspectj.lang.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.djzhgd.project.system.mapper.ZxMixingFloorInfoMapper;
import com.djzhgd.project.system.domain.ZxMixingFloorInfo;
import com.djzhgd.project.system.service.IZxMixingFloorInfoService;

/**
 * 拌合楼管理Service业务层处理
 *
 * @author suenle
 * @date 2020-02-27
 */
@Service
public class ZxMixingFloorInfoServiceImpl implements IZxMixingFloorInfoService
{
    @Autowired
    private ZxMixingFloorInfoMapper zxMixingFloorInfoMapper;

    /**
     * 查询拌合楼管理
     *
     * @param floorId 拌合楼管理ID
     * @return 拌合楼管理
     */
    @Override
    public ZxMixingFloorInfo selectZxMixingFloorInfoById(Long floorId)
    {
        return zxMixingFloorInfoMapper.selectZxMixingFloorInfoById(floorId);
    }

    /**
     * 查询拌合楼管理列表
     *
     * @param zxMixingFloorInfo 拌合楼管理
     * @return 拌合楼管理
     */
    @Override
    @DataScope
    public List<ZxMixingFloorInfo> selectZxMixingFloorInfoList(ZxMixingFloorInfo zxMixingFloorInfo)
    {
        return zxMixingFloorInfoMapper.selectZxMixingFloorInfoList(zxMixingFloorInfo);
    }

    /**
     * 新增拌合楼管理
     *
     * @param zxMixingFloorInfo 拌合楼管理
     * @return 结果
     */
    @Override
    public int insertZxMixingFloorInfo(ZxMixingFloorInfo zxMixingFloorInfo)
    {
        zxMixingFloorInfo.setCreateTime(DateUtils.getNowDate());
        return zxMixingFloorInfoMapper.insertZxMixingFloorInfo(zxMixingFloorInfo);
    }

    /**
     * 修改拌合楼管理
     *
     * @param zxMixingFloorInfo 拌合楼管理
     * @return 结果
     */
    @Override
    public int updateZxMixingFloorInfo(ZxMixingFloorInfo zxMixingFloorInfo)
    {
        zxMixingFloorInfo.setUpdateTime(DateUtils.getNowDate());
        return zxMixingFloorInfoMapper.updateZxMixingFloorInfo(zxMixingFloorInfo);
    }

    /**
     * 批量删除拌合楼管理
     *
     * @param floorIds 需要删除的拌合楼管理ID
     * @return 结果
     */
    @Override
    public int deleteZxMixingFloorInfoByIds(Long[] floorIds)
    {
        return zxMixingFloorInfoMapper.deleteZxMixingFloorInfoByIds(floorIds);
    }

    /**
     * 删除拌合楼管理信息
     *
     * @param floorId 拌合楼管理ID
     * @return 结果
     */
    @Override
    public int deleteZxMixingFloorInfoById(Long floorId)
    {
        return zxMixingFloorInfoMapper.deleteZxMixingFloorInfoById(floorId);
    }
}
