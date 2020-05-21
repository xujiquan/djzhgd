package com.djzhgd.module.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.djzhgd.common.utils.StringUtils;
import com.djzhgd.framework.config.UploadFileProperties;
import com.djzhgd.framework.web.page.PageDomain;
import com.djzhgd.framework.web.page.PageReq;
import com.djzhgd.framework.web.page.TableSupport;
import com.djzhgd.module.demo.domain.ZxMaterialInfo;
import com.djzhgd.module.demo.mapper.ZxMaterialInfoMapper;
import com.djzhgd.module.demo.service.IZxMaterialInfoService;
import com.djzhgd.module.projectmanagement.domain.ZhgdMeetingContent;
import com.djzhgd.module.projectmanagement.mapper.ZhgdMeetingContentMapper;
import com.djzhgd.module.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 原材料评估Service业务层处理
 *
 * @author suenle
 * @date 2020-03-26
 */
@Service
public class ZxMaterialInfoServiceImpl extends ServiceImpl<ZxMaterialInfoMapper, ZxMaterialInfo> implements IZxMaterialInfoService {

    @Autowired
    private ZxMaterialInfoMapper zxMaterialInfoMapper;
    @Override
    public IPage<ZxMaterialInfo> list(ZxMaterialInfo zxMaterialInfo) {

        LambdaQueryWrapper<ZxMaterialInfo> queryWrapper = new LambdaQueryWrapper<ZxMaterialInfo>();
        if (zxMaterialInfo.getDeptId() != null) {
            queryWrapper.eq(ZxMaterialInfo::getDeptId, zxMaterialInfo.getDeptId());
        }
        if (zxMaterialInfo.getParentId() != null) {
            queryWrapper.eq(ZxMaterialInfo::getParentId, zxMaterialInfo.getParentId());
        }
        if (StringUtils.isNotBlank(zxMaterialInfo.getMaterialType())) {
            queryWrapper.eq(ZxMaterialInfo::getMaterialType, zxMaterialInfo.getMaterialType());
        }
        if (StringUtils.isNotBlank(zxMaterialInfo.getMaterialName())) {
            queryWrapper.like(ZxMaterialInfo::getMaterialName, zxMaterialInfo.getMaterialName());
        }
        if (StringUtils.isNotBlank(zxMaterialInfo.getCompanyName())) {
            queryWrapper.like(ZxMaterialInfo::getCompanyName, zxMaterialInfo.getCompanyName());
        }
        if (StringUtils.isNotBlank(zxMaterialInfo.getCompanyAddress())) {
            queryWrapper.eq(ZxMaterialInfo::getCompanyAddress, zxMaterialInfo.getCompanyAddress());
        }
        if (StringUtils.isNotBlank(zxMaterialInfo.getBusinessScope())) {
            queryWrapper.eq(ZxMaterialInfo::getBusinessScope, zxMaterialInfo.getBusinessScope());
        }
        if (StringUtils.isNotBlank(zxMaterialInfo.getProduction())) {
            queryWrapper.eq(ZxMaterialInfo::getProduction, zxMaterialInfo.getProduction());
        }
        if (StringUtils.isNotBlank(zxMaterialInfo.getDistance())) {
            queryWrapper.eq(ZxMaterialInfo::getDistance, zxMaterialInfo.getDistance());
        }
        if (StringUtils.isNotBlank(zxMaterialInfo.getOtherDistance())) {
            queryWrapper.eq(ZxMaterialInfo::getOtherDistance, zxMaterialInfo.getOtherDistance());
        }
        if (StringUtils.isNotBlank(zxMaterialInfo.getTransportation())) {
            queryWrapper.eq(ZxMaterialInfo::getTransportation, zxMaterialInfo.getTransportation());
        }
        if (StringUtils.isNotBlank(zxMaterialInfo.getStructure())) {
            queryWrapper.eq(ZxMaterialInfo::getStructure, zxMaterialInfo.getStructure());
        }
        if (StringUtils.isNotBlank(zxMaterialInfo.getDetailedType())) {
            queryWrapper.eq(ZxMaterialInfo::getDetailedType, zxMaterialInfo.getDetailedType());
        }
        if (StringUtils.isNotBlank(zxMaterialInfo.getMaterialModel())) {
            queryWrapper.eq(ZxMaterialInfo::getMaterialModel, zxMaterialInfo.getMaterialModel());
        }
        if (StringUtils.isNotBlank(zxMaterialInfo.getIndicators())) {
            queryWrapper.eq(ZxMaterialInfo::getIndicators, zxMaterialInfo.getIndicators());
        }
        if (StringUtils.isNotBlank(zxMaterialInfo.getAttachment())) {
            queryWrapper.eq(ZxMaterialInfo::getAttachment, zxMaterialInfo.getAttachment());
        }
        if (StringUtils.isNotBlank(zxMaterialInfo.getRemark())) {
            queryWrapper.eq(ZxMaterialInfo::getRemark, zxMaterialInfo.getRemark());
        }
        if (StringUtils.isNotBlank(zxMaterialInfo.getStatus())) {
            queryWrapper.eq(ZxMaterialInfo::getStatus, zxMaterialInfo.getStatus());
        }
        if (StringUtils.isNotBlank(zxMaterialInfo.getDelFlag())) {
            queryWrapper.eq(ZxMaterialInfo::getDelFlag, zxMaterialInfo.getDelFlag());
        }
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        Page<ZxMaterialInfo> page = new Page<ZxMaterialInfo>(pageNum, pageSize);
        IPage<ZxMaterialInfo> Page = zxMaterialInfoMapper.selectPage(page, queryWrapper);

        return Page;
    }

    public ZxMaterialInfo selectById(Long materialId) {
        ZxMaterialInfo zxMaterialInfo = zxMaterialInfoMapper.selectById(materialId);
        return zxMaterialInfo;
    }

}
