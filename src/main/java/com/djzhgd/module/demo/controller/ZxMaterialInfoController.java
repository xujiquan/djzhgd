package com.djzhgd.module.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.djzhgd.common.utils.StringUtils;
import com.djzhgd.common.utils.poi.ExcelUtil;
import com.djzhgd.framework.aspectj.lang.annotation.Log;
import com.djzhgd.framework.aspectj.lang.enums.BusinessType;
import com.djzhgd.framework.web.controller.BaseController;
import com.djzhgd.framework.web.domain.AjaxResult;
import com.djzhgd.framework.web.domain.ResultBean;
import com.djzhgd.framework.web.page.PageReq;
import com.djzhgd.module.demo.domain.ZxMaterialInfo;
import com.djzhgd.module.demo.service.IZxMaterialInfoService;
import com.djzhgd.module.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 原材料评估Controller
 *
 * @author suenle
 * @date 2020-03-26
 */
@RestController
@RequestMapping("/demo/materialInfo")
public class ZxMaterialInfoController extends BaseController {
    @Autowired
    private IZxMaterialInfoService zxMaterialInfoService;


    /**
     * 查询原材料评估列表
     */
    // @PreAuthorize("@ss.hasPermi('system:materialInfo:list')")
    @GetMapping("/list")
    public ResultBean<PageReq> list(ZxMaterialInfo zxMaterialInfo) {
        return new ResultBean<PageReq>(getPage(zxMaterialInfoService.list(zxMaterialInfo)));
    }

    /**
     * 导出原材料评估列表
     */
    @PreAuthorize("@ss.hasPermi('system:materialInfo:export')")
    @Log(title = "原材料评估", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ZxMaterialInfo zxMaterialInfo) {
        QueryWrapper<ZxMaterialInfo> queryWrapper = new QueryWrapper<ZxMaterialInfo>(zxMaterialInfo);
        List<ZxMaterialInfo> list = zxMaterialInfoService.list(queryWrapper);
        ExcelUtil<ZxMaterialInfo> util = new ExcelUtil<ZxMaterialInfo>(ZxMaterialInfo.class);
        return util.exportExcel(list, "materialInfo");
    }

    /**
     * 获取原材料评估详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:materialInfo:query')")
    @GetMapping(value = "/{materialId}")
    public ResultBean<ZxMaterialInfo> getInfo(@PathVariable("materialId") Long materialId) {
        return new ResultBean<ZxMaterialInfo>(zxMaterialInfoService.selectById(materialId));
    }

    /**
     * 新增原材料评估
     */
    @PreAuthorize("@ss.hasPermi('system:materialInfo:add')")
    @Log(title = "原材料评估", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultBean add(@RequestBody ZxMaterialInfo zxMaterialInfo) {
        return Contrast(zxMaterialInfoService.save(zxMaterialInfo) ? 1 : 0);
    }

    /**
     * 修改原材料评估
     */
    @PreAuthorize("@ss.hasPermi('system:materialInfo:edit')")
    @Log(title = "原材料评估", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResultBean edit(@RequestBody ZxMaterialInfo zxMaterialInfo) {
        return Contrast(zxMaterialInfoService.updateById(zxMaterialInfo) ? 1 : 0);
    }

    /**
     * 删除原材料评估
     */
    @PreAuthorize("@ss.hasPermi('system:materialInfo:remove')")
    @Log(title = "原材料评估", businessType = BusinessType.DELETE)
    @DeleteMapping("/{materialIds}")
    public ResultBean remove(@PathVariable Long[] materialIds) {
        return Contrast(zxMaterialInfoService.removeByIds(Arrays.asList(materialIds)) ? 1 : 0);
    }
}
