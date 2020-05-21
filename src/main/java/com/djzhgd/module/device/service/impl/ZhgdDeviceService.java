/**
 * Description: ZhgdDevice业务实现类
 * Copyright:   Copyright (c)2019
 * Company:     envbase
 *
 * @author: huangjz
 * @version: 1.0
 * Create at:   2019-04-01 上午 11:36:04
 * <p>
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2019-04-01   huangjz   1.0         Initial
 */
package com.djzhgd.module.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djzhgd.common.utils.file.FileUploadUtils;
import com.djzhgd.framework.config.UploadFileProperties;
import com.djzhgd.module.Jurisdiction.domain.SysUserT;
import com.djzhgd.module.Jurisdiction.mapper.SysUserTMapper;
import com.djzhgd.module.device.domain.ZhgdDevice;
import com.djzhgd.module.device.domain.ZhgdDeviceInoutRecord;
import com.djzhgd.module.device.mapper.ZhgdDeviceInoutRecordMapper;
import com.djzhgd.module.device.mapper.ZhgdDeviceMapper;
import com.djzhgd.module.device.service.IZhgdDeviceService;
import com.djzhgd.module.device.vo.ZhgdDeviceVo;
import com.djzhgd.module.result.PageResult;
import com.djzhgd.module.result.ResultEnum;
import com.djzhgd.project.system.service.ISysDeptService;
import com.djzhgd.project.system.service.ISysDictTypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * ZhgdDevice业务实现类<br>
 *
 * @author huangjz
 * @version 1.0, 2019-04-01
 * @see
 * @since 1.0
 */
@Slf4j
@Service
@EnableConfigurationProperties(UploadFileProperties.class) // 启用资源配置读取类
public class ZhgdDeviceService extends ServiceImpl<ZhgdDeviceMapper, ZhgdDevice> implements IZhgdDeviceService {
    /**
     * 自动注入的数据访问对象
     */
    @Autowired
    private ZhgdDeviceMapper deviceMapper;

    @Autowired
    private SysUserTMapper sysUserTMapper;
    @Autowired
    private ZhgdDeviceInoutRecordMapper deviceInoutRecordMapper;

    @Value("djzhgd.upload-file.device")
    private String device;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private ISysDeptService sysDeptService;

    @Autowired
    private ISysDictTypeService sysDictTypeService;


    @Override
    public PageResult<ZhgdDeviceVo> getList(PageResult<ZhgdDeviceVo> pageResult) {


        ZhgdDeviceVo deviceVo = pageResult.getExample();
        // 查询条件封装
        LambdaQueryWrapper<ZhgdDevice> queryWrapper = Wrappers.lambdaQuery();
        // 根据字段查询
        if (deviceVo.getDeptId() != null) {
            queryWrapper.eq(ZhgdDevice::getDeptId, deviceVo.getDeptId());
        }
        if (deviceVo.getTenantId() != null) {
            queryWrapper.eq(ZhgdDevice::getTenantId, deviceVo.getTenantId());
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(deviceVo.getBeginDate())) {
            queryWrapper.ge(ZhgdDevice::getInTime, deviceVo.getBeginDate());
        }

        if (org.apache.commons.lang3.StringUtils.isNotBlank(deviceVo.getCatgegroy())) {
            queryWrapper.le(ZhgdDevice::getInTime, deviceVo.getEndDate());
        }
		/*if (org.apache.commons.lang3.StringUtils.isNotBlank(deviceVo.getDeviceName())) {
			queryWrapper.le(ZhgdDevice::getInTime, deviceVo.getEndDate());
		}*/
        if (org.apache.commons.lang3.StringUtils.isNotBlank(deviceVo.getUseLocationCode())) {
            queryWrapper.le(ZhgdDevice::getUseLocationCode, deviceVo.getUseLocationCode());
        }
        queryWrapper.eq(ZhgdDevice::getDisabled, 0);

        // 根据AnnDate倒序
        queryWrapper.orderByDesc(ZhgdDevice::getId);


        // 设置当前页和页容量
        Page<ZhgdDevice> page = new Page<>(pageResult.getPage(), pageResult.getLimit());
        // 查询总数量 和 当页的数据
        IPage<ZhgdDevice> ptUserIPage = deviceMapper.selectPage(page, queryWrapper);

        List<ZhgdDevice> devices = ptUserIPage.getRecords();
        final ArrayList<ZhgdDeviceVo> deviceVos = new ArrayList<>();
        devices.forEach(item -> {
            final ZhgdDeviceVo target = new ZhgdDeviceVo();
            BeanUtils.copyProperties(item, target);
            deviceVos.add(target);
        });
        pageResult.setData(deviceVos);
        pageResult.setCode(ResultEnum.RESULT_OK.getCode());
        pageResult.setMsg(ResultEnum.RESULT_OK.getMsg());
        pageResult.setCount(ptUserIPage.getTotal());


        return pageResult;
    }


