package com.djzhgd.project.system.controller;

import java.util.List;

import com.djzhgd.common.constant.UserConstants;
import com.djzhgd.common.utils.SecurityUtils;
import com.djzhgd.project.system.domain.SysDept;
import com.djzhgd.project.system.service.ISysDeptService;
import javafx.scene.DepthTest;
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
import com.djzhgd.project.system.domain.ZxProjectInfo;
import com.djzhgd.project.system.service.IZxProjectInfoService;
import com.djzhgd.framework.web.controller.BaseController;
import com.djzhgd.framework.web.domain.AjaxResult;
import com.djzhgd.common.utils.poi.ExcelUtil;
import com.djzhgd.framework.web.page.TableDataInfo;

/**
 * 项目信息Controller
 * 
 * @author suenle
 * @date 2020-02-22
 */
@RestController
@RequestMapping("/system/projectInfo")
public class ZxProjectInfoController extends BaseController
{
    @Autowired
    private IZxProjectInfoService zxProjectInfoService;
    @Autowired
    private ISysDeptService deptService;

    /**
     * 查询项目信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZxProjectInfo zxProjectInfo)
    {
        startPage();
        List<ZxProjectInfo> list = zxProjectInfoService.selectZxProjectInfoList(zxProjectInfo);
        return getDataTable(list);
    }



    /**
     * 导出项目信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:projectInfo:export')")
    @Log(title = "项目信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ZxProjectInfo zxProjectInfo)
    {
        List<ZxProjectInfo> list = zxProjectInfoService.selectZxProjectInfoList(zxProjectInfo);
        ExcelUtil<ZxProjectInfo> util = new ExcelUtil<ZxProjectInfo>(ZxProjectInfo.class);
        return util.exportExcel(list, "projectInfo");
    }

    /**
     * 获取项目信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:projectInfo:query')")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable("deptId") Long deptId)
    {
        return AjaxResult.success(zxProjectInfoService.selectZxProjectInfoById(deptId));
    }

    /**
     * 新增项目信息
     */
    @PreAuthorize("@ss.hasPermi('system:projectInfo:add')")
    @Log(title = "项目信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZxProjectInfo zxProjectInfo)
    {
         Long parenId=100L;
         SysDept dept=new SysDept();
         dept.setParentId(parenId);
         dept.setDeptName(zxProjectInfo.getDeptName());
         dept.setOrderNum("1");
         dept.setLeader("");
         dept.setPhone("");
         dept.setEmail("");
         dept.setStatus("0");
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept)))
        {
            return AjaxResult.error("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        dept.setCreateBy(SecurityUtils.getUsername());
        int deptId= deptService.insertDept(dept);
        System.out.print(dept.getDeptId());
        zxProjectInfo.setParentId(parenId);
        zxProjectInfo.setDeptId(dept.getDeptId());

        return toAjax(zxProjectInfoService.insertZxProjectInfo(zxProjectInfo));
    }

    /**
     * 修改项目信息
     */
    @PreAuthorize("@ss.hasPermi('system:projectInfo:edit')")
    @Log(title = "项目信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZxProjectInfo zxProjectInfo)
    {
        return toAjax(zxProjectInfoService.updateZxProjectInfo(zxProjectInfo));
    }

    /**
     * 删除项目信息
     */
    @PreAuthorize("@ss.hasPermi('system:projectInfo:remove')")
    @Log(title = "项目信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deptIds}")
    public AjaxResult remove(@PathVariable Long[] deptIds)
    {
        return toAjax(zxProjectInfoService.deleteZxProjectInfoByIds(deptIds));
    }
}
