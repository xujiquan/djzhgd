package com.djzhgd.project.system.service;

import com.djzhgd.project.system.domain.ZxDeptInfo;
import java.util.List;

/**
 * 部门Service接口
 * 
 * @author suenle
 * @date 2020-02-25
 */
public interface IZxDeptInfoService 
{
    /**
     * 查询部门
     * 
     * @param deptId 部门ID
     * @return 部门
     */
    public ZxDeptInfo selectZxDeptInfoById(Long deptId);

    /**
     * 查询部门列表
     * 
     * @param zxDeptInfo 部门
     * @return 部门集合
     */
    public List<ZxDeptInfo> selectZxDeptInfoList(ZxDeptInfo zxDeptInfo);

    /**
     * 新增部门
     * 
     * @param zxDeptInfo 部门
     * @return 结果
     */
    public int insertZxDeptInfo(ZxDeptInfo zxDeptInfo);

    /**
     * 修改部门
     * 
     * @param zxDeptInfo 部门
     * @return 结果
     */
    public int updateZxDeptInfo(ZxDeptInfo zxDeptInfo);

    /**
     * 批量删除部门
     * 
     * @param deptIds 需要删除的部门ID
     * @return 结果
     */
    public int deleteZxDeptInfoByIds(Long[] deptIds);

    /**
     * 删除部门信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    public int deleteZxDeptInfoById(Long deptId);
}
