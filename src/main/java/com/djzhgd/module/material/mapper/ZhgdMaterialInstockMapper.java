package com.djzhgd.module.material.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.djzhgd.module.material.domain.ZhgdMaterialInstock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: lgc
 * @Date: 2020/5/18 14:37
 */
@Mapper
public interface ZhgdMaterialInstockMapper extends BaseMapper<ZhgdMaterialInstock> {
    /**
     * 根据批次号查询是否数据已存在
     * @param paramMap
     * @return
     */
    List<ZhgdMaterialInstock> queryInStockByBatchAndProId(Map<String, Object> paramMap);

    /**
     * 查询入库总量数据汇总
     * @return
     */
    List<ZhgdMaterialInstock> queryGroupByMaterialNameAndProId(Long deptId);


    Integer save(ZhgdMaterialInstock zhgdMaterialInstock);

    ZhgdMaterialInstock getById(Integer id);

    Integer update(ZhgdMaterialInstock zhgdMaterialInstock);


    /**
     * 查询入库总量数据汇总
     * @return
     */
    List<ZhgdMaterialInstock> queryGroupByMaterialName(Long deptId);

    List<ZhgdMaterialInstock> queryAllByDeptId(Long deptId);

}
