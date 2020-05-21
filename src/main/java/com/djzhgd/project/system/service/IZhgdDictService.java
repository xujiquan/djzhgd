package com.djzhgd.project.system.service;

import com.djzhgd.project.system.domain.SysUser;
import com.djzhgd.project.system.domain.ZhgdDict;

import java.util.List;

/**
 * 字典表
 * 
 * @author djzhgd
 */
public interface IZhgdDictService
{

    /**
     * 通过用户名查询用户
     * 
     * @param deptId 用户名
     * @return 用户对象信息
     */
    public ZhgdDict getUrlByDeptid(Long deptId,String groupCode);

    public ZhgdDict getDataCodeByDeptid(String deptId,String dataCode);
}
