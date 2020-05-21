package com.djzhgd.project.system.service.impl;

import java.util.List;
import com.djzhgd.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.djzhgd.project.system.mapper.ZxPatrolBackMapper;
import com.djzhgd.project.system.domain.ZxPatrolBack;
import com.djzhgd.project.system.service.IZxPatrolBackService;

/**
 * 后场巡查Service业务层处理
 * 
 * @author suenle
 * @date 2020-03-12
 */
@Service
public class ZxPatrolBackServiceImpl implements IZxPatrolBackService 
{
    @Autowired
    private ZxPatrolBackMapper zxPatrolBackMapper;

    /**
     * 查询后场巡查
     * 
     * @param patrolBackId 后场巡查ID
     * @return 后场巡查
     */
    @Override
    public ZxPatrolBack selectZxPatrolBackById(Long patrolBackId)
    {
        return zxPatrolBackMapper.selectZxPatrolBackById(patrolBackId);
    }

    /**
     * 查询后场巡查列表
     * 
     * @param zxPatrolBack 后场巡查
     * @return 后场巡查
     */
    @Override
    public List<ZxPatrolBack> selectZxPatrolBackList(ZxPatrolBack zxPatrolBack)
    {
        return zxPatrolBackMapper.selectZxPatrolBackList(zxPatrolBack);
    }

    /**
     * 新增后场巡查
     * 
     * @param zxPatrolBack 后场巡查
     * @return 结果
     */
    @Override
    public int insertZxPatrolBack(ZxPatrolBack zxPatrolBack)
    {
        zxPatrolBack.setCreateTime(DateUtils.getNowDate());
        return zxPatrolBackMapper.insertZxPatrolBack(zxPatrolBack);
    }

    /**
     * 修改后场巡查
     * 
     * @param zxPatrolBack 后场巡查
     * @return 结果
     */
    @Override
    public int updateZxPatrolBack(ZxPatrolBack zxPatrolBack)
    {
        zxPatrolBack.setUpdateTime(DateUtils.getNowDate());
        return zxPatrolBackMapper.updateZxPatrolBack(zxPatrolBack);
    }

    /**
     * 批量删除后场巡查
     * 
     * @param patrolBackIds 需要删除的后场巡查ID
     * @return 结果
     */
    @Override
    public int deleteZxPatrolBackByIds(Long[] patrolBackIds)
    {
        return zxPatrolBackMapper.deleteZxPatrolBackByIds(patrolBackIds);
    }

    /**
     * 删除后场巡查信息
     * 
     * @param patrolBackId 后场巡查ID
     * @return 结果
     */
    @Override
    public int deleteZxPatrolBackById(Long patrolBackId)
    {
        return zxPatrolBackMapper.deleteZxPatrolBackById(patrolBackId);
    }
}
