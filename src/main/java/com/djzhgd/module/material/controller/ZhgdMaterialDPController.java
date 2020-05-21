package com.djzhgd.module.material.controller;

import com.djzhgd.module.material.service.ZhgdMaterialDetectionService;
import com.djzhgd.module.material.service.ZhgdMaterialInstockService;
import com.djzhgd.module.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: lgc
 * @Date: 2020/5/21 9:57
 */
@RestController
@RequestMapping("zhgdMaterialDP")
public class ZhgdMaterialDPController {
    @Autowired
    private ZhgdMaterialDetectionService zhgdMaterialDetectionService;
    @Autowired
    private ZhgdMaterialInstockService zhgdMaterialInstockService;


    /**
     * 物料检测
     *
     * @return
     */
    @RequestMapping(value = "stockCheck",method = RequestMethod.POST)
    public Result stockCheck(HttpServletRequest request){
        Long deptId = Long.valueOf(request.getHeader("deptId"));
        Result result = zhgdMaterialDetectionService.stockCheckDP(deptId);
        return result;
    }




}
