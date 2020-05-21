package com.djzhgd.project.system.mapper;

import com.djzhgd.project.system.domain.SysUser;
import com.djzhgd.project.system.domain.ZhgdDict;
import java.math.BigDecimal;
import java.util.List;

public interface ZhgdDictMapper {
    /**
     * 根据条件分页查询用户列表
     *
     * @param deptId 用户信息
     * @return 用户信息集合信息
     */
    public ZhgdDict getUrlByDeptid(Long deptId,String groupCode);

    public ZhgdDict getDataCodeByDeptid(String deptId,String dataCode);
}