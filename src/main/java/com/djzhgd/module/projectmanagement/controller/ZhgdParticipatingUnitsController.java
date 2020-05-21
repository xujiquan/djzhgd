package com.djzhgd.module.projectmanagement.controller;

import com.djzhgd.framework.aspectj.lang.annotation.Log;
import com.djzhgd.framework.aspectj.lang.enums.BusinessType;
import com.djzhgd.framework.web.controller.BaseController;
import com.djzhgd.framework.web.domain.ResultBean;
import com.djzhgd.framework.web.page.PageReq;
import com.djzhgd.module.projectmanagement.domain.ZhgdParticipatingUnits;
import com.djzhgd.module.projectmanagement.service.ZhgdParticipatingUnitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * @Author: lgc
 * @Date: 2020/5/18 11:43
 */
@RestController
@RequestMapping(value = "/zhgdparticipatingunits")
public class ZhgdParticipatingUnitsController extends BaseController {
    @Autowired
    private ZhgdParticipatingUnitsService zhgdParticipatingUnitsService;

    /**
     * 查询在建单位列表
     */
    // @PreAuthorize("@ss.hasPermi('system:participatingunits:list')")
    @PostMapping("/list")
    public ResultBean<PageReq> list(@RequestBody ZhgdParticipatingUnits zhgdParticipatingUnits) {

        return new ResultBean<PageReq>(getPage(zhgdParticipatingUnitsService.list(zhgdParticipatingUnits)));
    }


    /**
     * 获取在建单位详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:participatingunits:query')")
    @PostMapping("/detail/{id}")
    public ResultBean<ZhgdParticipatingUnits> getInfo(@PathVariable("id") Long id) {
        return new ResultBean<ZhgdParticipatingUnits>(zhgdParticipatingUnitsService.getById(id));
    }

    /**
     * 新增在建单位
     */
    //@PreAuthorize("@ss.hasPermi('system:participatingunits:add')")
    @Log(title = "新增在建单位", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public ResultBean add(@RequestBody ZhgdParticipatingUnits zhgdParticipatingUnits) {
        return Contrast(zhgdParticipatingUnitsService.save(zhgdParticipatingUnits) ? 1 : 0);
    }

    /**
     * 修改在建单位
     */
    //@PreAuthorize("@ss.hasPermi('system:participatingunits:edit')")
    @Log(title = "修改在建单位", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public ResultBean edit(@RequestBody ZhgdParticipatingUnits zhgdParticipatingUnits) {
        return Contrast(zhgdParticipatingUnitsService.updateById(zhgdParticipatingUnits) ? 1 : 0);
    }

    /**
     * 删除在建单位
     */
    //@PreAuthorize("@ss.hasPermi('system:participatingunits:remove')")
    @Log(title = "删除在建单位", businessType = BusinessType.DELETE)
    @PostMapping("/delete/{ids}")
    public ResultBean remove(@PathVariable Long[] ids) {
        return Contrast(zhgdParticipatingUnitsService.removeByIds(Arrays.asList(ids)) ? 1 : 0);
    }

}
