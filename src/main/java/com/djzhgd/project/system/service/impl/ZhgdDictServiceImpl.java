package com.djzhgd.project.system.service.impl;

import com.djzhgd.project.system.domain.ZhgdDict;
import com.djzhgd.project.system.mapper.ZhgdDictMapper;
import com.djzhgd.project.system.service.IZhgdDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户 业务层处理
 * 
 * @author djzhgd
 */
@Service
public class ZhgdDictServiceImpl implements IZhgdDictService
{
    @Autowired
    private ZhgdDictMapper zhgdDictMapper;

    private static final Logger log = LoggerFactory.getLogger(ZhgdDictServiceImpl.class);

    @Override
    public ZhgdDict getUrlByDeptid(Long deptId,String groupCode) {
        ZhgdDict zhgdDict = zhgdDictMapper.getUrlByDeptid(deptId,groupCode);
        return zhgdDict;
    }

    @Override
    public ZhgdDict getDataCodeByDeptid(String deptId, String dataCode) {
        ZhgdDict zhgdDict = zhgdDictMapper.getDataCodeByDeptid(deptId,dataCode);
        return zhgdDict;
    }
}
