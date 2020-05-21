package com.djzhgd.module.material.service;

import com.djzhgd.module.material.domain.ZhgdMaterialDetection;
import com.djzhgd.module.material.domain.vo.ZhgdMaterialDetectionVo;
import com.djzhgd.module.result.Result;

import java.util.List;
import java.util.Map;

/**
 * 物料检测
 *
 * @Author: lgc
 * @Date: 2020/5/18 17:16
 */
public interface ZhgdMaterialDetectionService {

    /**
     * 查询检测结果
     *
     * @return
     */
    List<ZhgdMaterialDetection> queryGroupByNameAndResultAndBatchAndDTNoPass(Long deptId);

    Result saveDetection(ZhgdMaterialDetectionVo materialDetectionVo);

    /**
     * 查询检查结果详情
     *
     * @param id
     * @return
     */
    ZhgdMaterialDetection detailDetection(Integer id);

    boolean save(ZhgdMaterialDetection materialDetection);

    List<ZhgdMaterialDetection> queryAll(Map<String, Object> paraMap);


    /**
     * 分组获得审核数据
     * @return
     */
    List<ZhgdMaterialDetection> queryGroupByNameAndResultAndBatchNoPass(Long deptId);

    /**
     * 获取物料名称与批次,总数
     * @return
     */
    List<ZhgdMaterialDetection> queryGroupByNameAndBatch(Long deptId);

    /**
     * 查询检测结果
     * @return
     */
    List<ZhgdMaterialDetection> queryGroupByNameAndResultAndBatch(Long deptId);


    Result stockCheckDP(Long deptId);
}
