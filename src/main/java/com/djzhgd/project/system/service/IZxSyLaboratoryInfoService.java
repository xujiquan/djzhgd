package com.djzhgd.project.system.service;

import java.util.List;
import com.djzhgd.project.system.domain.ZxSyLaboratoryInfo;

/**
 * 试验室管理Service接口
 * 
 * @author suenle
 * @date 2020-03-01
 */
public interface IZxSyLaboratoryInfoService 
{
    /**
     * 查询试验室管理
     * 
     * @param laboratoryId 试验室管理ID
     * @return 试验室管理
     */
    public ZxSyLaboratoryInfo selectZxSyLaboratoryInfoById(Long laboratoryId);

    /**
     * 查询试验室管理列表
     * 
     * @param zxSyLaboratoryInfo 试验室管理
     * @return 试验室管理集合
     */
    public List<ZxSyLaboratoryInfo> selectZxSyLaboratoryInfoList(ZxSyLaboratoryInfo zxSyLaboratoryInfo);

    /**
     * 新增试验室管理
     * 
     * @param zxSyLaboratoryInfo 试验室管理
     * @return 结果
     */
    public int insertZxSyLaboratoryInfo(ZxSyLaboratoryInfo zxSyLaboratoryInfo);

    /**
     * 修改试验室管理
     * 
     * @param zxSyLaboratoryInfo 试验室管理
     * @return 结果
     */
    public int updateZxSyLaboratoryInfo(ZxSyLaboratoryInfo zxSyLaboratoryInfo);

    /**
     * 批量删除试验室管理
     * 
     * @param laboratoryIds 需要删除的试验室管理ID
     * @return 结果
     */
    public int deleteZxSyLaboratoryInfoByIds(Long[] laboratoryIds);

    /**
     * 删除试验室管理信息
     * 
     * @param laboratoryId 试验室管理ID
     * @return 结果
     */
    public int deleteZxSyLaboratoryInfoById(Long laboratoryId);
}
