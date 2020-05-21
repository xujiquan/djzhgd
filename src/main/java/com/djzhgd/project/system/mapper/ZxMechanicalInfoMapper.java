package com.djzhgd.project.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.djzhgd.framework.aspectj.lang.annotation.DataScope;
import com.djzhgd.project.system.domain.ZxMechanicalInfo;
import org.springframework.stereotype.Repository;

/**
 * 机械管理Mapper接口
 *
 * @author suenle
 * @date 2020-02-27
 */
@Repository
public interface ZxMechanicalInfoMapper   extends BaseMapper<ZxMechanicalInfo>
{
    /**
     * 查询机械管理
     *
     * @param mechanicalId 机械管理ID
     * @return 机械管理
     */
    public ZxMechanicalInfo selectZxMechanicalInfoById(Long mechanicalId);

    /**
     * 查询机械管理列表
     *
     * @param zxMechanicalInfo 机械管理
     * @return 机械管理集合
     */
    public List<ZxMechanicalInfo> selectZxMechanicalInfoList(ZxMechanicalInfo zxMechanicalInfo);

    /**
     * 新增机械管理
     *
     * @param zxMechanicalInfo 机械管理
     * @return 结果
     */
    public int insertZxMechanicalInfo(ZxMechanicalInfo zxMechanicalInfo);

    /**
     * 修改机械管理
     *
     * @param zxMechanicalInfo 机械管理
     * @return 结果
     */
    public int updateZxMechanicalInfo(ZxMechanicalInfo zxMechanicalInfo);

    /**
     * 删除机械管理
     *
     * @param mechanicalId 机械管理ID
     * @return 结果
     */
    public int deleteZxMechanicalInfoById(Long mechanicalId);

    /**
     * 批量删除机械管理
     *
     * @param mechanicalIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteZxMechanicalInfoByIds(Long[] mechanicalIds);
}
