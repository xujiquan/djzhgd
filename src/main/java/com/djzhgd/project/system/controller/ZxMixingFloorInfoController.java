package com.djzhgd.project.system.controller;

import java.util.List;

import com.djzhgd.framework.aspectj.lang.annotation.DataScope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.djzhgd.framework.aspectj.lang.annotation.Log;
import com.djzhgd.framework.aspectj.lang.enums.BusinessType;
import com.djzhgd.project.system.domain.ZxMixingFloorInfo;
import com.djzhgd.project.system.service.IZxMixingFloorInfoService;
import com.djzhgd.framework.web.controller.BaseController;
import com.djzhgd.framework.web.domain.AjaxResult;
import com.djzhgd.common.utils.poi.ExcelUtil;
import com.djzhgd.framework.web.page.TableDataInfo;

/**
 * 拌合楼管理Controller
 *
 * @author suenle
 * @date 2020-02-27
 */
@RestController
@RequestMapping("/system/mixingFloorInfo")
public class ZxMixingFloorInfoController extends BaseController
{
    @Autowired
    private IZxMixingFloorInfoService zxMixingFloorInfoService;

    /**
     * 查询拌合楼管理列表
     */
    @DataScope
    @PreAuthorize("@ss.hasPermi('system:mixingFloorInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZxMixingFloorInfo zxMixingFloorInfo)
    {
        startPage();
        List<ZxMixingFloorInfo> list = zxMixingFloorInfoService.selectZxMixingFloorInfoList(zxMixingFloorInfo);
        return getDataTable(list);
    }

    /**
     * 导出拌合楼管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:mixingFloorInfo:export')")
    @Log(title = "拌合楼管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ZxMixingFloorInfo zxMixingFloorInfo)
    {
        List<ZxMixingFloorInfo> list = zxMixingFloorInfoService.selectZxMixingFloorInfoList(zxMixingFloorInfo);
        ExcelUtil<ZxMixingFloorInfo> util = new ExcelUtil<ZxMixingFloorInfo>(ZxMixingFloorInfo.class);
        return util.exportExcel(list, "mixingFloorInfo");
    }

    /**
     * 获取拌合楼管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:mixingFloorInfo:query')")
    @GetMapping(value = "/{floorId}")
    public AjaxResult getInfo(@PathVariable("floorId") Long floorId)
    {
        return AjaxResult.success(zxMixingFloorInfoService.selectZxMixingFloorInfoById(floorId));
    }

    /**
     * 新增拌合楼管理
     */
    @PreAuthorize("@ss.hasPermi('system:mixingFloorInfo:add')")
    @Log(title = "拌合楼管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZxMixingFloorInfo zxMixingFloorInfo)
    {
        return toAjax(zxMixingFloorInfoService.insertZxMixingFloorInfo(zxMixingFloorInfo));
    }

    /**
     * 修改拌合楼管理
     */
    @PreAuthorize("@ss.hasPermi('system:mixingFloorInfo:edit')")
    @Log(title = "拌合楼管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZxMixingFloorInfo zxMixingFloorInfo)
    {
        return toAjax(zxMixingFloorInfoService.updateZxMixingFloorInfo(zxMixingFloorInfo));
    }

    /**
     * 删除拌合楼管理
     */
    @PreAuthorize("@ss.hasPermi('system:mixingFloorInfo:remove')")
    @Log(title = "拌合楼管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{floorIds}")
    public AjaxResult remove(@PathVariable Long[] floorIds)
    {
        return toAjax(zxMixingFloorInfoService.deleteZxMixingFloorInfoByIds(floorIds));
    }
}
