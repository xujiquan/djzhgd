package com.djzhgd.module.projectmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.djzhgd.module.projectmanagement.domain.ZhgdProjectIntroduction;
import com.djzhgd.module.projectmanagement.mapper.ZhgdProjectIntroductionMapper;
import com.djzhgd.module.projectmanagement.result.ZhgdProjectIntroductionToVo;
import com.djzhgd.module.projectmanagement.service.ZhgdProjectIntroductionService;

import com.djzhgd.module.projectmanagement.vo.ZhgdParticipatingUnitsVo;
import com.djzhgd.module.projectmanagement.vo.ZhgdProjectIntroductionVo;
import com.djzhgd.module.result.PageResult;
import com.djzhgd.module.result.Result;
import com.djzhgd.module.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;

/**
 * @PROJECT_NAME: dj_zhgd 东交智慧品控项目
 * @ClassName: DemoServiceImpl
 * @Author: zhangheng
 * @DATE: 2020/5/13 10:42
 * @Version 1.0
 **/
@Slf4j
@Service
public class ZhgdProjectIntroductionServiceImpl extends ServiceImpl<ZhgdProjectIntroductionMapper, ZhgdProjectIntroduction> implements ZhgdProjectIntroductionService{

    @Autowired
    private ZhgdProjectIntroductionMapper zhgdProjectIntroductionMapper;

    /**
     * 查询demo模块的列表(分页)
     * @param pageResult
     * @return
     */
    @Transactional
    public PageResult<ZhgdProjectIntroductionVo> getZhgdProjectIntroductionList(PageResult<ZhgdProjectIntroductionVo> pageResult) {
        log.info("ZhgdProjectIntroduction Modular Query start");
        try{
            ZhgdProjectIntroductionVo zhgdProjectIntroductionVo = pageResult.getExample();
            // 查询条件封装
            //QueryWrapper<Demo> queryWrapper = new QueryWrapper<>();
            LambdaQueryWrapper<ZhgdProjectIntroduction> queryWrapper = Wrappers.lambdaQuery();
            // 根据dept_code字段查询
            if(StringUtils.isNotBlank(zhgdProjectIntroductionVo.getDeptIdVo())){
                queryWrapper.eq(ZhgdProjectIntroduction::getDeptId, zhgdProjectIntroductionVo.getDeptIdVo());
            }

            // 根据id倒序
            queryWrapper.orderByDesc(ZhgdProjectIntroduction::getId);
            //queryWrapper.orderByDesc(SystemConstant.ID);
            // 设置当前页和页容量
            Page<ZhgdProjectIntroduction> page = new Page<>(pageResult.getPage(), pageResult.getLimit());
            // 查询总数量 和 当页的数据
            IPage<ZhgdProjectIntroduction> ptUserIPage = zhgdProjectIntroductionMapper.selectPage(page, queryWrapper);
            // 循环查询出的用户
            pageResult.setData(ZhgdProjectIntroductionToVo.ZhgdProjectIntroductionToVo(ptUserIPage.getRecords()));
            pageResult.setCode(ResultEnum.RESULT_OK.getCode());
            pageResult.setMsg(ResultEnum.RESULT_OK.getMsg());
            pageResult.setCount(ptUserIPage.getTotal());
            log.info("ZhgdProjectIntroduction Modular Query End");
        }catch(Exception e){
            log.error("ZhgdProjectIntroduction Modular Query ERROR: " + e.getMessage());
            // 回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            pageResult.setCode(ResultEnum.RESULT_ERROR.getCode());
            pageResult.setMsg(ResultEnum.RESULT_ERROR.getMsg());
            return pageResult;
        }
        return pageResult;
    }


    /**
     * 新增一条zhgdProjectIntroduction信息
     * @param zhgdProjectIntroductionVo
     * @return
     */
    @Transactional
    public Result addZhgdProjectIntroduction(ZhgdProjectIntroductionVo zhgdProjectIntroductionVo) {
        Result result = new Result();
        log.info("ZhgdProjectIntroduction Modular Add Start");
        try{
            ZhgdProjectIntroduction zhgdProjectIntroduction = ZhgdProjectIntroductionToVo.ZhgdProjectIntroductionToVoEntity(zhgdProjectIntroductionVo);
            zhgdProjectIntroduction.setCreateDatetime(new Date());
//            zhgdProjectIntroduction.setVersion(SystemConstant.INTEGER_ONE);
            zhgdProjectIntroductionMapper.insert(zhgdProjectIntroduction);
            result.setCode(ResultEnum.RESULT_INSERT_OK.getCode());
            result.setMsg(ResultEnum.RESULT_INSERT_OK.getMsg());
            log.info("ZhgdParticipatingUnits Modular Add End");
        }catch(Exception e){
            log.info("ZhgdProjectIntroduction Modular Add Error：" + e.getMessage());
            // 回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setCode(ResultEnum.RESULT_INSERT_ERROR.getCode());
            result.setMsg(ResultEnum.RESULT_INSERT_ERROR.getMsg());
            return result;
        }
        return result;
    }




    @Override
    public ZhgdProjectIntroduction detail(Integer id) {
        ZhgdProjectIntroduction zhgdProjectIntroduction = zhgdProjectIntroductionMapper.selectById(id);
        return zhgdProjectIntroduction;
    }

    @Override
    public int delete(Integer id) {
        int i = zhgdProjectIntroductionMapper.deleteById(id);
        return i;
    }

    @Override
    public int updateData(ZhgdProjectIntroduction zhgdProjectIntroduction) {
        int i = zhgdProjectIntroductionMapper.updateById(zhgdProjectIntroduction);
        return i;
    }


}
