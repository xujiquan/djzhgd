package com.djzhgd.module.projectmanagement.controller;

import com.djzhgd.framework.web.domain.AjaxResult;
import com.djzhgd.framework.web.domain.ResultBean;
import com.djzhgd.module.projectmanagement.service.IZhgdAccountMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * @PROJECT_NAME: dj_zhgd 东交智慧品控项目
 * @ClassName: ZhgdDocumentController
 * @Author: zw
 * @DATE: 2020/5/19 10:44
 * @Version 1.0
 **/
@Controller
@RequestMapping("zhgdAccountMap")
public class ZhgdAccountMapController {

    @Autowired
    private IZhgdAccountMapService zhgdDocumentService;

    @RequestMapping(value = "documentManage")
    @ResponseBody
    public AjaxResult documentManage(HttpServletRequest request, @RequestBody String inParam)throws ParseException {
        AjaxResult ajax = zhgdDocumentService.documentManage(request,inParam);
        return ajax;
    }

    @RequestMapping(value = "circulationStatistics")
    @ResponseBody
    public AjaxResult circulationStatistics(HttpServletRequest request, @RequestBody String inParam){
        AjaxResult ajax = zhgdDocumentService.circulationStatistics(request,inParam);
        return ajax;
    }

    @RequestMapping(value = "getOtherDetails")
    @ResponseBody
    public ResultBean getOtherDetails(HttpServletRequest request, @RequestBody String inParam){
        return zhgdDocumentService.getOtherDetails(request,inParam);
    }

}
