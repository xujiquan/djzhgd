package com.djzhgd.module.projectmanagement.controller;

import com.djzhgd.framework.aspectj.lang.annotation.Log;
import com.djzhgd.framework.aspectj.lang.enums.BusinessType;
import com.djzhgd.framework.web.controller.BaseController;
import com.djzhgd.framework.web.domain.ResultBean;
import com.djzhgd.module.projectmanagement.domain.Zhgdmeetingagenda;
import com.djzhgd.module.projectmanagement.service.ZhgdmeetingagendaService;
import com.djzhgd.module.projectmanagement.vo.ZhgdmeetingagendaVo;
import com.djzhgd.module.result.Result;
import com.djzhgd.module.result.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @PROJECT_NAME: dj_zhgd 东交智慧品控项目
 * @ClassName: zhgdmeetingagendaController
 * @Author: shenjiannang
 * @DATE: 2020/5/18
 * @Version 1.0
 **/
@RestController
@RequestMapping("demo/zhgdmeetingagenda")
public  class ZhgdmeetingagendaController extends BaseController {
    @Autowired
    private ZhgdmeetingagendaService zhgdmeetingagendaservice;


    /**
     * 修改
     */
    @Log(title = "修改项目会议节点", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public ResultBean edit(@RequestBody ZhgdmeetingagendaVo zhgdmeetingagenda) {
        return Contrast(zhgdmeetingagendaservice.updateandfile(zhgdmeetingagenda) ? 1 : 0);
    }
    /**
     * 获取详情信息
     */
    @PostMapping("/detail/{id}")
    public ResultBean<Zhgdmeetingagenda> getInfo(@PathVariable("id") Long id) {
        return new ResultBean<Zhgdmeetingagenda>(zhgdmeetingagendaservice.getById(id));
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
            map = zhgdmeetingagendaservice.uploadFile(uploadFile);
            if(map.isEmpty()){
                result.setData(map);
                result.setCode(ResultEnum.RESULT_INSERT_ERROR.getCode());
                result.setMsg(ResultEnum.RESULT_INSERT_ERROR.getMsg());
            }else {
                result.setData(map);
                result.setCode(ResultEnum.RESULT_INSERT_OK.getCode());
                result.setMsg(ResultEnum.RESULT_INSERT_OK.getMsg());
            }
        } catch (IOException e) {
                result.setCode(ResultEnum.RESULT_INSERT_ERROR.getCode());
                result.setMsg(ResultEnum.RESULT_INSERT_ERROR.getMsg());
        }
        return result;
    }
    @ResponseBody
    @RequestMapping("/downloadFile/{id}")
    public void downloadFile(@PathVariable("id") Integer id) {
        zhgdmeetingagendaservice.downloadFile(id);
    }

}
