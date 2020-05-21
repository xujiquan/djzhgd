package com.djzhgd.module.projectmanagement.service;

import com.djzhgd.framework.web.domain.AjaxResult;
import com.djzhgd.framework.web.domain.ResultBean;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: IZhgdAnnouncementService
 * @Author: zhangheng
 * @DATE: 2020/5/13 10:42
 * @Version 1.0
 **/
public interface IZhgdAccountMapService {

    /*公文管理头部统计*/
    AjaxResult documentManage(HttpServletRequest request,String parameter) throws ParseException;

    /*各部门发文收文统计*/
    AjaxResult circulationStatistics(HttpServletRequest request,String parameter);

    /*查看详情*/
    ResultBean getOtherDetails(HttpServletRequest request,String parameter);
}
