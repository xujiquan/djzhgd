package com.djzhgd.project.system.controller;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.djzhgd.common.utils.file.FileUploadUtils;
import com.djzhgd.framework.config.DjzhgdConfig;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.djzhgd.framework.aspectj.lang.annotation.Log;
import com.djzhgd.framework.aspectj.lang.enums.BusinessType;
import com.djzhgd.project.system.domain.ZxFileInfo;
import com.djzhgd.project.system.service.IZxFileInfoService;
import com.djzhgd.framework.web.controller.BaseController;
import com.djzhgd.framework.web.domain.AjaxResult;
import com.djzhgd.common.utils.poi.ExcelUtil;
import com.djzhgd.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件管理Controller
 *
 * @author suenle
 * @date 2020-03-21
 */
@RestController
@RequestMapping("/system/zxFileInfo")
public class ZxFileInfoController extends BaseController
{
    @Autowired
    private IZxFileInfoService zxFileInfoService;

    /**
     * 查询文件管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:zxFileInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZxFileInfo zxFileInfo)
    {
        startPage();
        List<ZxFileInfo> list = zxFileInfoService.selectZxFileInfoList(zxFileInfo);
        return getDataTable(list);
    }

    /**
     * 导出文件管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:zxFileInfo:export')")
    @Log(title = "文件管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ZxFileInfo zxFileInfo)
    {
        List<ZxFileInfo> list = zxFileInfoService.selectZxFileInfoList(zxFileInfo);
        ExcelUtil<ZxFileInfo> util = new ExcelUtil<ZxFileInfo>(ZxFileInfo.class);
        return util.exportExcel(list, "zxFileInfo");
    }

    /**
     * 获取文件管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:zxFileInfo:query')")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") Long fileId)
    {
        return AjaxResult.success(zxFileInfoService.selectZxFileInfoById(fileId));
    }

    /**
     * 新增文件管理
     */
    @PreAuthorize("@ss.hasPermi('system:zxFileInfo:add')")
    @Log(title = "文件管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZxFileInfo zxFileInfo)
    {
        return toAjax(zxFileInfoService.insertZxFileInfo(zxFileInfo));
    }



    /**
     * 上传文件
     */
    @Log(title = "上传文件", businessType = BusinessType.INSERT)
    @PostMapping("/uploadZxFile")
    public AjaxResult uploadZxFile(HttpServletRequest request) throws IOException
    {
        //@RequestParam("uploadFile")
        MultipartFile file=null;
        //String connectKey = request.getParameter("connectKey");
        String connectType = request.getParameter("connectType");
        if (!file.isEmpty())
        {
            String[] strArray = file.getOriginalFilename().split("\\.");
            int suffixIndex = strArray.length -1;
            //LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            System.out.print(DjzhgdConfig.getUploadPath());
            String uploadFile = FileUploadUtils.upload(DjzhgdConfig.getUploadPath(), file);
            ZxFileInfo zxFileInfo = new ZxFileInfo();
           // zxFileInfo.setConnectKey(connectKey);
            zxFileInfo.setConnectType(connectType);
            zxFileInfo.setFileName(file.getOriginalFilename());
            zxFileInfo.setFilePath(uploadFile);
            zxFileInfo.setFileType(strArray[suffixIndex]);
            zxFileInfoService.insertZxFileInfo(zxFileInfo);
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileId", zxFileInfo.getFileId());
            return ajax;
        }
        return AjaxResult.error("上传文件异常，请联系管理员");
    }

    /**
         * 上传文件
     */
    @Log(title = "下载文件")
    @GetMapping("/downloadZxFileFlow")
    public void downloadZxFileFlow(HttpServletRequest request, HttpServletResponse response)
    {
        String fileId = request.getParameter("fileId");
        ZxFileInfo zxFileInfo = zxFileInfoService.selectZxFileInfoById(Long.valueOf(fileId));
        if (zxFileInfo!=null)
        {
            FileUploadUtils.downloadByFlow(DjzhgdConfig.getProfile()+zxFileInfo.getFilePath(),zxFileInfo.getFileName(), request, response);
        }
    }



    /**
     * 修改文件管理
     */
    @PreAuthorize("@ss.hasPermi('system:zxFileInfo:edit')")
    @Log(title = "文件管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZxFileInfo zxFileInfo)
    {
        return toAjax(zxFileInfoService.updateZxFileInfo(zxFileInfo));
    }

    /**
     * 删除文件管理
     */
    @PreAuthorize("@ss.hasPermi('system:zxFileInfo:remove')")
    @Log(title = "文件管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fileIds}")
    public AjaxResult remove(@PathVariable Long[] fileIds)
    {
        return toAjax(zxFileInfoService.deleteZxFileInfoByIds(fileIds));
    }
}
