package com.djzhgd.project.system.service.impl;

import com.djzhgd.common.utils.DateUtils;
import com.djzhgd.project.system.domain.ZxSyInstrumentInfo;
import com.djzhgd.project.system.mapper.ZxSyInstrumentInfoMapper;
import com.djzhgd.project.system.service.IZxSyInstrumentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 关键仪器管理Service业务层处理
 * 
 * @author suenle
 * @date 2020-03-01
 */
@Service
public class ZxSyInstrumentInfoServiceImpl implements IZxSyInstrumentInfoService 
{
    @Autowired
    private ZxSyInstrumentInfoMapper zxSyInstrumentInfoMapper;

    /**
     * 查询关键仪器管理
     * 
     * @param instrumentId 关键仪器管理ID
     * @return 关键仪器管理
     */
    @Override
    public ZxSyInstrumentInfo selectZxSyInstrumentInfoById(Long instrumentId)
    {
        return zxSyInstrumentInfoMapper.selectZxSyInstrumentInfoById(instrumentId);
    }

    /**
     * 查询关键仪器管理列表
     * 
     * @param zxSyInstrumentInfo 关键仪器管理
     * @return 关键仪器管理
     */
    @Override
    public List<ZxSyInstrumentInfo> selectZxSyInstrumentInfoList(ZxSyInstrumentInfo zxSyInstrumentInfo)
    {
        return zxSyInstrumentInfoMapper.selectZxSyInstrumentInfoList(zxSyInstrumentInfo);
    }

    /**
     * 新增关键仪器管理
     * 
     * @param zxSyInstrumentInfo 关键仪器管理
     * @return 结果
     */
    @Override
    public int insertZxSyInstrumentInfo(ZxSyInstrumentInfo zxSyInstrumentInfo)
    {
        zxSyInstrumentInfo.setCreateTime(DateUtils.getNowDate());
        return zxSyInstrumentInfoMapper.insertZxSyInstrumentInfo(zxSyInstrumentInfo);
    }

    /**
     * 修改关键仪器管理
     * 
     * @param zxSyInstrumentInfo 关键仪器管理
     * @return 结果
     */
    @Override
    public int updateZxSyInstrumentInfo(ZxSyInstrumentInfo zxSyInstrumentInfo)
    {
        zxSyInstrumentInfo.setUpdateTime(DateUtils.getNowDate());
        return zxSyInstrumentInfoMapper.updateZxSyInstrumentInfo(zxSyInstrumentInfo);
    }

    /**
     * 批量删除关键仪器管理
     * 
     * @param instrumentIds 需要删除的关键仪器管理ID
     * @return 结果
     */
    @Override
    public int deleteZxSyInstrumentInfoByIds(Long[] instrumentIds)
    {
        return zxSyInstrumentInfoMapper.deleteZxSyInstrumentInfoByIds(instrumentIds);
    }

    /**
     * 删除关键仪器管理信息
     * 
     * @param instrumentId 关键仪器管理ID
     * @return 结果
     */
    @Override
    public int deleteZxSyInstrumentInfoById(Long instrumentId)
    {
        return zxSyInstrumentInfoMapper.deleteZxSyInstrumentInfoById(instrumentId);
    }
}
