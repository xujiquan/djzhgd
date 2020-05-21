/**
 * Description: ZhgdMeetingSign控制器
 * Copyright:   Copyright (c)2020
 * Company:     envbase
 * @author:     caoyx
 * @version:    1.0
 * Create at:   2020-05-19 下午 14:52:14
 *  
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2020-05-19   caoyx   1.0         Initial
 */
package com.djzhgd.module.projectmanagement.controller;

import com.djzhgd.framework.web.controller.BaseController;
import com.djzhgd.framework.web.domain.ResultBean;
import com.djzhgd.module.projectmanagement.service.IZhgdMeetingSignService;
import com.djzhgd.module.projectmanagement.vo.ZhgdMeetingSignVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


/**
 * ZhgdMeetingSign控制器<br>
 * 
 * @author caoyx
 * @version 1.0, 2020-05-19
 * @see
 * @since 1.0
 */
@RestController
@RequestMapping("demo/zhgdmeetingsign")
public class ZhgdMeetingSignController extends BaseController {
    @Autowired
    private  IZhgdMeetingSignService  izhgdmeetingsignservice;
    /**
     *  签到
     */
    @PostMapping(value = "/meetingSignBook")
    @ResponseBody
    public ResultBean<String> meetingSignBook(ZhgdMeetingSignVo zhgdmeetingsignvo) {
        return Contrast(izhgdmeetingsignservice.meetingSignBook(zhgdmeetingsignvo) ? 1 : 0);
    }

    /*
      * 根据id获取文件 导出
     */
    @ResponseBody
    @RequestMapping("exportexcel/{id}")
    public void exportExcel(@PathVariable("id") Integer meetingId, HttpServletResponse response) {
       izhgdmeetingsignservice.exportExcel(meetingId,response);
    }

}