    @Override
    public Integer saveDevice(ZhgdDevice entity, Integer sysUserId, Integer deptId) {

        SysUserT sysUserT = sysUserTMapper.selectById(sysUserId);


        if (sysUserT != null) {
            entity.setCreateUserid(sysUserId);
            entity.setCreateUsername(sysUserT.getUserName());
        }
        entity.setDeptId(deptId);
        entity.setInOutStatus("0");
        int insert = deviceMapper.insert(entity);

        //保存进场记录
        if (entity.getInTime() != null) {
            ZhgdDeviceInoutRecord zhgdDeviceInoutRecord = new ZhgdDeviceInoutRecord();
            zhgdDeviceInoutRecord.setDeviceId(sysUserId);
            zhgdDeviceInoutRecord.setRecordTime(entity.getInTime());
            zhgdDeviceInoutRecord.setRecordType("0");//进场
            if (sysUserT != null) {
                zhgdDeviceInoutRecord.setCreateUsername(sysUserT.getUserName());
            }
            deviceInoutRecordMapper.insert(zhgdDeviceInoutRecord);
        }
        return sysUserId;
    }

    /**
     * 删除
     */
    @Override
    public Integer deleted(Integer id, Integer sysUserId) {

        SysUserT sysUserT = sysUserTMapper.selectById(sysUserId);
        ZhgdDevice zhgdDevice = deviceMapper.selectById(id);
        zhgdDevice.setDisabled(1);
        if (null != sysUserT) {
            zhgdDevice.setUpdateDatetime(new Date());
            zhgdDevice.setUpdateUserid(sysUserId);
            zhgdDevice.setUpdateUsername(sysUserT.getUserName());
        }
        deviceMapper.updateById(zhgdDevice);
        return id;
    }

    @Override
    public Integer updateDevice(ZhgdDevice entity, String enterDate, Integer sysUserId) {
        ZhgdDevice zhgdDevice = deviceMapper.selectById(entity.getId());


        BeanUtils.copyProperties(entity, zhgdDevice);
        SysUserT sysUserT = sysUserTMapper.selectById(sysUserId);
        if (null != sysUserT) {
            zhgdDevice.setUpdateUserid(sysUserId);
            zhgdDevice.setUpdateUsername(sysUserT.getUserName());
        }
        zhgdDevice.setUpdateDatetime(new Date());
        deviceMapper.updateById(zhgdDevice);

        return entity.getId();
    }

