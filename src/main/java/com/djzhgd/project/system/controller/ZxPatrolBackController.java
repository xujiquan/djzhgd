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
import com.djzhgd.project.system.domain.ZxPatrolBack;
import com.djzhgd.project.system.service.IZxPatrolBackService;
import com.djzhgd.framework.web.controller.BaseController;
import com.djzhgd.framework.web.domain.AjaxResult;
import com.djzhgd.common.utils.poi.ExcelUtil;
import com.djzhgd.framework.web.page.TableDataInfo;

/**
 * 后场巡查Controller
 * 
 * @author suenle
 * @date 2020-03-12
 */
@RestController
@RequestMapping("/system/patrolBack")
public class ZxPatrolBackController extends BaseController
{
    @Autowired
    private IZxPatrolBackService zxPatrolBackService;

    /**
     * 查询后场巡查列表
     */
    @PreAuthorize("@ss.hasPermi('system:patrolBack:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZxPatrolBack zxPatrolBack)
    {
        startPage();
        List<ZxPatrolBack> list = zxPatrolBackService.selectZxPatrolBackList(zxPatrolBack);
        return getDataTable(list);
    }

    /**
     * 导出后场巡查列表
     */
    @PreAuthorize("@ss.hasPermi('system:patrolBack:export')")
    @Log(title = "后场巡查", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ZxPatrolBack zxPatrolBack)
    {
        List<ZxPatrolBack> list = zxPatrolBackService.selectZxPatrolBackList(zxPatrolBack);
        ExcelUtil<ZxPatrolBack> util = new ExcelUtil<ZxPatrolBack>(ZxPatrolBack.class);
        return util.exportExcel(list, "patrolBack");
    }

    /**
     * 获取后场巡查详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:patrolBack:query')")
    @GetMapping(value = "/{patrolBackId}")
    public AjaxResult getInfo(@PathVariable("patrolBackId") Long patrolBackId)
    {
        return AjaxResult.success(zxPatrolBackService.selectZxPatrolBackById(patrolBackId));
    }

    /**
     * 新增后场巡查
     */
    @PreAuthorize("@ss.hasPermi('system:patrolBack:add')")
    @Log(title = "后场巡查", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZxPatrolBack zxPatrolBack)
    {
        return toAjax(zxPatrolBackService.insertZxPatrolBack(zxPatrolBack));
    }

    /**
     * 修改后场巡查
     */
    @PreAuthorize("@ss.hasPermi('system:patrolBack:edit')")
    @Log(title = "后场巡查", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZxPatrolBack zxPatrolBack)
    {
        return toAjax(zxPatrolBackService.updateZxPatrolBack(zxPatrolBack));
    }

    /**
     * 删除后场巡查
     */
    @PreAuthorize("@ss.hasPermi('system:patrolBack:remove')")
    @Log(title = "后场巡查", businessType = BusinessType.DELETE)
	@DeleteMapping("/{patrolBackIds}")
    public AjaxResult remove(@PathVariable Long[] patrolBackIds)
    {
        return toAjax(zxPatrolBackService.deleteZxPatrolBackByIds(patrolBackIds));
    }
}
