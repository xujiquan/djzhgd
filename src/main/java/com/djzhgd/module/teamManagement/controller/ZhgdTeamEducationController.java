package com.djzhgd.module.teamManagement.controller;

import com.djzhgd.framework.web.controller.BaseController;
import com.djzhgd.framework.web.domain.ResultBean;
import com.djzhgd.framework.web.page.PageReq;
import com.djzhgd.module.result.Result;
import com.djzhgd.module.teamManagement.domain.ZhgdTeamEducation;
import com.djzhgd.module.teamManagement.service.ZhgdTeamEducationService;
import com.djzhgd.module.teamManagement.vo.ZhgdTeamEducationVo;
import com.djzhgd.module.utils.RequestHeaderUtils;
import com.djzhgd.project.system.service.ZhgdFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: ZhgdTeamEducationController
 * @Author: zhangheng
 * @DATE: 2020/5/20 17:05
 * @Version 1.0
 **/
@RestController
@RequestMapping("/demo/zhgdTeamEducation")
public class ZhgdTeamEducationController extends BaseController {

    // 班前教育文件需要保存的位置
    @Value("${djzhgd.uploadFile.teamEducation}")
    private String teamEducation;
    @Autowired
    private ZhgdTeamEducationService zhgdTeamEducationService;
    @Autowired
    private ZhgdFileService zhgdFileService;

    /**
     * 班前教育查询集合
     * @param zhgdTeamEducationVo
     * @return
     * @throws ParseException
     */
    @GetMapping("/list")
    public ResultBean<PageReq> list(ZhgdTeamEducationVo zhgdTeamEducationVo) throws ParseException {
        return new ResultBean<PageReq>(getPage(zhgdTeamEducationService.list(zhgdTeamEducationVo)));
    }

    /**
     * 根据班前教育ID查询详情
     * @param zhgdTeamEducation
     * @return
     */
    @RequestMapping("/getZhgdTeamEducationDetail")
    public Result getZhgdTeamEducationDetail(@RequestBody ZhgdTeamEducation zhgdTeamEducation){
        Result result = zhgdTeamEducationService.getZhgdTeamEducationDetail(zhgdTeamEducation);
        return result;
    }

    /**
     * 新增一条班前教育数据
     * @param zhgdTeamEducation
     * @return
     */
    @RequestMapping("/addZhgdTeamEducation")
    public Result addZhgdTeamEducation(@RequestBody ZhgdTeamEducation zhgdTeamEducation, HttpServletRequest request) {
        Result result = zhgdTeamEducationService.addZhgdTeamEducation(zhgdTeamEducation, request);
        return result;
    }

    /**
     * 修改一条班前教育数据
     * @param zhgdTeamEducation
     * @return
     */
    @RequestMapping("/updateZhgdTeamEducation")
    public Result updateZhgdTeamEducation(@RequestBody ZhgdTeamEducation zhgdTeamEducation, HttpServletRequest request){
        Result result = zhgdTeamEducationService.updateZhgdTeamEducation(zhgdTeamEducation, request);
        return result;
    }

    /**
     * 根据ID批量删除班前教育数据
     * @param idList
     * @return
     */
    @RequestMapping("/deleteById")
    public Result deleteById(@RequestBody List<Long> idList){
        Result result = zhgdTeamEducationService.deleteById(idList);
        return result;
    }

    /**
     * 附件、图片上传
     * @param uploadFile 上传文件
     * @param form       文件来源
     * @param fileType   文件类型
     * @param request
     * @throws IOException
     */
    @RequestMapping("/uploadFile")
    public ResultBean<Map<String, Object>> uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile, Integer form, Integer fileType, HttpServletRequest request) throws IOException {
        Long deptId = RequestHeaderUtils.getDeptId(request);
        return new ResultBean<>(zhgdFileService.uploadFile(uploadFile, deptId, form, fileType, teamEducation));
    }

    /**
     * 根据附件ID下载附件
     * @param id
     * @param response
     * @throws Exception
     */
    @RequestMapping("download/{id}")
    public void download(@PathVariable("id") Integer id, HttpServletResponse response) throws Exception {
        zhgdFileService.download(id, response);
    }


}
