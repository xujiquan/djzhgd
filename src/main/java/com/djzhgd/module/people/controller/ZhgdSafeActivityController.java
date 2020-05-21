package com.djzhgd.module.people.controller;

import com.djzhgd.framework.aspectj.lang.annotation.Log;
import com.djzhgd.framework.aspectj.lang.enums.BusinessType;
import com.djzhgd.framework.web.controller.BaseController;
import com.djzhgd.framework.web.domain.ResultBean;
import com.djzhgd.framework.web.page.PageReq;
import com.djzhgd.module.people.domain.vo.ZhgdSafeActivityVo;
import com.djzhgd.module.people.service.IZhgdSafeActivityService;
import com.djzhgd.module.result.Result;
import com.djzhgd.module.result.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 教育培训Controller
 *
 * @author wangjing
 * @date 2020-03-26
 */
@RestController
@RequestMapping("/zhgdsafeactivity")
public class ZhgdSafeActivityController extends BaseController {
    @Autowired
    private IZhgdSafeActivityService zhgdSafeActivityService;

    /**
     * 查询教育培训列表
     */
    //@PreAuthorize("@ss.hasPermi('system:safeactivity:list')")
    @PostMapping("/list")
    public ResultBean<PageReq> list(@RequestBody ZhgdSafeActivityVo zhgdSafeActivityVo) {
        return new ResultBean<PageReq>(getPage(zhgdSafeActivityService.list(zhgdSafeActivityVo)));
    }
    /**
     * 获取教育培训详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:safeactivity:query')")
    @PostMapping("/detail/{id}")
    public ResultBean<ZhgdSafeActivityVo> detail(@PathVariable("id") Integer id) {
        return new ResultBean<ZhgdSafeActivityVo>(zhgdSafeActivityService.detail(id));
    }

    /**
     * 新增教育培训
     */
   // @PreAuthorize("@ss.hasPermi('system:safeactivity:add')")
    @Log(title = "新增教育培训", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public ResultBean add(@RequestBody ZhgdSafeActivityVo zhgdSafeActivityVo) {
        return Contrast(zhgdSafeActivityService.saveData(zhgdSafeActivityVo) ? 1 : 0);
    }

    /**
     * 修改教育培训
     */
    //@PreAuthorize("@ss.hasPermi('system:safeactivity:edit')")
    @Log(title = "修改教育培训", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public ResultBean edit(@RequestBody ZhgdSafeActivityVo zhgdSafeActivityVo) {
        return Contrast(zhgdSafeActivityService.updateData(zhgdSafeActivityVo) ? 1 : 0);
    }

    /**
     * 删除教育培训
     */
   // @PreAuthorize("@ss.hasPermi('system:safeactivity:remove')")
    @Log(title = "删除教育培训", businessType = BusinessType.DELETE)
    @PostMapping("/delete/{ids}")
    public ResultBean remove(@PathVariable Long[] ids) {
        return Contrast(zhgdSafeActivityService.removeByIds(Arrays.asList(ids)) ? 1 : 0);
    }


    /**
     * 统计页面
     *
     */
    @PostMapping("/trainCount")
    public ResultBean<List<ZhgdSafeActivityVo>> trainCount(@RequestBody ZhgdSafeActivityVo zhgdSafeActivityVo) {
        return new ResultBean<List<ZhgdSafeActivityVo>>(zhgdSafeActivityService.getTrainCount(zhgdSafeActivityVo));
    }

    @PostMapping("/trainZBCount")
    public ResultBean<List<ZhgdSafeActivityVo>> trainZBCount(@RequestBody ZhgdSafeActivityVo zhgdSafeActivityVo) {
        return new ResultBean<List<ZhgdSafeActivityVo>>(zhgdSafeActivityService.trainZBCount(zhgdSafeActivityVo));
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
            map = zhgdSafeActivityService.uploadFile(uploadFile);
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