    @Override
    public Integer download(Integer id, HttpServletRequest request, HttpServletResponse response) {
        ZhgdDevice zhgdDevice = deviceMapper.selectById(id);
        String fileName = zhgdDevice.getAttachmentId();
        String fileNameAft = "";
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/msword");
        try {
            Boolean flag = request.getHeader("User-Agent").indexOf("like Gecko") > 0;
            // firefox浏览器
            if (request.getHeader("User-Agent").toLowerCase()
                    .indexOf("firefox") > 0) {
                fileNameAft = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            } else if (request.getHeader("User-Agent").toUpperCase()
                    .indexOf("MSIE") > 0 || flag) {
                // IE浏览器
                fileNameAft = URLEncoder.encode(fileName, "UTF-8");
            } else if (request.getHeader("User-Agent").toUpperCase()
                    .indexOf("CHROME") > 0) {
                fileNameAft = new String(fileName.getBytes("UTF-8"), "ISO8859-1");// 谷歌
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment; filename=" + fileNameAft);

        FileUploadUtils.downloadByFlow(device, fileName, request, response);
        return id;
    }

    @Override
    public String downloadFile(String name, HttpServletRequest request, HttpServletResponse response) {
        FileUploadUtils.downloadByFlow(device, name, request, response);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/msword");
        try {
            Boolean flag = request.getHeader("User-Agent").indexOf("like Gecko") > 0;
            // firefox浏览器
            if (request.getHeader("User-Agent").toLowerCase()
                    .indexOf("firefox") > 0) {
                name = new String(name.getBytes("UTF-8"), "ISO8859-1");
            } else if (request.getHeader("User-Agent").toUpperCase()
                    .indexOf("MSIE") > 0 || flag) {
                // IE浏览器
                name = URLEncoder.encode(name, "UTF-8");
            } else if (request.getHeader("User-Agent").toUpperCase()
                    .indexOf("CHROME") > 0) {
                name = new String(name.getBytes("UTF-8"), "ISO8859-1");// 谷歌
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment; filename=" + name);
        return name;
    }

    @Override
    public Map<String, String> uploadFile(MultipartFile file) {
        Map<String, String> map = new HashMap<>();

        try {
            String fileName = FileUploadUtils.upload(device, file);
            map.put("title", fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public ZhgdDevice getDetail(Integer id) {
        ZhgdDevice zhgdDevice = deviceMapper.selectById(id);
        return zhgdDevice;
    }

    @Override
    public ZhgdDevice detail(Integer id) {
        ZhgdDevice zhgdDevice = deviceMapper.selectById(id);
        return zhgdDevice;
    }

    @Override
    public List<ZhgdDevice> queryByDeptId(Integer zhgdDeptId) {
        Map<String, Object> tempMap = new HashMap<>();
        tempMap.put("zhgdDeptId", zhgdDeptId);
//TODO where disabled = 0
//	    and zhgd_dept_id = #zhgdDeptId#
//	    and create_datetime between CONVERT(varchar(100), GETDATE(), 23) and CONVERT(varchar(100), GETDATE(), 120)
        List<ZhgdDevice> devices = deviceMapper.selectByMap(tempMap);

        return devices;
    }

    @Override
    public List<ZhgdDevice> queryMainByDeptId(Integer zhgdDeptId) {
        Map<String, Object> tempMap = new HashMap<>();
        tempMap.put("zhgdDeptId", zhgdDeptId);
        //SELECT
        //			a.id
        //		FROM
        //			zhgd_device a,
        //			zhgd_device_maintain b
        //		WHERE
        //			a.id = b.device_id
        //		and a.zhgd_dept_id = #zhgdDeptId#
        //		AND b.next_maintain_time >= CONVERT (VARCHAR(100), GETDATE(), 23)
        //		GROUP BY a.id

        List<ZhgdDevice> devices = deviceMapper.selectByMap(tempMap);
        return devices;
    }

    @Override
    public List<ZhgdDevice> queryJCByDeptId(Integer zhgdDeptId) {

        //SELECT
        //			a.id
        //		FROM
        //			zhgd_device a,
        //			zhgd_device_check b
        //		WHERE
        //			a.id = b.device_id
        //		and a.zhgd_dept_id = #zhgdDeptId#
        //		AND b.next_check_time >= CONVERT (VARCHAR(100), GETDATE(), 23)
        //		GROUP BY a.id

        Map<String, Object> tempMap = new HashMap<>();
        tempMap.put("zhgdDeptId", zhgdDeptId);
        List<ZhgdDevice> devices = deviceMapper.selectByMap(tempMap);
        //deviceMapper.queryJCByDeptId(tempMap)
        return devices;
    }

    @Override
    public List<ZhgdDevice> getDeviceCount(String startTime, String endTime, Integer type) {
        Map<String, Object> map = new HashMap<>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        List<ZhgdDevice> devices = deviceMapper.selectByMap(map);
        //deviceMapper.getDeviceCount(map)

        return devices;
    }

    //台账导出exportExcelByDeptId
    @Override
    public Integer exportExcelByDeptId(Integer deptId, HttpServletResponse response) {
        // 声明一个工作薄
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet();
        //设置基础样式
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        //TODO 报错
        //style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        Font font = workbook.createFont();
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 20);
        // 设置表头
        Row titleRow = sheet.createRow(0);// 表头 rowIndex=0
        titleRow.createCell(0).setCellValue("设备名称");
        titleRow.createCell(1).setCellValue("规格型号");
        titleRow.createCell(2).setCellValue("设备分类");
        titleRow.createCell(3).setCellValue("使用地点");
        titleRow.createCell(4).setCellValue("状态");
        titleRow.createCell(5).setCellValue("进场时间");
        titleRow.createCell(6).setCellValue("出场时间");
        titleRow.createCell(7).setCellValue("操作负责人");
        titleRow.getCell(0).setCellStyle(style);
        titleRow.getCell(1).setCellStyle(style);
        titleRow.getCell(2).setCellStyle(style);
        titleRow.getCell(3).setCellStyle(style);
        titleRow.getCell(4).setCellStyle(style);
        titleRow.getCell(5).setCellStyle(style);
        titleRow.getCell(6).setCellStyle(style);
        titleRow.getCell(7).setCellStyle(style);
        //填充考勤数据
        HashMap<String, Object> map = new HashMap<>();
        map.put("zhgdDeptId", deptId);
        //List<ZhgdDevice> zhgdDeviceList = deviceMapper.queryList(map);
        List<ZhgdDevice> zhgdDeviceList = deviceMapper.selectByMap(map);
//		workbook.setSheetName(0, zhgdusers.get(0).getUserName());
        int rowIndex = 1;
        for (int i = 0; i < zhgdDeviceList.size(); i++) {
            Row row = sheet.createRow(rowIndex);
            //row.createCell(0).setCellValue(zhgdDeviceList.get(i).getDeviceName());//设备名称
            // row.createCell(1).setCellValue(zhgdDeviceList.get(i).getModel());//规格型号
            //row.createCell(2).setCellValue(zhgdDeviceList.get(i).getDeviceClass());//设备分类
            // row.createCell(3).setCellValue(zhgdDeviceList.get(i).getUseLocation());//使用地点
            if (zhgdDeviceList.get(i).getInOutStatus().equals("0")) {
                row.createCell(4).setCellValue("进场");
            } else if (zhgdDeviceList.get(i).getInOutStatus().equals("1")) {
                row.createCell(4).setCellValue("出场");
            } else {
                row.createCell(4).setCellValue("");
            }
            row.createCell(5).setCellValue(zhgdDeviceList.get(i).getInTime());//进场时间
            row.createCell(6).setCellValue(zhgdDeviceList.get(i).getOutTime());//出场
            //row.createCell(7).setCellValue(zhgdDeviceList.get(i).getZhgdUserName());//操作负责人
            rowIndex++;
        }
        String name = "设备信息台账";
        //下载
        downloadExcel(name, response, workbook);

        return zhgdDeviceList.size();
    }

    private void downloadExcel(String name, HttpServletResponse response, SXSSFWorkbook workbook) {
        BufferedOutputStream fos = null;
        try {
            String fileName = name + ".xlsx";
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
            fos = new BufferedOutputStream(response.getOutputStream());
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public List<ZhgdDevice> getPrint(String ids) {
		/*Map<String, Object> map = new HashMap<>();
		String pids = "";
		if(ids!=null && ids!=""){
			String[] idsArray= ids.split(",");
			for(int i=0;i<idsArray.length;i++){
			    pids +="'"+idsArray[i]+"',";
		  }
		  pids = pids.substring(0,pids.length() - 1);
		}
		map.put("pids", pids);
		//根据pids查询设备信息
		List<ZhgdDevice> list = deviceMapper.getPrint(map);
		for (ZhgdDevice d : list) {
			if (StringUtils.isEmpty(d.getQrCodePicture())) {
				// 生成二维码
				String url= Constants.QR_CODE_URL + "deviceQRInfo.html?id=" + d.getId();
				String path = deviceFilePath + "\\weixin\\qr_" + d.getId() + ".png";
				File qrcFile = new File(path);
				try {
					//不带logo的二维码
					QRCodeKit.writeToFile(url, "png", qrcFile,"");
				} catch (Exception e) {
					e.printStackTrace();
				}
				d.setQrCodePicture("qr_" + d.getId() + ".png");
				ZhgdDevice updateInfo = deviceMapper.getById(d.getId());
				updateInfo.setQrCodePicture("qr_" + d.getId() + ".png");
				deviceMapper.update(updateInfo);
			}
		}*/
        return null;
    }

    @Override
    public List<ZhgdDevice> queryByDeptIdAll(Integer zhgdDeptId) {
		/*Map<String, Object> tempMap = new HashMap<>();
		tempMap.put("zhgdDeptId", zhgdDeptId);
		return deviceMapper.queryByDeptIdAll(tempMap);*/

        return null;
    }


    @Override
    public PageResult<ZhgdDeviceVo> getDeviceByDeptCode(PageResult<ZhgdDeviceVo> pageResult) {
		/*String detailType = MapUtils.getString(pageReq.getKeywords(), "detailType", "");
        String currentDeptCode = MapUtils.getString(pageReq.getKeywords(), "currentDeptCode", "");
        // 查询部门id
        QueryParams<ZhgdDept> queryParams = new QueryParams<ZhgdDept>();
        ZhgdDept zhgdDept = new ZhgdDept();
        zhgdDept.setDeptCode(currentDeptCode);
        queryParams.setEntity(zhgdDept);
        List<ZhgdDept> depts = zhgdDeptService.queryAll(queryParams);
        Integer deptId = depts.get(0).getId();
        
        Page page = new Page();
        page.setPageSize(pageReq.getPagesize());
        page.setCurrentPage(pageReq.getPage());
        List<ZhgdDevice> page1 = new ArrayList<>();
        List<ZhgdDevice> ret = new ArrayList<>();
        List<ZhgdDevice> list = queryByDeptIdAll(deptId);
        if ("1".equals(detailType)) {
        	for (ZhgdDevice e : list) {
    			if (DictionaryConst.SPECIAL_EQUIPMENT.getValue().equals(e.getCatgegroy())
    					|| DictionaryConst.CRUS_EQUIPMENT.getValue().equals(e.getCatgegroy())
    					|| DictionaryConst.MAIN_EQUIPMENT.getValue().equals(e.getCatgegroy())) {
    				if (null == e.getZhgdUserId()) {
    					ret.add(e);
    				}
    			}
    		}
        } else if ("2".equals(detailType)) {
        	List<ZhgdDevice> mainlist = queryMainByDeptId(deptId);
        	for (ZhgdDevice e : list) {
        		if (!mainlist.contains(e)) {
        			ret.add(e);
        		}
        	}
        } else if ("3".equals(detailType)) {
        	List<ZhgdDevice> jclist = queryJCByDeptId(deptId);
        	for (ZhgdDevice e : list) {
        		if (!jclist.contains(e)) {
        			ret.add(e);
        		}
        	}
        }
        // 查询设备名称、设备分类、使用地点
        List<ZhgdDict> name = sysDictTypeService.getByGroupCode(DictionaryConst.DEVICE_NAME.getValue());
        List<ZhgdDict> category = sysDictTypeService.getByGroupCode(DictionaryConst.DEVICE_CLASS.getValue());
        List<ZhgdDict> local = sysDictTypeService.getByGroupCode(DictionaryConst.USE_LOCATION.getValue());
        for (ZhgdDevice e : ret) {
        	for (ZhgdDict d : name) {
        		if (e.getNumber().equals(d.getDataCode())) {
        			e.setDeviceName(d.getDataDesc());
        			break;
        		}
        	}
        	for (ZhgdDict d : category) {
        		if (e.getCatgegroy().equals(d.getDataCode())) {
        			e.setDeviceClass(d.getDataDesc());
        			break;
        		}
        	}
        	for (ZhgdDict d : local) {
        		if (e.getUseLocationCode().equals(d.getDataCode())) {
        			e.setUseLocation(d.getDataDesc());
        			break;
        		}
        	}
        }
        page.setPages(pageReq.getPage());
		page.setRecords(ret.size());
		Pager<ZhgdDevice> pagers = Pager.create(ret, pageReq.getPagesize());
		page1 = pagers.getPagedList(pageReq.getPage());
		return new PageResp<>(page, page1);*/
        return null;
    }

    @Override
    public List<ZhgdDevice> getEquInfo(String ids) {
		/*Map<String, Object> map = new HashMap<>();
		String pids = "";
		if(ids!=null && ids!=""){
			String[] idsArray= ids.split(",");
			for(int i=0;i<idsArray.length;i++){
			    pids +="'"+idsArray[i]+"',";
		  }
		  pids = pids.substring(0,pids.length() - 1);
		}
		map.put("pids", pids);
		//根据pids查询设备信息
		List<ZhgdDevice> list = deviceMapper.getPrint(map);
		for (ZhgdDevice d : list) {
			if (StringUtils.isEmpty(d.getQrCodePicture())) {
				// 生成二维码
				String url= Constants.QR_CODE_URL + "deviceQRInfo.html?id=" + d.getId();
				String path = deviceFilePath + "\\weixin\\qr_" + d.getId() + ".png";
				File qrcFile = new File(path);
				try {
					//不带logo的二维码
					QRCodeKit.writeToFile(url, "png", qrcFile,"");
				} catch (Exception e) {
					e.printStackTrace();
				}
				d.setQrCodePicture("qr_" + d.getId() + ".png");
				ZhgdDevice updateInfo = deviceMapper.getById(d.getId());
				updateInfo.setQrCodePicture("qr_" + d.getId() + ".png");
				deviceMapper.update(updateInfo);
			}
		}
		return list;*/
        return null;
    }

    @Override
    public String downloadQRFile(String name, HttpServletResponse response) {
		/*FileUtils.downloadFile(deviceFilePath + "\\weixin\\" + name, response);
		response.setCharacterEncoding("utf-8");  
        response.setContentType("application/msword"); 
        try {
			Boolean flag= request.getHeader("User-Agent").indexOf("like Gecko")>0;
			// firefox浏览器
			if (request.getHeader("User-Agent").toLowerCase()
					.indexOf("firefox") > 0) {
				name = new String(name.getBytes("UTF-8"), "ISO8859-1");
			} else if (request.getHeader("User-Agent").toUpperCase()
					.indexOf("MSIE") > 0 || flag) {
				// IE浏览器
				name = URLEncoder.encode(name, "UTF-8");
			} else if (request.getHeader("User-Agent").toUpperCase()
					.indexOf("CHROME") > 0) {
				name = new String(name.getBytes("UTF-8"), "ISO8859-1");// 谷歌
			} 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        response.setHeader("Content-Disposition", "attachment; filename=" + name); */
        return null;
    }

    @Override
    public Map<String, Object> getDeviceData() {
		/*Map<String, Object> map = new HashMap<>();
		List<ZhgdDevice> equipmentPartyList = deviceMapper.getEquipmentPartyList();
		List<ZhgdDevice> equipmentEnterList = deviceMapper.equipmentEnterList();
		List<ZhgdDevice> equipmentList = deviceMapper.getEquipmentList();
		List<ZhgdDevice> equipmentExportList = deviceMapper.getEquipmentExportList();
		
		map.put("equipmentPartyList", equipmentPartyList);
		map.put("equipmentEnterList", equipmentEnterList);
		map.put("equipmentList", equipmentList);
		map.put("equipmentExportList", equipmentExportList);
		map.put("code", "0");*/
        return null;
    }

    @Override
    public List<ZhgdDevice> getEquipmentList() {
        //deviceMapper.getEquipmentList()

        return null;
    }
}