package com.djzhgd.module.contract.controller;

import com.djzhgd.framework.aspectj.lang.annotation.Log;
import com.djzhgd.framework.aspectj.lang.enums.BusinessType;
import com.djzhgd.framework.web.controller.BaseController;
import com.djzhgd.framework.web.domain.ResultBean;
import com.djzhgd.framework.web.page.PageReq;
import com.djzhgd.module.contract.domain.vo.ZhgdAssessmentVo;
import com.djzhgd.module.contract.service.IZhgdAssessmentService;
import com.djzhgd.module.result.Result;
import com.djzhgd.module.result.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * 履约考核Controller
 *
 * @author wangjing
 * @date 2020-03-26
 */
@RestController
@RequestMapping("/zhgdassessment")
public class ZhgdAssessmentController extends BaseController {
    @Autowired
    private IZhgdAssessmentService zhgdAssessmentService;

    /**
     * 查询履约考核列表
     */
    //@PreAuthorize("@ss.hasPermi('system:assessment:list')")
    @PostMapping("/list")
    public ResultBean<PageReq> list(@RequestBody ZhgdAssessmentVo zhgdAssessmentVo) {
        return new ResultBean<PageReq>(getPage(zhgdAssessmentService.list(zhgdAssessmentVo)));
    }
    /**
     * 获取履约考核详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:assessment:query')")
    @PostMapping("/detail/{id}")
    public ResultBean<ZhgdAssessmentVo> detail(@PathVariable("id") Integer id) {
        return new ResultBean<ZhgdAssessmentVo>(zhgdAssessmentService.detail(id));
    }

    /**
     * 新增履约考核
     */
   // @PreAuthorize("@ss.hasPermi('system:assessment:add')")
    @Log(title = "新增履约考核", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public ResultBean add(@RequestBody ZhgdAssessmentVo zhgdAssessmentVo) {
        return Contrast(zhgdAssessmentService.saveData(zhgdAssessmentVo) ? 1 : 0);
    }

    /**
     * 修改履约考核
     */
    //@PreAuthorize("@ss.hasPermi('system:assessment:edit')")
    @Log(title = "修改履约考核", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public ResultBean edit(@RequestBody ZhgdAssessmentVo zhgdAssessmentVo) {
        return Contrast(zhgdAssessmentService.updateData(zhgdAssessmentVo) ? 1 : 0);
    }

    /**
     * 删除履约考核评估
     */
   // @PreAuthorize("@ss.hasPermi('system:assessment:remove')")
    @Log(title = "删除履约考核评估", businessType = BusinessType.DELETE)
    @PostMapping("/delete/{ids}")
    public ResultBean remove(@PathVariable Long[] ids) {
        return Contrast(zhgdAssessmentService.removeByIds(Arrays.asList(ids)) ? 1 : 0);
    }

    /**
     * 上传文件、图片
     */
    @ResponseBody
    @PostMapping("/uploadfile")
    public Result uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile) {
        Result result = new Result();

        Map<String, Object> map = null;
        try {
            map = zhgdAssessmentService.uploadFile(uploadFile);
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
