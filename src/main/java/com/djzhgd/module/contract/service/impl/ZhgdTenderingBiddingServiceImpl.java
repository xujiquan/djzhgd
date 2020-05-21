package com.djzhgd.module.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djzhgd.common.utils.file.FileUploadUtils;
import com.djzhgd.framework.config.UploadFileProperties;
import com.djzhgd.framework.web.page.PageDomain;
import com.djzhgd.framework.web.page.TableSupport;
import com.djzhgd.module.constants.SystemConstant;
import com.djzhgd.module.contract.domain.ZhgdTenderingBidding;
import com.djzhgd.module.contract.domain.vo.ZhgdTenderingBiddingVo;
import com.djzhgd.module.contract.mapper.ZhgdTenderingBiddingMapper;
import com.djzhgd.module.contract.service.IZhgdTenderingBiddingService;
import com.djzhgd.project.system.domain.ZhgdFile;
import com.djzhgd.project.system.mapper.ZhgdFileMapper;
import com.djzhgd.project.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * @PROJECT_NAME: dj_zhgd 东交智慧品控项目
 * @ClassName: DemoServiceImpl
 * @Author: zhangheng
 * @DATE: 2020/5/13 10:42
 * @Version 1.0
 **/
@Slf4j
@Service
@EnableConfigurationProperties(UploadFileProperties.class) // 启用资源配置读取类
public class ZhgdTenderingBiddingServiceImpl extends ServiceImpl<ZhgdTenderingBiddingMapper, ZhgdTenderingBidding> implements IZhgdTenderingBiddingService {

    @Autowired
    private ZhgdTenderingBiddingMapper zhgdTenderingBiddingMapper;

    @Autowired
    private ZhgdFileMapper zhgdFileMapper;

    @Autowired
    private UploadFileProperties uploadFileProperties;

    @Override
    public IPage<ZhgdTenderingBidding> list(ZhgdTenderingBiddingVo zhgdTenderingBiddingVo){

        LambdaQueryWrapper<ZhgdTenderingBidding> queryWrapper = new LambdaQueryWrapper<>();
        try {
            if (zhgdTenderingBiddingVo.getDeptId() != null) {
                queryWrapper.eq(ZhgdTenderingBidding::getDeptId, zhgdTenderingBiddingVo.getDeptId());
            }
            if (zhgdTenderingBiddingVo.getAssTitle() != null) {
                queryWrapper.like(ZhgdTenderingBidding::getAssTitle, zhgdTenderingBiddingVo.getAssTitle());
            }
            if (zhgdTenderingBiddingVo.getUpUser() != null) {
                queryWrapper.eq(ZhgdTenderingBidding::getUpUser, zhgdTenderingBiddingVo.getUpUser());
            }
            if (org.apache.commons.lang3.StringUtils.isNotBlank(zhgdTenderingBiddingVo.getBeginDate())) {
                queryWrapper.ge(ZhgdTenderingBidding::getCreateDatetime, DateUtil.getYYYYMMDDHHMMSS(zhgdTenderingBiddingVo.getBeginDate()));
            }
            if (org.apache.commons.lang3.StringUtils.isNotBlank(zhgdTenderingBiddingVo.getEndDate())) {
                queryWrapper.le(ZhgdTenderingBidding::getCreateDatetime, DateUtil.getYYYYMMDDHHMMSS(zhgdTenderingBiddingVo.getEndDate()));
            }
        }catch (ParseException e){

        }
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        Page<ZhgdTenderingBidding> page = new Page<>(pageNum, pageSize);
        IPage<ZhgdTenderingBidding> Page = zhgdTenderingBiddingMapper.selectPage(page, queryWrapper);
        return Page;
    }

    @Override
    public ZhgdTenderingBiddingVo detail(Integer id) {
        final ZhgdTenderingBidding zhgdTenderingBidding = zhgdTenderingBiddingMapper.selectById(id);
        final ZhgdTenderingBiddingVo target = new ZhgdTenderingBiddingVo();
        BeanUtils.copyProperties(zhgdTenderingBidding, target);
        LambdaQueryWrapper<ZhgdFile> queryWrapper = Wrappers.lambdaQuery();
        // 根据字段查询
        if (target.getId() != null) {
            queryWrapper.eq(ZhgdFile::getParentId, target.getId());
        }
        if (target.getDeptId() != null) {
            queryWrapper.eq(ZhgdFile::getDeptId, target.getDeptId());
        }
        queryWrapper.eq(ZhgdFile::getForm, 2);
        List<ZhgdFile> zhgdFileList = zhgdFileMapper.selectList(queryWrapper);
        List<String> fileNames = new ArrayList<>();
        zhgdFileList.forEach(item -> {
            fileNames.add(item.getFileName());
        });
        target.setFileNames(fileNames);
        target.setFilePath(this.uploadFileProperties.getTenderingBidding());
        return target;
    }

