package com.djzhgd.module.projectmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djzhgd.framework.web.page.PageDomain;
import com.djzhgd.framework.web.page.TableSupport;
import com.djzhgd.module.projectmanagement.domain.ZhgdParticipatingUnits;
import com.djzhgd.module.projectmanagement.mapper.ZhgdParticipatingUnitsMapper;
import com.djzhgd.module.projectmanagement.service.ZhgdParticipatingUnitsService;
import com.djzhgd.module.projectmanagement.vo.ZhgdParticipatingUnitsVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @PROJECT_NAME: dj_zhgd 东交智慧品控项目
 * @ClassName: DemoServiceImpl
 * @Author: zhangheng
 * @DATE: 2020/5/13 10:42
 * @Version 1.0
 **/
@Slf4j
@Service
public class ZhgdParticipatingUnitsServiceImpl extends ServiceImpl<ZhgdParticipatingUnitsMapper, ZhgdParticipatingUnits> implements ZhgdParticipatingUnitsService {

    @Autowired
    private ZhgdParticipatingUnitsMapper zhgdParticipatingUnitsMapper;


    public IPage<ZhgdParticipatingUnits> list(ZhgdParticipatingUnits zhgdParticipatingUnits){

        LambdaQueryWrapper<ZhgdParticipatingUnits> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(zhgdParticipatingUnits.getUnitName())){
            queryWrapper.like(ZhgdParticipatingUnits::getUnitName, zhgdParticipatingUnits.getUnitName());
        }
        if(zhgdParticipatingUnits.getUnitType()!=null){
            queryWrapper.eq(ZhgdParticipatingUnits::getUnitType, zhgdParticipatingUnits.getUnitType());
        }
        if(zhgdParticipatingUnits.getDeptId()!=null){
            queryWrapper.eq(ZhgdParticipatingUnits::getDeptId, zhgdParticipatingUnits.getDeptId());
        }
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        Page<ZhgdParticipatingUnits> page = new Page<>(pageNum, pageSize);
        IPage<ZhgdParticipatingUnits> Page = zhgdParticipatingUnitsMapper.selectPage(page, queryWrapper);
        return Page;
    }

}
