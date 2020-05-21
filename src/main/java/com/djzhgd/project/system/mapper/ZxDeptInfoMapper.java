package com.djzhgd.project.system.mapper;

import com.djzhgd.project.system.domain.ZxDeptInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 部门Mapper接口
 * 
 * @author suenle
 * @date 2020-02-25
 */
@Repository
public interface ZxDeptInfoMapper 
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
     * 删除部门
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    public int deleteZxDeptInfoById(Long deptId);

    /**
     * 批量删除部门
     * 
     * @param deptIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteZxDeptInfoByIds(Long[] deptIds);
}
