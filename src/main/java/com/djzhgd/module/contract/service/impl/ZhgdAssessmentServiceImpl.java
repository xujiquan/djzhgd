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
import com.djzhgd.module.contract.domain.ZhgdAssessment;
import com.djzhgd.module.contract.domain.vo.ZhgdAssessmentVo;
import com.djzhgd.module.contract.mapper.ZhgdAssessmentMapper;
import com.djzhgd.module.contract.service.IZhgdAssessmentService;
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
public class ZhgdAssessmentServiceImpl extends ServiceImpl<ZhgdAssessmentMapper, ZhgdAssessment> implements IZhgdAssessmentService {

    @Autowired
    private ZhgdAssessmentMapper zhgdAssessmentMapper;

    @Autowired
    private ZhgdFileMapper zhgdFileMapper;

    @Autowired
    private UploadFileProperties uploadFileProperties;


    @Override
    public IPage<ZhgdAssessment> list(ZhgdAssessmentVo zhgdAssessmentVo){

        LambdaQueryWrapper<ZhgdAssessment> queryWrapper = new LambdaQueryWrapper<>();
        try {
            if (zhgdAssessmentVo.getDeptId() != null) {
                queryWrapper.eq(ZhgdAssessment::getDeptId, zhgdAssessmentVo.getDeptId());
            }
            if (zhgdAssessmentVo.getAssTitle() != null) {
                queryWrapper.like(ZhgdAssessment::getAssTitle, zhgdAssessmentVo.getAssTitle());
            }
            if (zhgdAssessmentVo.getUpUser() != null) {
                queryWrapper.eq(ZhgdAssessment::getUpUser, zhgdAssessmentVo.getUpUser());
            }
            if (org.apache.commons.lang3.StringUtils.isNotBlank(zhgdAssessmentVo.getBeginDate())) {
                queryWrapper.ge(ZhgdAssessment::getCreateDatetime, DateUtil.getYYYYMMDDHHMMSS(zhgdAssessmentVo.getBeginDate()));
            }
            if (org.apache.commons.lang3.StringUtils.isNotBlank(zhgdAssessmentVo.getEndDate())) {
                queryWrapper.le(ZhgdAssessment::getCreateDatetime, DateUtil.getYYYYMMDDHHMMSS(zhgdAssessmentVo.getEndDate()));
            }
        }catch (ParseException e){

        }
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        Page<ZhgdAssessment> page = new Page<ZhgdAssessment>(pageNum, pageSize);
        IPage<ZhgdAssessment> Page = zhgdAssessmentMapper.selectPage(page, queryWrapper);
        return Page;
    }


    @Override
    public ZhgdAssessmentVo detail(Integer id) {
        final ZhgdAssessment zhgdAssessment = zhgdAssessmentMapper.selectById(id);
        final ZhgdAssessmentVo target = new ZhgdAssessmentVo();
        BeanUtils.copyProperties(zhgdAssessment, target);
        LambdaQueryWrapper<ZhgdFile> queryWrapper = Wrappers.lambdaQuery();
        // 根据字段查询
        if (target.getId() != null) {
            queryWrapper.eq(ZhgdFile::getParentId, target.getId());
        }
        if (target.getDeptId() != null) {
            queryWrapper.eq(ZhgdFile::getDeptId, target.getDeptId());
        }
        queryWrapper.eq(ZhgdFile::getForm, 1);
        List<ZhgdFile> zhgdFileList = zhgdFileMapper.selectList(queryWrapper);
        List<String> fileNames = new ArrayList<>();
        zhgdFileList.forEach(item -> {
            fileNames.add(item.getFileName());
        });
        target.setFileNames(fileNames);
        target.setFilePath(this.uploadFileProperties.getAssessment());
        return target;
    }

    @Override
    public int delete(Integer id) {
        final int i = zhgdAssessmentMapper.deleteById(id);
        return i;
    }

    @Override
    public boolean saveData(ZhgdAssessmentVo zhgdAssessmentVo) {
        boolean flag = true;
        ZhgdAssessmentServiceImpl.log.info("ZhgdParticipatingUnits Modular Add Start");
        try {
            final ZhgdAssessment target = new ZhgdAssessment();
            BeanUtils.copyProperties(zhgdAssessmentVo, target);
            target.setVersion(SystemConstant.INTEGER_ONE);
            final int i = zhgdAssessmentMapper.insert(target);
            for (String fileName : zhgdAssessmentVo.getFileNames()) {
                ZhgdFile zhgdFile = new ZhgdFile();
                zhgdFile.setParentId(target.getId());
                zhgdFile.setForm(1);
                zhgdFile.setDeptId(target.getDeptId());
                zhgdFile.setFileName(fileName);
                zhgdFile.setFilePath(this.uploadFileProperties.getAssessment());
                zhgdFile.setVersion(SystemConstant.INTEGER_ONE);
                int j = zhgdFileMapper.insert(zhgdFile);
            }
            ZhgdAssessmentServiceImpl.log.info("ZhgdParticipatingUnits Modular Add End");
            return flag;
        } catch (Exception e) {
            ZhgdAssessmentServiceImpl.log.info("ZhgdParticipatingUnits Modular Add Error：" + e.getMessage());
            flag=false;
            return flag;
        }
    }

    @Override
    public boolean updateData(ZhgdAssessmentVo zhgdAssessmentVo) {
        boolean flag = true;
        ZhgdAssessmentServiceImpl.log.info("ZhgdParticipatingUnits Modular Add Start");
        try {
            final ZhgdAssessment target = new ZhgdAssessment();
            BeanUtils.copyProperties(zhgdAssessmentVo, target);
            final int i = zhgdAssessmentMapper.updateById(target);
            LambdaQueryWrapper<ZhgdFile> queryWrapper = Wrappers.lambdaQuery();
            // 根据字段查询
            if (target.getId() != null) {
                queryWrapper.eq(ZhgdFile::getParentId, target.getId());
            }
            if (target.getDeptId() != null) {
                queryWrapper.eq(ZhgdFile::getDeptId, target.getDeptId());
            }
            queryWrapper.eq(ZhgdFile::getForm, 1);
           /* List<ZhgdFile> zhgdFileList = zhgdFileMapper.selectList(queryWrapper);
            zhgdFileList.forEach(item -> {
                zhgdFileMapper.deleteById(item.getId());
            });*/
            zhgdFileMapper.delete(queryWrapper);
            for (String fileName : zhgdAssessmentVo.getFileNames()) {
                ZhgdFile zhgdFile = new ZhgdFile();
                zhgdFile.setParentId(target.getId());
                zhgdFile.setForm(1);
                zhgdFile.setDeptId(target.getDeptId());
                zhgdFile.setFileName(fileName);
                zhgdFile.setFilePath(this.uploadFileProperties.getAssessment());
                zhgdFile.setVersion(SystemConstant.INTEGER_ONE);
                int j = zhgdFileMapper.insert(zhgdFile);
            }
            ZhgdAssessmentServiceImpl.log.info("ZhgdParticipatingUnits Modular Add End");
            return flag;
        } catch (Exception e) {
            ZhgdAssessmentServiceImpl.log.info("ZhgdParticipatingUnits Modular Add Error：" + e.getMessage());
            flag=false;
            return flag;
        }
    }

    @Override
    public Map<String, Object> uploadFile(MultipartFile uploadFile) throws IOException {

        String filePath = this.uploadFileProperties.getAssessment();
        final String fileName = FileUploadUtils.upload(filePath, uploadFile);
        Map<String, Object> map = new HashMap<>();

        map.put("fileName", fileName);

        return map;
    }


}
