package com.djzhgd.module.material.service;

import com.djzhgd.module.material.domain.ZhgdMaterialInstock;
import com.djzhgd.module.material.domain.vo.ZhgdMaterialInstockVo;
import com.djzhgd.module.result.PageResult;
import com.djzhgd.module.result.PageResultSupple;
import com.djzhgd.module.result.Result;

import java.util.List;
import java.util.Map;


/**
 * @Author: lgc
 * @Date: 2020/5/18 14:02
 */
public interface ZhgdMaterialInstockService {
    Result saveInStockData(ZhgdMaterialInstockVo materialInstockVo,Long deptId);

    Map<String, Object> changeDataToDUN(ZhgdMaterialInstock inStockData);


    /**
     * 查询列表
     * @param
     * @return
     */
    PageResult<ZhgdMaterialInstock> getlist(PageResult<ZhgdMaterialInstock> pageResult,Long deptId);


    Result queryInStockById(String inParam);

    /**
     * 查看
     * @param id
     * @return
     */
    Map<String, Object> detail(Integer id);


    /**
     * 查询检测信息
     * @return
     */
    PageResultSupple< Map<String, Object>>  queryDetection(PageResultSupple<Map<String, Object>> pageResult, Long deptId);

    /**
     * 查询入库总量数据汇总
     * @return
     */
    List<ZhgdMaterialInstock> queryGroupByMaterialName(Long deptId);

}
