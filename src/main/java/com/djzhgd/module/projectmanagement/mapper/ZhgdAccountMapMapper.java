package com.djzhgd.module.projectmanagement.mapper;

import com.djzhgd.module.projectmanagement.domain.ZhgdAccountMap;

import java.util.List;

public interface ZhgdAccountMapMapper {
    List<ZhgdAccountMap> getOtherLogin(String otherSystem,String userId,String deptId);
}