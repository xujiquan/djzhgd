package com.djzhgd.project.system.controller;

import com.djzhgd.common.utils.poi.ExcelUtil;
import com.djzhgd.framework.aspectj.lang.annotation.Log;
import com.djzhgd.framework.aspectj.lang.enums.BusinessType;
import com.djzhgd.framework.web.controller.BaseController;
import com.djzhgd.framework.web.domain.AjaxResult;
import com.djzhgd.framework.web.page.TableDataInfo;
import com.djzhgd.project.system.domain.ZxPatrolBase;
import com.djzhgd.project.system.service.IZxPatrolBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 巡查管理Controller
 *
 * @author suenle
 * @date 2020-03-12
 */
@RestController
@RequestMapping("/system/patrolBase")
public class ZxPatrolBaseController extends BaseController
{
    @Autowired
    private IZxPatrolBaseService zxPatrolBaseService;

    /**
     * 查询巡查管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:patrolBase:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZxPatrolBase zxPatrolBase)
    {
        startPage();
        List<ZxPatrolBase> list = zxPatrolBaseService.selectZxPatrolBaseList(zxPatrolBase);
        return getDataTable(list);
    }

    /**
     * 导出巡查管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:patrolBase:export')")
    @Log(title = "巡查管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ZxPatrolBase zxPatrolBase)
    {
        List<ZxPatrolBase> list = zxPatrolBaseService.selectZxPatrolBaseList(zxPatrolBase);
        ExcelUtil<ZxPatrolBase> util = new ExcelUtil<ZxPatrolBase>(ZxPatrolBase.class);
        return util.exportExcel(list, "patrolBase");
    }

    /**
     * 获取巡查管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:patrolBase:query')")
    @GetMapping(value = "/{patrolId}")
    public AjaxResult getInfo(@PathVariable("patrolId") Long patrolId)
    {
        return AjaxResult.success(zxPatrolBaseService.selectZxPatrolBaseById(patrolId));
    }

    /**
     * 新增巡查管理
     */
    @PreAuthorize("@ss.hasPermi('system:patrolBase:add')")
    @Log(title = "巡查管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZxPatrolBase zxPatrolBase)
    {
        return toAjax(zxPatrolBaseService.insertZxPatrolBase(zxPatrolBase));
    }

    /**
     * 修改巡查管理
     */
    @PreAuthorize("@ss.hasPermi('system:patrolBase:edit')")
    @Log(title = "巡查管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZxPatrolBase zxPatrolBase)
    {
        return toAjax(zxPatrolBaseService.updateZxPatrolBase(zxPatrolBase));
    }

    /**
     * 删除巡查管理
     */
    @PreAuthorize("@ss.hasPermi('system:patrolBase:remove')")
    @Log(title = "巡查管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{patrolIds}")
    public AjaxResult remove(@PathVariable Long[] patrolIds)
    {
        return toAjax(zxPatrolBaseService.deleteZxPatrolBaseByIds(patrolIds));
    }

    @RequestMapping("/downFileWord")
    public Map<String,Object> downFileWord(HttpServletRequest request, HttpServletResponse response){
        String patrolIdStr=request.getParameter("patrolId");
        Map<String,Object> map = zxPatrolBaseService.downFileWord(Long.valueOf(patrolIdStr), request, response);
        return map;
    }
}
