package com.djzhgd.module.material.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.djzhgd.module.material.domain.ZhgdMaterialDetection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: lgc
 * @Date: 2020/5/18 17:19
 */
@Mapper
public interface ZhgdMaterialDetectionMapper extends BaseMapper<ZhgdMaterialDetection> {
    /**
     * 查询检测结果
     * @return
     */
    List<ZhgdMaterialDetection> queryGroupByNameAndResultAndBatchAndDTNoPass(Long deptId);

    boolean save(ZhgdMaterialDetection zhgdMaterialDetection);

    List<ZhgdMaterialDetection> getByInStockBatchAndTenId(Map<String, Object> inParam);

    ZhgdMaterialDetection getById(Integer id);

    List<ZhgdMaterialDetection> queryAllByMap(Map<String, Object> paraMap);


    /**
     * 分组获得审核数据
     * @return
     */
    List<ZhgdMaterialDetection> queryGroupByNameAndResultAndBatchNoPass(Long deptId);

    /**
     *  获取物料名称与批次,总数
     * @return
     */
    List<ZhgdMaterialDetection> queryGroupByNameAndBatch(Long deptId);

    /**
     * 分组查询检测结果
     * @return
     */
    List<ZhgdMaterialDetection> queryGroupByNameAndResultAndBatch(Long deptId);

    List<ZhgdMaterialDetection> stockCheckByDeptId(Long deptId);

}
