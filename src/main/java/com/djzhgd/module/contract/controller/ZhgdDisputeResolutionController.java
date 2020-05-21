package com.djzhgd.module.contract.controller;

import com.djzhgd.framework.web.controller.BaseController;
import com.djzhgd.framework.web.domain.ResultBean;
import com.djzhgd.framework.web.page.PageReq;
import com.djzhgd.module.contract.service.IZhgdDisputeResolutionService;
import com.djzhgd.module.projectmanagement.vo.ZhgdDisputeResolutionVo;
import com.djzhgd.module.utils.RequestHeaderUtils;
import com.djzhgd.project.system.service.ZhgdFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@Controller
//@RequestMapping("demo/zhgdDisputeResolution")
public class ZhgdDisputeResolutionController extends BaseController {

    @Autowired
    private IZhgdDisputeResolutionService zhgdDisputeResolutionService;
    @Value("djzhgd.upload-file.disputeResolution")
    private String disputeResolution;
    @Autowired
    private ZhgdFileService zhgdFileService;

    @GetMapping("list")
    public ResultBean<PageReq> list(ZhgdDisputeResolutionVo disputeResolutionVo) {

        return new ResultBean<>(getPage(zhgdDisputeResolutionService.list(disputeResolutionVo)));
    }


    @GetMapping("detail/{id}")
    public ResultBean<ZhgdDisputeResolutionVo> detail(@PathVariable("id") Integer id) {

        return new ResultBean<>(zhgdDisputeResolutionService.detail(id));
    }

    @GetMapping("delete/{id}")
    public ResultBean<Integer> delete(@PathVariable("id") Integer id) {

        return new ResultBean<>(zhgdDisputeResolutionService.delete(id));
    }


    @PostMapping("save")
    public ResultBean<Integer> save(ZhgdDisputeResolutionVo zhgdAnnouncementVo) {

        return new ResultBean<>(zhgdDisputeResolutionService.saveData(zhgdAnnouncementVo));
    }

    @PostMapping("update")
    public ResultBean<Integer> update(ZhgdDisputeResolutionVo zhgdDisputeResolutionVo) {

        return new ResultBean<>(zhgdDisputeResolutionService.updateData(zhgdDisputeResolutionVo));
    }


    @PostMapping("uploadFile")
    public ResultBean<Map<String, Object>> uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile,
                                                      Integer form, Integer fileType, HttpServletRequest request) throws IOException {
        Long deptId = RequestHeaderUtils.getDeptId(request);
        return new ResultBean<>(zhgdFileService.uploadFile(uploadFile, deptId, form, fileType, disputeResolution));
    }

    @GetMapping("download/{id}")
    public void download(@PathVariable("id") Integer id,
                         HttpServletResponse response) throws Exception {

        zhgdFileService.download(id, response);
    }

}
