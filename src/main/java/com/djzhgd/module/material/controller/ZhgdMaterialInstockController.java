package com.djzhgd.module.material.controller;

import com.alibaba.fastjson.JSONObject;
import com.djzhgd.framework.web.domain.ResultBean;
import com.djzhgd.module.material.domain.ZhgdMaterialDetection;
import com.djzhgd.module.material.domain.ZhgdMaterialInstock;
import com.djzhgd.module.material.domain.vo.ZhgdMaterialDetectionVo;
import com.djzhgd.module.material.domain.vo.ZhgdMaterialInstockVo;
import com.djzhgd.module.material.service.ZhgdMaterialDetectionService;
import com.djzhgd.module.material.service.ZhgdMaterialInstockService;
import com.djzhgd.module.result.PageResult;
import com.djzhgd.module.result.PageResultSupple;
import com.djzhgd.module.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * @Author: lgc
 * @Date: 2020/5/18 11:43
 */
@RestController
@RequestMapping(value = "/zhgdmaterialinstock")
public class ZhgdMaterialInstockController {

    @Autowired
    private ZhgdMaterialInstockService zhgdMaterialInstockService;
    @Autowired
    private ZhgdMaterialDetectionService zhgdMaterialDetectionService;
    /**
     * 物料入库操作
     *
     * @param materialInstockVo
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody ZhgdMaterialInstockVo materialInstockVo, HttpServletRequest request) {
        Long deptId = Long.valueOf(request.getHeader("deptId"));
        Result result = zhgdMaterialInstockService.saveInStockData(materialInstockVo,deptId);
        return result;
    }

    /**列表
     * @param pageResult
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public PageResult<ZhgdMaterialInstock> list(@RequestBody PageResult<ZhgdMaterialInstock> pageResult,HttpServletRequest request) {
        Long deptId = Long.valueOf(request.getHeader("deptId"));
        pageResult = zhgdMaterialInstockService.getlist(pageResult,deptId);
        return pageResult;
    }


    /**
     * 根据id 获取改物料的其他属性
     * @param inParam
     * @return
     */
    @RequestMapping(value = "queryInStockById", method = RequestMethod.POST)
    public Result queryInStockById(@RequestBody String inParam){
        Result result = zhgdMaterialInstockService.queryInStockById(inParam);
        return result;
    }

    /** TODO 推送没定
     * 保存检测信息 （施工单位，监理，中心试验）
     */
    @RequestMapping(value = "saveDetection", method = RequestMethod.POST)
    public Result saveDetection(ZhgdMaterialDetectionVo materialDetectionVo,HttpServletRequest request) {
        Long deptId = Long.valueOf(request.getHeader("deptId"));
        materialDetectionVo.getEntity().setDeptId(deptId);
        Result result = zhgdMaterialDetectionService.saveDetection(materialDetectionVo);
        return result;
    }



    /**TODO 详情待补充完整(图片待定)
     * 查看原材料入库详情
     */
    @RequestMapping(value = "detail", method = RequestMethod.POST )
    public ResultBean<Map<String, Object>> detail(@RequestBody String inParam,HttpServletRequest request) {
        JSONObject object = JSONObject.parseObject(inParam);
        Integer id = object.getInteger("id");
        return new ResultBean<Map<String, Object>>(zhgdMaterialInstockService.detail(id));
    }

    /**
     * 查询检查结果详情
     */
    @RequestMapping(value = "detailDetection", method = RequestMethod.POST )
    public ResultBean<ZhgdMaterialDetection> detialDetection(@RequestBody String inParam,HttpServletRequest request) {
        JSONObject object = JSONObject.parseObject(inParam);
        Integer id = object.getInteger("id");
        return new ResultBean<ZhgdMaterialDetection>(zhgdMaterialDetectionService.detailDetection(id));
    }




    /**
     * 查看检测信息 （施工单位，监理，中心试验）
     */
    @RequestMapping(value = "queryDetection", method = RequestMethod.POST)
    public PageResultSupple<Map<String, Object>> queryDetection(@RequestBody PageResultSupple<Map<String, Object>> pageResult, HttpServletRequest request) {
        Long deptId = Long.valueOf(request.getHeader("deptId"));
        pageResult = zhgdMaterialInstockService.queryDetection(pageResult,deptId);
        return pageResult;
    }





}
