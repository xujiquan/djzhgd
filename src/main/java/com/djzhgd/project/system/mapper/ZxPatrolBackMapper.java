package com.djzhgd.project.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.djzhgd.project.system.domain.ZxPatrolBack;
import org.springframework.stereotype.Repository;

/**
 * 后场巡查Mapper接口
 *
 * @author suenle
 * @date 2020-03-12
 */
@Repository
public interface ZxPatrolBackMapper extends BaseMapper<ZxPatrolBack>
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
     * 删除后场巡查
     *
     * @param patrolBackId 后场巡查ID
     * @return 结果
     */
    public int deleteZxPatrolBackById(Long patrolBackId);

    /**
     * 批量删除后场巡查
     *
     * @param patrolBackIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteZxPatrolBackByIds(Long[] patrolBackIds);

    /**
     * 根据巡查管理ID 查询一组 前场巡查 信息
     * @param patrolId 巡查管理ID
     * @return
     */
    List<ZxPatrolBack> findByPatrolId(Long patrolId);
}
