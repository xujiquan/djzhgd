package com.djzhgd.project.system.service;

import java.util.List;
import com.djzhgd.project.system.domain.JlPartition;

/**
 * 测试Service接口
 * 
 * @author suenle
 * @date 2020-03-12
 */
public interface IJlPartitionService 
{
    /**
     * 查询测试
     * 
     * @param id 测试ID
     * @return 测试
     */
    public JlPartition selectJlPartitionById(Long id);

    /**
     * 查询测试列表
     * 
     * @param jlPartition 测试
     * @return 测试集合
     */
    public List<JlPartition> selectJlPartitionList(JlPartition jlPartition);

    /**
     * 新增测试
     * 
     * @param jlPartition 测试
     * @return 结果
     */
    public int insertJlPartition(JlPartition jlPartition);

    /**
     * 修改测试
     * 
     * @param jlPartition 测试
     * @return 结果
     */
    public int updateJlPartition(JlPartition jlPartition);

    /**
     * 批量删除测试
     * 
     * @param ids 需要删除的测试ID
     * @return 结果
     */
    public int deleteJlPartitionByIds(Long[] ids);

    /**
     * 删除测试信息
     * 
     * @param id 测试ID
     * @return 结果
     */
    public int deleteJlPartitionById(Long id);
}
