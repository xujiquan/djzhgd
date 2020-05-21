package com.djzhgd.project.system.mapper;

import java.util.List;

import com.djzhgd.framework.aspectj.lang.annotation.DataScope;
import com.djzhgd.project.system.domain.ZxMixingFloorInfo;

/**
 * 拌合楼管理Mapper接口
 *
 * @author suenle
 * @date 2020-02-27
 */
public interface ZxMixingFloorInfoMapper
{
    /**
     * 查询拌合楼管理
     *
     * @param floorId 拌合楼管理ID
     * @return 拌合楼管理
     */
    public ZxMixingFloorInfo selectZxMixingFloorInfoById(Long floorId);

    /**
     * 查询拌合楼管理列表
     *
     * @param zxMixingFloorInfo 拌合楼管理
     * @return 拌合楼管理集合
     */
    @DataScope(deptAlias = "")
    public List<ZxMixingFloorInfo> selectZxMixingFloorInfoList(ZxMixingFloorInfo zxMixingFloorInfo);

    /**
     * 新增拌合楼管理
     *
     * @param zxMixingFloorInfo 拌合楼管理
     * @return 结果
     */
    public int insertZxMixingFloorInfo(ZxMixingFloorInfo zxMixingFloorInfo);

    /**
     * 修改拌合楼管理
     *
     * @param zxMixingFloorInfo 拌合楼管理
     * @return 结果
     */
    public int updateZxMixingFloorInfo(ZxMixingFloorInfo zxMixingFloorInfo);

    /**
     * 删除拌合楼管理
     *
     * @param floorId 拌合楼管理ID
     * @return 结果
     */
    public int deleteZxMixingFloorInfoById(Long floorId);

    /**
     * 批量删除拌合楼管理
     *
     * @param floorIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteZxMixingFloorInfoByIds(Long[] floorIds);
}
