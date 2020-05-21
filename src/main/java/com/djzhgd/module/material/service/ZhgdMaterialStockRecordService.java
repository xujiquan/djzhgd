package com.djzhgd.module.material.service;

import com.djzhgd.module.material.domain.ZhgdMaterialStockRecord;
import com.djzhgd.module.result.PageResult;

import java.util.List;
import java.util.Map;

/**入库记录
 * @Author: lgc
 * @Date: 2020/5/18 15:14
 */
public interface ZhgdMaterialStockRecordService{
    /**
     * 根据物料类型和名称查询库存
     * @param materialType
     * @param materialName
     * @param bidsName
     * @param proId
     * @return
     */
    List<ZhgdMaterialStockRecord> queryByTypeAndNameProId(String materialType, String materialName, String bidsName,Integer proId,Long deptId);

    boolean save(ZhgdMaterialStockRecord zhgdMaterialStockRecord);

    int update(ZhgdMaterialStockRecord zhgdMaterialStockRecord);

    PageResult<ZhgdMaterialStockRecord> getlist(PageResult<ZhgdMaterialStockRecord> pageResult);


    /**
     * 库存数量统计
     * @return
     */
    Map<String, Object> getStockBar(Long deptId);

    /**
     * 合格率统计接口
     * @return
     */
    Map<String, Object> getStockPie(Long deptId);


    Map<String, Object> getStockBarNew(Long deptId);

    Map<String, Object> getStockPieNew(Long deptId);

}
