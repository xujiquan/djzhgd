package com.djzhgd.project.system.service;

import java.util.List;
import com.djzhgd.project.system.domain.ZxPatrolBack;

/**
 * 后场巡查Service接口
 * 
 * @author suenle
 * @date 2020-03-12
 */
public interface IZxPatrolBackService 
{
    /**
     * 查询后场巡查
     * 
     * @param patrolBackId 后场巡查ID
     * @return 后场巡查
     */
    public ZxPatrolBack selectZxPatrolBackById(Long patrolBackId);

    /**
     * 查询后场巡查列表
     * 
     * @param zxPatrolBack 后场巡查
     * @return 后场巡查集合
     */
    public List<ZxPatrolBack> selectZxPatrolBackList(ZxPatrolBack zxPatrolBack);

    /**
     * 新增后场巡查
     * 
     * @param zxPatrolBack 后场巡查
     * @return 结果
     */
    public int insertZxPatrolBack(ZxPatrolBack zxPatrolBack);

    /**
     * 修改后场巡查
     * 
     * @param zxPatrolBack 后场巡查
     * @return 结果
     */
    public int updateZxPatrolBack(ZxPatrolBack zxPatrolBack);

    /**
     * 批量删除后场巡查
     * 
     * @param patrolBackIds 需要删除的后场巡查ID
     * @return 结果
     */
    public int deleteZxPatrolBackByIds(Long[] patrolBackIds);

    /**
     * 删除后场巡查信息
     * 
     * @param patrolBackId 后场巡查ID
     * @return 结果
     */
    public int deleteZxPatrolBackById(Long patrolBackId);
}
