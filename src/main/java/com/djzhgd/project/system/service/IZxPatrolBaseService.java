package com.djzhgd.project.system.service;

import java.util.List;
import java.util.Map;

import com.djzhgd.project.system.domain.ZxPatrolBase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 巡查管理Service接口
 *
 * @author suenle
 * @date 2020-03-12
 */
public interface IZxPatrolBaseService
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
     * 批量删除巡查管理
     *
     * @param patrolIds 需要删除的巡查管理ID
     * @return 结果
     */
    public int deleteZxPatrolBaseByIds(Long[] patrolIds);

    /**
     * 删除巡查管理信息
     *
     * @param patrolId 巡查管理ID
     * @return 结果
     */
    public int deleteZxPatrolBaseById(Long patrolId);

    /**
     * 生成word并下载
     * @param patrolId
     * @return
     */
    Map<String, Object> downFileWord(Long patrolId, HttpServletRequest request, HttpServletResponse response);

}
