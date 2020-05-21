/*
package com.djzhgd.module.device.controller;

import com.alibaba.fastjson.JSONObject;
import com.djzhgd.framework.web.domain.ResultBean;
import com.djzhgd.framework.web.page.PageReq;
import com.djzhgd.module.device.domain.ZhgdDevice;
import com.djzhgd.module.device.service.IZhgdDeviceService;
import com.djzhgd.module.device.vo.ZhgdDeviceVo;
import com.djzhgd.module.projectmanagement.vo.ZhgdAnnouncementVo;
import com.djzhgd.module.result.PageResult;
import com.djzhgd.module.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

*/
/**
 * @PROJECT_NAME: dj_zhgd 东交智慧品控项目
 * @ClassName: ZhgdDeviceController
 * @Author: xjq
 * @DATE: 2020/5/18 10:44
 * @Version 1.0
 **//*

@Slf4j
@Controller
@RequestMapping("zhgdDevice")
public class ZhgdDeviceController {





    */
/**
     * 自动注入ZhgdDevice业务层实现
     *//*

    @Autowired
    private IZhgdDeviceService<ZhgdDevice> zhgdDeviceService;

    @ResponseBody
    @PostMapping("getlist")
    public PageResult<ZhgdDevice> list(@RequestBody PageResult<ZhgdDevice> pageResult) {
        pageResult = zhgdDeviceService.list(pageResult);
        return pageResult;
    }


    */
/**
     * 新增
     *
     *//*

    @PostMapping("save")
    @ResponseBody
    public Result saveSafeActivity(ZhgdDevice zhgdDevice, Integer deptId) {


        zhgdDeviceService.saveDevice(zhgdDeviceVo.getEntity(),deptId)

        return new ResultBean<Integer>();
    }

    */
/**
     * 修改
     *
     *//*

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean<Integer> update(ZhgdDeviceVo zhgdDeviceVo, String startTime) {
        return new ResultBean<Integer>(zhgdDeviceService.updateDevice(zhgdDeviceVo.getEntity(),startTime));
    }
    */
/**
     * 删除
     *//*

    @ResponseBody
    @RequestMapping("delete/{id}")
    public ResultBean<Integer> delete(@PathVariable("id") Integer id) {
        return new ResultBean<Integer>(zhgdDeviceService.deleted(id));
    }
    */
/**
     * 上传文件、图片
     *//*

    @ResponseBody
    @RequestMapping("uploadfile")
    public ResultBean<Map<String,String>> uploadFile(@RequestParam("file") MultipartFile file) {
        return new ResultBean<Map<String,String>>(zhgdDeviceService.uploadFile(file));
    }

    */
/**
     * 根据id获取文件
     *//*

    @ResponseBody
    @RequestMapping("download/{id}")
    public ResultBean<Integer> download(@PathVariable("id") Integer id, HttpServletResponse response) {
        return new ResultBean<Integer>(zhgdDeviceService.download(id, response));
    }

    */
/**
     * 根据附件名称获取文件
     *//*

    @ResponseBody
    @RequestMapping("downloadFile")
    public ResultBean<String> downloadFile(String name, HttpServletResponse response) {
        return new ResultBean<String>(zhgdDeviceService.downloadFile(name, response));
    }

    */
/**
     * 详情
     *//*

    @RequestMapping(value = "getDetail/{id}")
    @ResponseBody
    public ResultBean<ZhgdDevice> getDetail(@PathVariable("id") Integer id) {
        return new ResultBean<ZhgdDevice>(zhgdDeviceService.getDetail(id));
    }

    */
/**
     * 详情
     *//*

    @RequestMapping(value = "detail/{id}")
    @ResponseBody
    public ResultBean<ZhgdDevice> detail(@PathVariable("id") Integer id) {
        return new ResultBean<ZhgdDevice>(zhgdDeviceService.detail(id));
    }

    */
/**
     * 检查台账导出
     *//*

    @RequestMapping(value = "exportExcelByDeptId")
    @ResponseBody
    public ResultBean<Integer> exportExcelByDeptId(String deptid,HttpServletResponse response) {
        return new ResultBean<Integer>(zhgdDeviceService.exportExcelByDeptId(Integer.valueOf(deptid),response));
    }

    */
/**
     * 统计页面
     *
     *//*

    @ResponseBody
    @RequestMapping(value = "deviceCount")
    public ResultBean<List<ZhgdDevice>> trainCount(String startTime,String endTime,Integer type) {
        return new ResultBean<List<ZhgdDevice>>(zhgdDeviceService.getDeviceCount(startTime, endTime, type));
    }

    */
/**
     * 打印查询
     *
     *//*

    @ResponseBody
    @RequestMapping(value = "printQuery")
    public ResultBean<List<ZhgdDevice>> printQuery(@RequestBody String inParam) {
        JSONObject object = JSONObject.parseObject(inParam);
        String ids = object.getString("ids");
        return new ResultBean<List<ZhgdDevice>>(zhgdDeviceService.getPrint(ids));
    }

    @ResponseBody
    @RequestMapping(value = "getDeviceByDeptCode")
    public ResultBean<PageResp<ZhgdDevice>> getDeviceByDeptCode(@RequestBody PageReq pageReq) {
        return new ResultBean<PageResp<ZhgdDevice>>(zhgdDeviceService.getDeviceByDeptCode(pageReq));
    }

    @ResponseBody
    @RequestMapping(value = "getEquInfo")
    public ResultBean<List<ZhgdDevice>> getEquInfo(@RequestBody String inParam) {
        JSONObject object = JSONObject.parseObject(inParam);
        String ids = object.getString("ids");
        return new ResultBean<List<ZhgdDevice>>(zhgdDeviceService.getEquInfo(ids));
    }

    @ResponseBody
    @RequestMapping("downloadQRFile")
    public ResultBean<String> downloadQRFile(String name, HttpServletResponse response) {
        return new ResultBean<String>(zhgdDeviceService.downloadQRFile(name, response));
    }

    @ResponseBody
    @RequestMapping(value = "devicesbxx")
    public String devicesbxx(HttpServletRequest request, @RequestBody String inParam) throws ParseException {
        Map<String, Object> response = zhgdDeviceService.getDeviceData();
        return JSONObject.toJSONString(response);
    }

}
*/
