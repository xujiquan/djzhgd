package com.djzhgd.project.system.service;

import com.djzhgd.project.system.domain.ZxSyInstrumentInfo;

import java.util.List;

/**
 * 关键仪器管理Service接口
 * @author suenle
 * @date 2020-03-01
 */
public interface IZxSyInstrumentInfoService {
    /**
     * 查询关键仪器管理
     * 
     * @param instrumentId 关键仪器管理ID
     * @return 关键仪器管理
     */
    ZxSyInstrumentInfo selectZxSyInstrumentInfoById(Long instrumentId);

    /**
     * 查询关键仪器管理列表
     * 
     * @param zxSyInstrumentInfo 关键仪器管理
     * @return 关键仪器管理集合
     */
    List<ZxSyInstrumentInfo> selectZxSyInstrumentInfoList(ZxSyInstrumentInfo zxSyInstrumentInfo);

    /**
     * 新增关键仪器管理
     * 
     * @param zxSyInstrumentInfo 关键仪器管理
     * @return 结果
     */
    int insertZxSyInstrumentInfo(ZxSyInstrumentInfo zxSyInstrumentInfo);

    /**
     * 修改关键仪器管理
     * 
     * @param zxSyInstrumentInfo 关键仪器管理
     * @return 结果
     */
    int updateZxSyInstrumentInfo(ZxSyInstrumentInfo zxSyInstrumentInfo);

    /**
     * 批量删除关键仪器管理
     * 
     * @param instrumentIds 需要删除的关键仪器管理ID
     * @return 结果
     */
    int deleteZxSyInstrumentInfoByIds(Long[] instrumentIds);

    /**
     * 删除关键仪器管理信息
     * 
     * @param instrumentId 关键仪器管理ID
     * @return 结果
     */
    int deleteZxSyInstrumentInfoById(Long instrumentId);
}
