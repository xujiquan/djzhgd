package com.djzhgd.project.system.controller;

import com.djzhgd.common.utils.poi.ExcelUtil;
import com.djzhgd.framework.aspectj.lang.annotation.Log;
import com.djzhgd.framework.aspectj.lang.enums.BusinessType;
import com.djzhgd.framework.web.controller.BaseController;
import com.djzhgd.framework.web.domain.AjaxResult;
import com.djzhgd.framework.web.page.TableDataInfo;
import com.djzhgd.project.system.domain.ZxSyInstrumentInfo;
import com.djzhgd.project.system.service.IZxSyInstrumentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 关键仪器管理Controller
 * 
 * @author suenle
 * @date 2020-03-01
 */
@RestController
@RequestMapping("/system/syInstrumentInfo")
public class ZxSyInstrumentInfoController extends BaseController
{
    @Autowired
    private IZxSyInstrumentInfoService zxSyInstrumentInfoService;

    /**
     * 查询关键仪器管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:syInstrumentInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZxSyInstrumentInfo zxSyInstrumentInfo)
    {
        startPage();
        List<ZxSyInstrumentInfo> list = zxSyInstrumentInfoService.selectZxSyInstrumentInfoList(zxSyInstrumentInfo);
        return getDataTable(list);
    }

    /**
     * 导出关键仪器管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:syInstrumentInfo:export')")
    @Log(title = "关键仪器管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ZxSyInstrumentInfo zxSyInstrumentInfo)
    {
        List<ZxSyInstrumentInfo> list = zxSyInstrumentInfoService.selectZxSyInstrumentInfoList(zxSyInstrumentInfo);
        ExcelUtil<ZxSyInstrumentInfo> util = new ExcelUtil<ZxSyInstrumentInfo>(ZxSyInstrumentInfo.class);
        return util.exportExcel(list, "syInstrumentInfo");
    }

    /**
     * 获取关键仪器管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:syInstrumentInfo:query')")
    @GetMapping(value = "/{instrumentId}")
    public AjaxResult getInfo(@PathVariable("instrumentId") Long instrumentId)
    {
        return AjaxResult.success(zxSyInstrumentInfoService.selectZxSyInstrumentInfoById(instrumentId));
    }

    /**
     * 新增关键仪器管理
     */
    @PreAuthorize("@ss.hasPermi('system:syInstrumentInfo:add')")
    @Log(title = "关键仪器管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZxSyInstrumentInfo zxSyInstrumentInfo)
    {
        return toAjax(zxSyInstrumentInfoService.insertZxSyInstrumentInfo(zxSyInstrumentInfo));
    }

    /**
     * 修改关键仪器管理
     */
    @PreAuthorize("@ss.hasPermi('system:syInstrumentInfo:edit')")
    @Log(title = "关键仪器管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZxSyInstrumentInfo zxSyInstrumentInfo)
    {
        return toAjax(zxSyInstrumentInfoService.updateZxSyInstrumentInfo(zxSyInstrumentInfo));
    }

    /**
     * 删除关键仪器管理
     */
    @PreAuthorize("@ss.hasPermi('system:syInstrumentInfo:remove')")
    @Log(title = "关键仪器管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{instrumentIds}")
    public AjaxResult remove(@PathVariable Long[] instrumentIds)
    {
        return toAjax(zxSyInstrumentInfoService.deleteZxSyInstrumentInfoByIds(instrumentIds));
    }
}