    @Override
    public int delete(Integer id) {
        final int i = zhgdTenderingBiddingMapper.deleteById(id);
        return i;
    }

    @Override
    public boolean saveData(ZhgdTenderingBiddingVo zhgdTenderingBiddingVo) {
        boolean flag = true;
        ZhgdTenderingBiddingServiceImpl.log.info("ZhgdParticipatingUnits Modular Add Start");
        try {
            final ZhgdTenderingBidding target = new ZhgdTenderingBidding();
            BeanUtils.copyProperties(zhgdTenderingBiddingVo, target);
            target.setVersion(SystemConstant.INTEGER_ONE);
            final int i = zhgdTenderingBiddingMapper.insert(target);
            for (String fileName : zhgdTenderingBiddingVo.getFileNames()) {
                ZhgdFile zhgdFile = new ZhgdFile();
                zhgdFile.setParentId(target.getId());
                zhgdFile.setForm(2);
                zhgdFile.setDeptId(target.getDeptId());
                zhgdFile.setFileName(fileName);
                zhgdFile.setFilePath(this.uploadFileProperties.getTenderingBidding());
                zhgdFile.setVersion(SystemConstant.INTEGER_ONE);
                int j = zhgdFileMapper.insert(zhgdFile);
            }
            ZhgdTenderingBiddingServiceImpl.log.info("ZhgdParticipatingUnits Modular Add End");
            return flag;
        } catch (Exception e) {
            ZhgdTenderingBiddingServiceImpl.log.info("ZhgdParticipatingUnits Modular Add Error：" + e.getMessage());
            flag=false;
            return flag;
        }
    }

    @Override
    public boolean updateData(ZhgdTenderingBiddingVo zhgdTenderingBiddingVo) {
        boolean flag = true;
        ZhgdTenderingBiddingServiceImpl.log.info("ZhgdParticipatingUnits Modular Add Start");
        try {
            final ZhgdTenderingBidding target = new ZhgdTenderingBidding();
            BeanUtils.copyProperties(zhgdTenderingBiddingVo, target);
            final int i = zhgdTenderingBiddingMapper.updateById(target);
            LambdaQueryWrapper<ZhgdFile> queryWrapper = Wrappers.lambdaQuery();
            // 根据字段查询
            if (target.getId() != null) {
                queryWrapper.eq(ZhgdFile::getParentId, target.getId());
            }
            if (target.getDeptId() != null) {
                queryWrapper.eq(ZhgdFile::getDeptId, target.getDeptId());
            }
            queryWrapper.eq(ZhgdFile::getForm, 2);
           /* List<ZhgdFile> zhgdFileList = zhgdFileMapper.selectList(queryWrapper);
            zhgdFileList.forEach(item -> {
                zhgdFileMapper.deleteById(item.getId());
            });*/
            zhgdFileMapper.delete(queryWrapper);
            for (String fileName : zhgdTenderingBiddingVo.getFileNames()) {
                ZhgdFile zhgdFile = new ZhgdFile();
                zhgdFile.setParentId(target.getId());
                zhgdFile.setForm(2);
                zhgdFile.setDeptId(target.getDeptId());
                zhgdFile.setFileName(fileName);
                zhgdFile.setFilePath(this.uploadFileProperties.getTenderingBidding());
                zhgdFile.setVersion(SystemConstant.INTEGER_ONE);
                int j = zhgdFileMapper.insert(zhgdFile);
            }
            ZhgdTenderingBiddingServiceImpl.log.info("ZhgdParticipatingUnits Modular Add End");
            return flag;
        } catch (Exception e) {
            ZhgdTenderingBiddingServiceImpl.log.info("ZhgdParticipatingUnits Modular Add Error：" + e.getMessage());
            flag=false;
            return flag;
        }
    }

    @Override
    public Map<String, Object> uploadFile(MultipartFile uploadFile) throws IOException {

        String filePath = this.uploadFileProperties.getTenderingBidding();
        final String fileName = FileUploadUtils.upload(filePath, uploadFile);
        Map<String, Object> map = new HashMap<>();

        map.put("fileName", fileName);

        return map;
    }


}
