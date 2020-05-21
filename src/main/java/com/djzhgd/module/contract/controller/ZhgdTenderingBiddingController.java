package com.djzhgd.module.contract.controller;

import com.djzhgd.framework.aspectj.lang.annotation.Log;
import com.djzhgd.framework.aspectj.lang.enums.BusinessType;
import com.djzhgd.framework.web.controller.BaseController;
import com.djzhgd.framework.web.domain.ResultBean;
import com.djzhgd.framework.web.page.PageReq;
import com.djzhgd.module.contract.domain.vo.ZhgdTenderingBiddingVo;
import com.djzhgd.module.contract.service.IZhgdTenderingBiddingService;
import com.djzhgd.module.result.Result;
import com.djzhgd.module.result.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * 招投标文件Controller
 *
 * @author wangjing
 * @date 2020-03-26
 */
@RestController
@RequestMapping("/zhgdtenderingbidding")
public class ZhgdTenderingBiddingController extends BaseController {
    @Autowired
    private IZhgdTenderingBiddingService zhgdTenderingBiddingService;

    /**
     * 查询招投标文件列表
     */
    //@PreAuthorize("@ss.hasPermi('system:tenderingbidding:list')")
    @PostMapping("/list")
    public ResultBean<PageReq> list(@RequestBody ZhgdTenderingBiddingVo zhgdTenderingBiddingVo) {
        return new ResultBean<PageReq>(getPage(zhgdTenderingBiddingService.list(zhgdTenderingBiddingVo)));
    }

    /**
     * 获取招投标文件详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:tenderingbidding:query')")
    @PostMapping("/detail/{id}")
    public ResultBean<ZhgdTenderingBiddingVo> detail(@PathVariable("id") Integer id) {
        return new ResultBean<ZhgdTenderingBiddingVo>(zhgdTenderingBiddingService.detail(id));
    }

    /**
     * 新增招投标文件
     */
   // @PreAuthorize("@ss.hasPermi('system:tenderingbidding:add')")
    @Log(title = "新增招投标文件", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public ResultBean add(@RequestBody ZhgdTenderingBiddingVo zhgdTenderingBiddingVo) {
        return Contrast(zhgdTenderingBiddingService.saveData(zhgdTenderingBiddingVo) ? 1 : 0);
    }

    /**
     * 修改招投标文件
     */
    //@PreAuthorize("@ss.hasPermi('system:tenderingbidding:edit')")
    @Log(title = "修改招投标文件", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public ResultBean edit(@RequestBody ZhgdTenderingBiddingVo zhgdTenderingBiddingVo) {
        return Contrast(zhgdTenderingBiddingService.updateData(zhgdTenderingBiddingVo) ? 1 : 0);
    }

    /**
     * 删除招投标文件
     */
   // @PreAuthorize("@ss.hasPermi('system:tenderingbidding:remove')")
    @Log(title = "删除招投标文件", businessType = BusinessType.DELETE)
    @PostMapping("/delete/{ids}")
    public ResultBean remove(@PathVariable Long[] ids) {
        return Contrast(zhgdTenderingBiddingService.removeByIds(Arrays.asList(ids)) ? 1 : 0);
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
            map = zhgdTenderingBiddingService.uploadFile(uploadFile);
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
