package com.djzhgd.project.system.mapper;

import java.util.List;
import com.djzhgd.project.system.domain.ZxSyFunctionRoomInfo;

/**
 * 功能室管理Mapper接口
 * 
 * @author suenle
 * @date 2020-03-01
 */
public interface ZxSyFunctionRoomInfoMapper 
{
    /**
     * 查询功能室管理
     * 
     * @param roomId 功能室管理ID
     * @return 功能室管理
     */
    public ZxSyFunctionRoomInfo selectZxSyFunctionRoomInfoById(Long roomId);

    /**
     * 查询功能室管理列表
     * 
     * @param zxSyFunctionRoomInfo 功能室管理
     * @return 功能室管理集合
     */
    public List<ZxSyFunctionRoomInfo> selectZxSyFunctionRoomInfoList(ZxSyFunctionRoomInfo zxSyFunctionRoomInfo);

    /**
     * 新增功能室管理
     * 
     * @param zxSyFunctionRoomInfo 功能室管理
     * @return 结果
     */
    public int insertZxSyFunctionRoomInfo(ZxSyFunctionRoomInfo zxSyFunctionRoomInfo);

    /**
     * 修改功能室管理
     * 
     * @param zxSyFunctionRoomInfo 功能室管理
     * @return 结果
     */
    public int updateZxSyFunctionRoomInfo(ZxSyFunctionRoomInfo zxSyFunctionRoomInfo);

    /**
     * 删除功能室管理
     * 
     * @param roomId 功能室管理ID
     * @return 结果
     */
    public int deleteZxSyFunctionRoomInfoById(Long roomId);

    /**
     * 批量删除功能室管理
     * 
     * @param roomIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteZxSyFunctionRoomInfoByIds(Long[] roomIds);
}
