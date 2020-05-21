package com.djzhgd.module.people.controller;
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

import com.djzhgd.framework.aspectj.lang.annotation.Log;
import com.djzhgd.framework.aspectj.lang.enums.BusinessType;
import com.djzhgd.framework.web.controller.BaseController;
import com.djzhgd.framework.web.domain.ResultBean;
import com.djzhgd.module.people.domain.ZhgdUser;
import com.djzhgd.module.people.service.IZhgdUserService;
import com.djzhgd.module.result.Result;
import com.djzhgd.module.result.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * ZhgdMeetingContent控制器<br>
 *
 * @author caoyx
 * @version 1.0, 2020-05-19
 * @see
 * @since 1.0
 */
@Controller
@RequestMapping("/demo/zhgduser")
public class ZhgdUserController extends BaseController {

    @Autowired
    private IZhgdUserService zhgduserservice;
    /**
     * 新增
     */
    @PreAuthorize("@ss.hasPermi('system:materialInfo:add')")
    @Log(title = "新增人员信息", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultBean add(@RequestBody ZhgdUser zhgduser) {
        return Contrast(zhgduserservice.save(zhgduser) ? 1 : 0);
    }
    /**
     * 上传文件、图片
     */
    @ResponseBody
    @RequestMapping("uploadfile")
    public Result uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile) {
        Result result = new Result();

        Map<String,Object> map = null;
        try {
            map = zhgduserservice.uploadFile(uploadFile);
            result.setData(map);
            result.setCode(ResultEnum.RESULT_INSERT_OK.getCode());
            result.setMsg(ResultEnum.RESULT_INSERT_OK.getMsg());
        } catch (IOException e) {
            result.setCode(ResultEnum.RESULT_INSERT_ERROR.getCode());
            result.setMsg(ResultEnum.RESULT_INSERT_ERROR.getMsg());
        }

        return result;
    }
}
