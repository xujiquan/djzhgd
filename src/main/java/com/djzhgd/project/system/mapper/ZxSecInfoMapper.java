package com.djzhgd.project.system.mapper;

import com.djzhgd.project.system.domain.ZxSecInfo;
import java.util.List;

/**
 * 标段信息Mapper接口
 * 
 * @author suenle
 * @date 2020-02-25
 */
public interface ZxSecInfoMapper 
{
    /**
     * 查询标段信息
     * 
     * @param deptId 标段信息ID
     * @return 标段信息
     */
    public ZxSecInfo selectZxSecInfoById(Long deptId);

    /**
     * 查询标段信息列表
     * 
     * @param zxSecInfo 标段信息
     * @return 标段信息集合
     */
    public List<ZxSecInfo> selectZxSecInfoList(ZxSecInfo zxSecInfo);

    /**
     * 新增标段信息
     * 
     * @param zxSecInfo 标段信息
     * @return 结果
     */
    public int insertZxSecInfo(ZxSecInfo zxSecInfo);

    /**
     * 修改标段信息
     * 
     * @param zxSecInfo 标段信息
     * @return 结果
     */
    public int updateZxSecInfo(ZxSecInfo zxSecInfo);

    /**
     * 删除标段信息
     * 
     * @param deptId 标段信息ID
     * @return 结果
     */
    public int deleteZxSecInfoById(Long deptId);

    /**
     * 批量删除标段信息
     * 
     * @param deptIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteZxSecInfoByIds(Long[] deptIds);
}
