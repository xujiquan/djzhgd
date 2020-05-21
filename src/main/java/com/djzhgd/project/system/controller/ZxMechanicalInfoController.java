package com.djzhgd.project.system.controller;

import java.util.List;
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
import com.djzhgd.project.system.domain.ZxMechanicalInfo;
import com.djzhgd.project.system.service.IZxMechanicalInfoService;
import com.djzhgd.framework.web.controller.BaseController;
import com.djzhgd.framework.web.domain.AjaxResult;
import com.djzhgd.common.utils.poi.ExcelUtil;
import com.djzhgd.framework.web.page.TableDataInfo;

/**
 * 机械管理Controller
 * 
 * @author suenle
 * @date 2020-02-27
 */
@RestController
@RequestMapping("/system/mechanicalInfo")
public class ZxMechanicalInfoController extends BaseController
{
    @Autowired
    private IZxMechanicalInfoService zxMechanicalInfoService;

    /**
     * 查询机械管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:mechanicalInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZxMechanicalInfo zxMechanicalInfo)
    {
        startPage();
        List<ZxMechanicalInfo> list = zxMechanicalInfoService.selectZxMechanicalInfoList(zxMechanicalInfo);
        return getDataTable(list);
    }

    /**
     * 导出机械管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:mechanicalInfo:export')")
    @Log(title = "机械管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ZxMechanicalInfo zxMechanicalInfo)
    {
        List<ZxMechanicalInfo> list = zxMechanicalInfoService.selectZxMechanicalInfoList(zxMechanicalInfo);
        ExcelUtil<ZxMechanicalInfo> util = new ExcelUtil<ZxMechanicalInfo>(ZxMechanicalInfo.class);
        return util.exportExcel(list, "mechanicalInfo");
    }

    /**
     * 获取机械管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:mechanicalInfo:query')")
    @GetMapping(value = "/{mechanicalId}")
    public AjaxResult getInfo(@PathVariable("mechanicalId") Long mechanicalId)
    {
        return AjaxResult.success(zxMechanicalInfoService.selectZxMechanicalInfoById(mechanicalId));
    }

    /**
     * 新增机械管理
     */
    @PreAuthorize("@ss.hasPermi('system:mechanicalInfo:add')")
    @Log(title = "机械管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZxMechanicalInfo zxMechanicalInfo)
    {
        return toAjax(zxMechanicalInfoService.insertZxMechanicalInfo(zxMechanicalInfo));
    }

    /**
     * 修改机械管理
     */
    @PreAuthorize("@ss.hasPermi('system:mechanicalInfo:edit')")
    @Log(title = "机械管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZxMechanicalInfo zxMechanicalInfo)
    {
        return toAjax(zxMechanicalInfoService.updateZxMechanicalInfo(zxMechanicalInfo));
    }

    /**
     * 删除机械管理
     */
    @PreAuthorize("@ss.hasPermi('system:mechanicalInfo:remove')")
    @Log(title = "机械管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mechanicalIds}")
    public AjaxResult remove(@PathVariable Long[] mechanicalIds)
    {
        return toAjax(zxMechanicalInfoService.deleteZxMechanicalInfoByIds(mechanicalIds));
    }
}
