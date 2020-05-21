package com.djzhgd.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.djzhgd.project.system.domain.ZxPatrolBack;
import com.djzhgd.project.system.domain.ZxPatrolBase;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 巡查管理Mapper接口
 *
 * @author suenle
 * @date 2020-03-12
 */
@Repository
public interface ZxPatrolBaseMapper  extends BaseMapper<ZxPatrolBase>
{
    /**
     * 查询巡查管理
     *
     * @param patrolId 巡查管理ID
     * @return 巡查管理
     */
    public ZxPatrolBase selectZxPatrolBaseById(Long patrolId);

    /**
     * 查询巡查管理列表
     *
     * @param zxPatrolBase 巡查管理
     * @return 巡查管理集合
     */
    public List<ZxPatrolBase> selectZxPatrolBaseList(ZxPatrolBase zxPatrolBase);

    /**
     * 新增巡查管理
     *
     * @param zxPatrolBase 巡查管理
     * @return 结果
     */
    public int insertZxPatrolBase(ZxPatrolBase zxPatrolBase);

    /**
     * 修改巡查管理
     *
     * @param zxPatrolBase 巡查管理
     * @return 结果
     */
    public int updateZxPatrolBase(ZxPatrolBase zxPatrolBase);

    /**
     * 删除巡查管理
     *
     * @param patrolId 巡查管理ID
     * @return 结果
     */
    public int deleteZxPatrolBaseById(Long patrolId);

    /**
     * 批量删除巡查管理
     *
     * @param patrolIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteZxPatrolBaseByIds(Long[] patrolIds);

    /**
     * 根据deptId 查询一组 巡查管理 信息
     * @param deptId
     * @return
     */
    List<ZxPatrolBase> findBydeptId(Long deptId, String createDate);
}
