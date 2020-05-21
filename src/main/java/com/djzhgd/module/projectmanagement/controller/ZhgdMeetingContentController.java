/**
 * Description: ZhgdMeetingContent控制器
 * Copyright:   Copyright (c)2020
 * Company:     envbase
 * @author:     caoyx
 * @version:    1.0
 * Create at:   2020-05-19 下午 14:52:13
 *  
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2020-05-19   caoyx   1.0         Initial
 */
package com.djzhgd.module.projectmanagement.controller;

import com.djzhgd.framework.aspectj.lang.annotation.Log;
import com.djzhgd.framework.aspectj.lang.enums.BusinessType;
import com.djzhgd.framework.web.controller.BaseController;
import com.djzhgd.framework.web.domain.ResultBean;
import com.djzhgd.framework.web.page.PageReq;
import com.djzhgd.module.projectmanagement.domain.ZhgdMeetingContent;
import com.djzhgd.module.projectmanagement.service.IZhgdMeetingContentService;
import com.djzhgd.module.projectmanagement.vo.ZhgdMeetingContentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * ZhgdMeetingContent控制器<br>
 * 
 * @author caoyx
 * @version 1.0, 2020-05-19
 * @see
 * @since 1.0
 */
@RequestMapping("demo/zhgdmeetingcontent")
@RestController
public class ZhgdMeetingContentController extends BaseController {

    @Autowired
    private IZhgdMeetingContentService izhgdmeetingcontentservice;

    /* 项目会议列表
    */
    @PostMapping("/list")
    public ResultBean<PageReq> list(@RequestBody ZhgdMeetingContent zhgdmeetingcontent) {
        return new ResultBean<PageReq>(getPage(izhgdmeetingcontentservice.list(zhgdmeetingcontent)));
    }

    /**
     *  同时新增ZhgdMeetingContent 和 Zhgdmeetingagenda表
     */
    @PostMapping("/add")
    public ResultBean add(@RequestBody ZhgdMeetingContentVo zhgdmeetingcontentvo) {
        return Contrast(izhgdmeetingcontentservice.savecontentandagenda(zhgdmeetingcontentvo,zhgdmeetingcontentvo.getZhgdMeetingAgendas()) ? 1 : 0);
    }

    /**
     * 获取项目会议信息和节点信息
     */
    @PostMapping("/detail/{id}")
    public ResultBean<ZhgdMeetingContent> getInfo(@PathVariable("id") Long id) {
        return new ResultBean<ZhgdMeetingContent>(izhgdmeetingcontentservice.contentandagenda(id));
    }

    /**
     * 设置主持人
     */
    @PostMapping(value = "synchronousupdate")
    public ResultBean<Integer> synchronousUpdate(Integer meetingId, Integer currentNodeId, Integer zhgdUserId) {
        return new ResultBean<Integer>(izhgdmeetingcontentservice.synchronousUpdate(meetingId,currentNodeId,zhgdUserId));
    }

    /**
     * 删除项目会议列表 和节点数据 和文件
     */
    @Log(title = "删除", businessType = BusinessType.DELETE)
    @PostMapping("/delete/{id}")
    public ResultBean remove(@PathVariable Integer id) {
        return Contrast(izhgdmeetingcontentservice.removeandothers(id) ? 1 : 0);
    }
}
