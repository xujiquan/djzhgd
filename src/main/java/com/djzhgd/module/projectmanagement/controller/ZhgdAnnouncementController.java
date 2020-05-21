package com.djzhgd.module.projectmanagement.controller;

import com.djzhgd.framework.web.controller.BaseController;
import com.djzhgd.framework.web.domain.ResultBean;
import com.djzhgd.framework.web.page.PageReq;
import com.djzhgd.module.projectmanagement.service.IZhgdAnnouncementService;
import com.djzhgd.module.projectmanagement.vo.ZhgdAnnouncementVo;
import com.djzhgd.module.utils.RequestHeaderUtils;
import com.djzhgd.project.system.service.ZhgdFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @PROJECT_NAME: dj_zhgd 东交智慧品控项目
 * @ClassName: ZhgdAnnouncementController
 * @Author: xjq
 * @DATE: 2020/5/18 10:44
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("demo/zhgdAnnouncement")
public class ZhgdAnnouncementController extends BaseController {

    @Autowired
    private IZhgdAnnouncementService zhgdAnnouncementService;
    @Value("djzhgd.upload-file.announcement")
    private String announcement;
    @Autowired
    private ZhgdFileService zhgdFileService;


    @GetMapping("list")
    public ResultBean<PageReq> list(ZhgdAnnouncementVo announcementVo) {

        return new ResultBean<>(getPage(zhgdAnnouncementService.list(announcementVo)));
    }


    @GetMapping("detail/{id}")
    public ResultBean<ZhgdAnnouncementVo> detail(@PathVariable("id") Integer id) {

        return new ResultBean<>(zhgdAnnouncementService.detail(id));
    }

    @GetMapping("delete/{id}")
    public ResultBean<Integer> delete(@PathVariable("id") Integer id) {

        return new ResultBean<>(zhgdAnnouncementService.delete(id));
    }


    @PostMapping("save")
    public ResultBean<Integer> save(ZhgdAnnouncementVo zhgdAnnouncementVo) {

        return new ResultBean<>(zhgdAnnouncementService.saveData(zhgdAnnouncementVo));
    }

    @PostMapping("update")
    public ResultBean<Integer> update(ZhgdAnnouncementVo zhgdAnnouncementVo) {

        return new ResultBean<>(zhgdAnnouncementService.updateData(zhgdAnnouncementVo));
    }


    @PostMapping("uploadFile")
    public ResultBean<Map<String, Object>> uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile,
                                                      Integer form, Integer fileType, HttpServletRequest request) throws IOException {
        Long deptId = RequestHeaderUtils.getDeptId(request);
        return new ResultBean<>(zhgdFileService.uploadFile(uploadFile, deptId, form, fileType, announcement));
    }

    @GetMapping("download/{id}")
    public void download(@PathVariable("id") Integer id,
                         HttpServletResponse response) throws Exception {

        zhgdFileService.download(id, response);
    }


}
