package com.djzhgd.module.material.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.djzhgd.module.material.domain.ZhgdMaterialStockRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: lgc
 * @Date: 2020/5/18 15:31
 */
@Mapper
public interface ZhgdMaterialStockRecordMapper  extends BaseMapper<ZhgdMaterialStockRecord> {
    /**
     * 根据物料名称和物料类型查询库存
     * @param params
     * @return
     */
    List<ZhgdMaterialStockRecord> queryByTypeAndNameProId(Map<String, Object> params);

    /**
     * 查询库存总量
     * @param params
     * @return
     */
    List<ZhgdMaterialStockRecord> queryTypeFirstAndProId(Map<String, String> params);

    /**
     * 查询库存总量
     * @return
     */
    List<ZhgdMaterialStockRecord> queryGroupByMaterialNameAndProId(Integer tenantId);


    boolean save(ZhgdMaterialStockRecord zhgdMaterialStockRecord);

    int update(ZhgdMaterialStockRecord zhgdMaterialStockRecord);

    int delete(ZhgdMaterialStockRecord zhgdMaterialStockRecord);


    ZhgdMaterialStockRecord getById(Integer id);

    /**
     * 查询库存总量
     * @return
     */
    List<ZhgdMaterialStockRecord> queryGroupByMaterialName(Long deptId);

}
