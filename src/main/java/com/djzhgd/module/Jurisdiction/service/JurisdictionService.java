package com.djzhgd.module.Jurisdiction.service;

import com.djzhgd.module.Jurisdiction.vo.Jurisdiction;
import com.djzhgd.module.result.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: JurisdictionService 权限控制层
 * @Author: zhangheng
 * @DATE: 2020/5/19 9:12
 * @Version 1.0
 **/
public interface JurisdictionService {

    // 根据用户ID查询菜单权限
    Result getSysMenuPerms(Jurisdiction jurisdiction);

    // 根据用户ID查询平台、项目信息
    Result getDeptList(Jurisdiction jurisdiction);

    // 根据平台ID查询平台下的项目
    Result getProjectList(Jurisdiction jurisdiction);

    // 根据用户ID 查询所有单位单位
    Result getAllUnit(HttpServletRequest request);

    // 获取有权限施工标段
    Result getBidSection(HttpServletRequest request);

}
