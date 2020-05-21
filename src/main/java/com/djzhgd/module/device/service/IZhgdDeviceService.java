/**
 * Description: ZhgdDevice业务接口
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
package com.djzhgd.module.device.service;

import com.djzhgd.module.device.domain.ZhgdDevice;
import com.djzhgd.module.device.vo.ZhgdDeviceVo;
import com.djzhgd.module.result.PageResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * ZhgdDevice业务接口<br>
 *
 * @author huangjz
 * @version 1.0, 2019-04-01
 * @see
 * @since 1.0
 */
public interface IZhgdDeviceService {

    /**
     * 查询列表展示
     */
    PageResult<ZhgdDeviceVo> getList(PageResult<ZhgdDeviceVo> pageResult);

    Integer saveDevice(ZhgdDevice entity, Integer sysUserId, Integer deptId);

    Integer deleted(Integer id, Integer sysUserId);

    Integer updateDevice(ZhgdDevice entity, String enterDate, Integer sysUserId);

    Integer download(Integer id, HttpServletRequest request, HttpServletResponse response);

    String downloadFile(String name, HttpServletRequest request, HttpServletResponse response);

    /**
     * 上传附件或者图片
     */
    Map<String, String> uploadFile(MultipartFile file);


    /**
     * 详情
     */
    ZhgdDevice detail(Integer id);

    List<ZhgdDevice> queryByDeptId(Integer zhgdDeptId);

    List<ZhgdDevice> queryMainByDeptId(Integer zhgdDeptId);

    List<ZhgdDevice> queryJCByDeptId(Integer zhgdDeptId);

    /**
     * 台账导出
     */
    Integer exportExcelByDeptId(Integer deptId, HttpServletResponse response);

    /**
     * 统计
     */
    List<ZhgdDevice> getDeviceCount(String startTime, String endTime, Integer type);

    /**
     * 打印查询
     */
    List<ZhgdDevice> getPrint(String ids);

    List<ZhgdDevice> queryByDeptIdAll(Integer zhgdDeptId);


    List<ZhgdDevice> getEquInfo(String ids);

    String downloadQRFile(String name, HttpServletResponse response);

    Map<String, Object> getDeviceData();

    List<ZhgdDevice> getEquipmentList();


    ZhgdDevice getDetail(Integer id);

    PageResult<ZhgdDeviceVo> getDeviceByDeptCode(PageResult<ZhgdDeviceVo> pageResult);
}