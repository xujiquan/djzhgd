package com.djzhgd.project.system.controller;

import com.djzhgd.common.constant.UserConstants;
import com.djzhgd.common.utils.SecurityUtils;
import com.djzhgd.common.utils.poi.ExcelUtil;
import com.djzhgd.framework.aspectj.lang.annotation.Log;
import com.djzhgd.framework.aspectj.lang.enums.BusinessType;
import com.djzhgd.framework.web.controller.BaseController;
import com.djzhgd.framework.web.domain.AjaxResult;
import com.djzhgd.framework.web.domain.ResultBean;
import com.djzhgd.framework.web.page.TableDataInfo;
import com.djzhgd.project.system.domain.SysDept;
import com.djzhgd.project.system.domain.ZxDeptInfo;
import com.djzhgd.project.system.service.ISysDeptService;
import com.djzhgd.project.system.service.IZxDeptInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门Controller
 *
 * @author suenle
 * @date 2020-02-25
 */
@RestController
@RequestMapping("/system/deptInfo")
public class ZxDeptInfoController extends BaseController {
    @Autowired
    private IZxDeptInfoService zxDeptInfoService;
    @Autowired
    private ISysDeptService deptService;

    /**
     * 查询部门列表
     */
    @PreAuthorize("@ss.hasPermi('system:deptInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZxDeptInfo zxDeptInfo) {
        zxDeptInfo.setInfoType(2L);
        startPage();
        List<ZxDeptInfo> list = zxDeptInfoService.selectZxDeptInfoList(zxDeptInfo);
        return getDataTable(list);
    }


    /**
     * 根据条件查询部门列表(不分页)
     *
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:deptInfo:list')")
    @GetMapping("/listAll")
    public ResultBean listAll(ZxDeptInfo zxDeptInfo) {
        //zxDeptInfo.setInfoType(2L);
        //List<ZxDeptInfo> list = zxDeptInfoService.selectZxDeptInfoList(zxDeptInfo);
        //return ResultBean(list);
        return new ResultBean<List<ZxDeptInfo>>(zxDeptInfoService.selectZxDeptInfoList(zxDeptInfo));
    }


    /**
     * 导出部门列表
     */
    @PreAuthorize("@ss.hasPermi('system:deptInfo:export')")
    @Log(title = "部门", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ZxDeptInfo zxDeptInfo) {
        List<ZxDeptInfo> list = zxDeptInfoService.selectZxDeptInfoList(zxDeptInfo);
        ExcelUtil<ZxDeptInfo> util = new ExcelUtil<ZxDeptInfo>(ZxDeptInfo.class);
        return util.exportExcel(list, "deptInfo");
    }

    /**
     * 获取部门详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:deptInfo:query')")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable("deptId") Long deptId) {
        return AjaxResult.success(zxDeptInfoService.selectZxDeptInfoById(deptId));
    }

    /**
     * 新增部门
     */
    @PreAuthorize("@ss.hasPermi('system:deptInfo:add')")
    @Log(title = "部门", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZxDeptInfo zxDeptInfo) {
        Long parenId = 100L;
        SysDept dept = new SysDept();
        dept.setParentId(parenId);
        dept.setDeptName(zxDeptInfo.getDeptName());
        dept.setOrderNum("1");
        dept.setLeader("");
        dept.setPhone("");
        dept.setEmail("");
        dept.setStatus("0");
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
            return AjaxResult.error("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        dept.setCreateBy(SecurityUtils.getUsername());
        int deptId = deptService.insertDept(dept);
        System.out.print(dept.getDeptId());
        zxDeptInfo.setParentId(parenId);
        zxDeptInfo.setDeptId(dept.getDeptId());
        zxDeptInfo.setInfoType(2L);
        return toAjax(zxDeptInfoService.insertZxDeptInfo(zxDeptInfo));
    }

    /**
     * 修改部门
     */
    @PreAuthorize("@ss.hasPermi('system:deptInfo:edit')")
    @Log(title = "部门", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZxDeptInfo zxDeptInfo) {
        SysDept dept = deptService.selectDeptById(zxDeptInfo.getDeptId());
        dept.setDeptName(zxDeptInfo.getDeptName());

        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
            return AjaxResult.error("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        } else if (dept.getParentId().equals(dept.getDeptId())) {
            return AjaxResult.error("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
        }
        dept.setUpdateBy(SecurityUtils.getUsername());

        dept.setParentId(zxDeptInfo.getParentId());

        zxDeptInfo.setInfoType(2L);

        deptService.updateDept(dept);

        return toAjax(zxDeptInfoService.updateZxDeptInfo(zxDeptInfo));
    }

    /**
     * 删除部门
     */
    @PreAuthorize("@ss.hasPermi('system:deptInfo:remove')")
    @Log(title = "部门", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deptIds}")
    public AjaxResult remove(@PathVariable Long[] deptIds) {
        Long deptId = deptIds[0];
        if (deptService.hasChildByDeptId(deptId)) {
            return AjaxResult.error("存在下级部门,不允许删除");
        }
        if (deptService.checkDeptExistUser(deptId)) {
            return AjaxResult.error("部门存在用户,不允许删除");
        }
        deptService.deleteDeptById(deptId);
        return toAjax(zxDeptInfoService.deleteZxDeptInfoById(deptId));
    }

    /**
     * 查询标段列表
     */
    @PreAuthorize("@ss.hasPermi('system:deptInfo:list')")
    @GetMapping("/seclist")
    public TableDataInfo seclist(ZxDeptInfo zxDeptInfo) {

        zxDeptInfo.setInfoType(3L);
        startPage();
        List<ZxDeptInfo> list = zxDeptInfoService.selectZxDeptInfoList(zxDeptInfo);
        return getDataTable(list);
    }

    /**
     * 新增标段
     */
    @PreAuthorize("@ss.hasPermi('system:deptInfo:add')")
    @Log(title = "部门", businessType = BusinessType.INSERT)
    @PostMapping("/addsec")
    public AjaxResult addsec(@RequestBody ZxDeptInfo zxDeptInfo) {
        SysDept dept = new SysDept();
        dept.setParentId(zxDeptInfo.getParentId());
        dept.setDeptName(zxDeptInfo.getDeptName());
        dept.setOrderNum("1");
        dept.setLeader("");
        dept.setPhone("");
        dept.setEmail("");
        dept.setStatus("0");
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
            return AjaxResult.error("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        dept.setCreateBy(SecurityUtils.getUsername());
        int deptId = deptService.insertDept(dept);
        zxDeptInfo.setDeptId(dept.getDeptId());
        zxDeptInfo.setInfoType(3L);
        return toAjax(zxDeptInfoService.insertZxDeptInfo(zxDeptInfo));
    }

    /**
     * 修改部门
     */
    @PreAuthorize("@ss.hasPermi('system:deptInfo:edit')")
    @Log(title = "部门", businessType = BusinessType.UPDATE)
    @PutMapping("/editsec")
    public AjaxResult editsec(@RequestBody ZxDeptInfo zxDeptInfo) {
        SysDept dept = deptService.selectDeptById(zxDeptInfo.getDeptId());
        dept.setDeptName(zxDeptInfo.getDeptName());
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
            return AjaxResult.error("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        } else if (dept.getParentId().equals(dept.getDeptId())) {
            return AjaxResult.error("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
        }
        dept.setUpdateBy(SecurityUtils.getUsername());
        dept.setParentId(zxDeptInfo.getParentId());
        zxDeptInfo.setInfoType(3L);
        deptService.updateDept(dept);
        return toAjax(zxDeptInfoService.updateZxDeptInfo(zxDeptInfo));
    }


}

