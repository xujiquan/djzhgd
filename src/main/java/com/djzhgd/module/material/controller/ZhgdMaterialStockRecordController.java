package com.djzhgd.module.material.controller;

import com.djzhgd.framework.web.domain.ResultBean;
import com.djzhgd.module.material.domain.ZhgdMaterialStockRecord;
import com.djzhgd.module.material.service.ZhgdMaterialStockRecordService;
import com.djzhgd.module.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: lgc
 * @Date: 2020/5/19 13:30
 */
@RestController
@RequestMapping(value = "/zhgdmaterialstockrecord")
public class ZhgdMaterialStockRecordController {
    @Autowired
    private ZhgdMaterialStockRecordService zhgdMaterialStockRecordService;

    /**
     * 库存列表
     * @param pageResult
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public PageResult<ZhgdMaterialStockRecord> list(@RequestBody PageResult<ZhgdMaterialStockRecord> pageResult) {
        pageResult = zhgdMaterialStockRecordService.getlist(pageResult);
        return pageResult;
    }



    /**
     * 原材料统计接口
     */
    @RequestMapping(value = "stockBar", method = RequestMethod.POST)
    public ResultBean<Map<String, Object>> stockBar(HttpServletRequest request) {
        Long deptId = Long.valueOf(request.getHeader("deptId"));
        return new ResultBean<Map<String, Object>>(zhgdMaterialStockRecordService.getStockBar(deptId));
    }

    /**
     * 合格率统计接口<br>
     */
    @RequestMapping(value = "stockPie", method = RequestMethod.POST)
    public ResultBean<Map<String, Object>> stockPie(HttpServletRequest request) {
        Long deptId = Long.valueOf(request.getHeader("deptId"));
        return new ResultBean<Map<String, Object>>(zhgdMaterialStockRecordService.getStockPie(deptId));
    }

}
