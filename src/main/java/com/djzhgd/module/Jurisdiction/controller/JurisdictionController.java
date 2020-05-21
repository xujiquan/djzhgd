package com.djzhgd.module.Jurisdiction.controller;

import com.djzhgd.module.Jurisdiction.service.JurisdictionService;
import com.djzhgd.module.Jurisdiction.vo.Jurisdiction;
import com.djzhgd.module.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: JurisdictionController
 * @Author: zhangheng
 * @DATE: 2020/5/19 9:12
 * @Version 1.0
 **/
@RestController
@RequestMapping("/jurisdiction")
public class JurisdictionController {

    @Autowired
    private JurisdictionService jurisdictionService;

    /**
     * 根据用户ID查询菜单权限
     * @param jurisdiction
     * @return
     */
    @RequestMapping("/getSysMenuPerms")
    public Result getSysMenuPerms(@RequestBody Jurisdiction jurisdiction){
        Result result = jurisdictionService.getSysMenuPerms(jurisdiction);
        return result;
    }

    /**
     * 通过用户ID查询平台、项目信息
     * @param jurisdiction
     * @return
     */
    @RequestMapping("/getDeptList")
    public Result getDeptList(@RequestBody Jurisdiction jurisdiction){
        Result result = jurisdictionService.getDeptList(jurisdiction);
        return result;
    }

    /**
     * 根据平台ID查询平台下的项目
     * @param jurisdiction
     * @return
     */
    @RequestMapping("/getProjectList")
    public Result getProjectList(@RequestBody Jurisdiction jurisdiction){
        Result result = jurisdictionService.getProjectList(jurisdiction);
        return result;
    }

    /**
     * 获取用户权限及以下的所有单位
     * @param request
     * @return
     */
    @RequestMapping("/getAllUnit")
    public Result getAllUnit(HttpServletRequest request){
        Result result = jurisdictionService.getAllUnit(request);
        return result;
    }

    /**
     * 获取用户权限及以下所有施工单位和标段
     * @param request
     * @return
     */
    @RequestMapping("/getBidSection")
    public Result getBidSection(HttpServletRequest request){
        Result result = jurisdictionService.getBidSection(request);
        return result;
    }


}
