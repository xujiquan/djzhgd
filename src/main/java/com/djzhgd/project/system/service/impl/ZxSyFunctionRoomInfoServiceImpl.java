package com.djzhgd.project.system.service.impl;

import java.util.List;
import com.djzhgd.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.djzhgd.project.system.mapper.ZxSyFunctionRoomInfoMapper;
import com.djzhgd.project.system.domain.ZxSyFunctionRoomInfo;
import com.djzhgd.project.system.service.IZxSyFunctionRoomInfoService;

/**
 * 功能室管理Service业务层处理
 * 
 * @author suenle
 * @date 2020-03-01
 */
@Service
public class ZxSyFunctionRoomInfoServiceImpl implements IZxSyFunctionRoomInfoService 
{
    @Autowired
    private ZxSyFunctionRoomInfoMapper zxSyFunctionRoomInfoMapper;

    /**
     * 查询功能室管理
     * 
     * @param roomId 功能室管理ID
     * @return 功能室管理
     */
    @Override
    public ZxSyFunctionRoomInfo selectZxSyFunctionRoomInfoById(Long roomId)
    {
        return zxSyFunctionRoomInfoMapper.selectZxSyFunctionRoomInfoById(roomId);
    }

    /**
     * 查询功能室管理列表
     * 
     * @param zxSyFunctionRoomInfo 功能室管理
     * @return 功能室管理
     */
    @Override
    public List<ZxSyFunctionRoomInfo> selectZxSyFunctionRoomInfoList(ZxSyFunctionRoomInfo zxSyFunctionRoomInfo)
    {
        return zxSyFunctionRoomInfoMapper.selectZxSyFunctionRoomInfoList(zxSyFunctionRoomInfo);
    }

    /**
     * 新增功能室管理
     * 
     * @param zxSyFunctionRoomInfo 功能室管理
     * @return 结果
     */
    @Override
    public int insertZxSyFunctionRoomInfo(ZxSyFunctionRoomInfo zxSyFunctionRoomInfo)
    {
        zxSyFunctionRoomInfo.setCreateTime(DateUtils.getNowDate());
        return zxSyFunctionRoomInfoMapper.insertZxSyFunctionRoomInfo(zxSyFunctionRoomInfo);
    }

    /**
     * 修改功能室管理
     * 
     * @param zxSyFunctionRoomInfo 功能室管理
     * @return 结果
     */
    @Override
    public int updateZxSyFunctionRoomInfo(ZxSyFunctionRoomInfo zxSyFunctionRoomInfo)
    {
        zxSyFunctionRoomInfo.setUpdateTime(DateUtils.getNowDate());
        return zxSyFunctionRoomInfoMapper.updateZxSyFunctionRoomInfo(zxSyFunctionRoomInfo);
    }

    /**
     * 批量删除功能室管理
     * 
     * @param roomIds 需要删除的功能室管理ID
     * @return 结果
     */
    @Override
    public int deleteZxSyFunctionRoomInfoByIds(Long[] roomIds)
    {
        return zxSyFunctionRoomInfoMapper.deleteZxSyFunctionRoomInfoByIds(roomIds);
    }

    /**
     * 删除功能室管理信息
     * 
     * @param roomId 功能室管理ID
     * @return 结果
     */
    @Override
    public int deleteZxSyFunctionRoomInfoById(Long roomId)
    {
        return zxSyFunctionRoomInfoMapper.deleteZxSyFunctionRoomInfoById(roomId);
    }
}
