package com.djzhgd.module.projectmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djzhgd.framework.web.page.PageDomain;
import com.djzhgd.framework.web.page.TableSupport;
import com.djzhgd.module.projectmanagement.domain.ZhgdAnnouncement;
import com.djzhgd.module.projectmanagement.mapper.ZhgdAnnouncementMapper;
import com.djzhgd.module.projectmanagement.service.IZhgdAnnouncementService;
import com.djzhgd.module.projectmanagement.vo.ZhgdAnnouncementVo;
import com.djzhgd.project.system.domain.ZhgdFile;
import com.djzhgd.project.system.mapper.ZhgdFileMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PROJECT_NAME: dj_zhgd 东交智慧品控项目
 * @ClassName: ZhgdAnnouncementServiceImpl
 * @Author: xjq
 * @DATE: 2020/5/18 10:42
 * @Version 1.0
 **/
@Slf4j
@Service
public class ZhgdAnnouncementServiceImpl extends ServiceImpl<ZhgdAnnouncementMapper, ZhgdAnnouncement> implements IZhgdAnnouncementService {

    @Autowired
    private ZhgdAnnouncementMapper announcementMapper;
    @Autowired
    private ZhgdFileMapper zhgdFileMapper;

    @Override
    public List<ZhgdAnnouncementVo> list(ZhgdAnnouncementVo announcementVo) {

        // 查询条件封装
        LambdaQueryWrapper<ZhgdAnnouncement> queryWrapper = Wrappers.lambdaQuery();
        if (announcementVo != null) {
            // 根据字段查询
            if (announcementVo.getDeptId() != null) {
                queryWrapper.eq(ZhgdAnnouncement::getDeptId, announcementVo.getDeptId());
            }
            if (announcementVo.getTenantId() != null) {
                queryWrapper.eq(ZhgdAnnouncement::getTenantId, announcementVo.getTenantId());
            }
            if (announcementVo.getTenantId() != null) {
                queryWrapper.eq(ZhgdAnnouncement::getTenantId, announcementVo.getBeginDate());
            }
            if (StringUtils.isNotBlank(announcementVo.getBeginDate())) {
                queryWrapper.apply("ann_date>=TO_DATE({0}, 'yyyy-MM-dd')", announcementVo.getBeginDate());
            }
            if (StringUtils.isNotBlank(announcementVo.getEndDate())) {
                queryWrapper.apply("ann_date<=TO_DATE({0}, 'yyyy-MM-dd')", announcementVo.getEndDate());
            }
        }

        // 根据AnnDate倒序
        queryWrapper.orderByDesc(ZhgdAnnouncement::getAnnDate);
        // 设置当前页和页容量
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        Page<ZhgdAnnouncement> page = new Page<>(pageNum, pageSize);
        IPage<ZhgdAnnouncement> announcementIPage = announcementMapper.selectPage(page, queryWrapper);
        // 封装Vo对象
        List<ZhgdAnnouncement> announcements = announcementIPage.getRecords();
        List<ZhgdAnnouncementVo> announcementVos = new ArrayList<>();
        announcements.forEach(item -> {
            final ZhgdAnnouncementVo target = new ZhgdAnnouncementVo();
            BeanUtils.copyProperties(item, target);
            announcementVos.add(target);
        });

        return announcementVos;
    }


    @Override
    public ZhgdAnnouncementVo detail(Integer id) {
        ZhgdAnnouncement announcement = announcementMapper.selectById(id);

        ZhgdAnnouncementVo target = new ZhgdAnnouncementVo();
        BeanUtils.copyProperties(announcement, target);

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

        List<Map<String, Object>> uploadAttachment = new ArrayList<>();//上传附件
        List<Map<String, Object>> uploadImage = new ArrayList<>();//上传图片

        zhgdFileList.forEach(item -> {
            Integer fileType = item.getFileType();
            Map<String, Object> map = new HashMap<>();
            map.put("id", item.getId());
            map.put("fileName", item.getFileName());
            if (fileType == 1) {
                uploadAttachment.add(map);
            } else if (fileType == 2) {
                uploadImage.add(map);
            }
        });
        target.setUploadAttachment(uploadAttachment);
        target.setUploadImage(uploadImage);

        return target;
    }

    @Override
    @Transactional
    public int delete(Integer id) {
        int i = announcementMapper.deleteById(id);
        //解除与主表关联，设置zhgdFile的disabled=1
        unbindWithPrimaryTable(id, 100);

        return i;
    }

    private void unbindWithPrimaryTable(Integer id, Integer from) {
        UpdateWrapper<ZhgdFile> fileUpdateWrapper = new UpdateWrapper<>();
        ZhgdFile file = new ZhgdFile();
        file.setDisabled(1);
        fileUpdateWrapper.eq("parent_id", id);
        fileUpdateWrapper.eq("disabled", 0);
        fileUpdateWrapper.eq("form", from);
        zhgdFileMapper.update(file, fileUpdateWrapper);
    }

    @Override
    @Transactional
    public int saveData(ZhgdAnnouncementVo zhgdAnnouncementVo) {

        ZhgdAnnouncement target = new ZhgdAnnouncement();
        BeanUtils.copyProperties(zhgdAnnouncementVo, target);
        //target.setVersion(SystemConstant.INTEGER_ONE);
        int i = announcementMapper.insert(target);
        //接收表单中fileId数组，关联主表数据
        bindWithPrimaryTable(zhgdAnnouncementVo);

        return i;
    }

    @Override
    @Transactional
    public int updateData(ZhgdAnnouncementVo zhgdAnnouncementVo) {

        ZhgdAnnouncement target = new ZhgdAnnouncement();
        BeanUtils.copyProperties(zhgdAnnouncementVo, target);
        int i = announcementMapper.updateById(target);

        //解除与主表关联，设置zhgdFile的disabled=1
        unbindWithPrimaryTable(zhgdAnnouncementVo.getId(), 100);
        //接收表单中fileId数组，关联主表数据
        bindWithPrimaryTable(zhgdAnnouncementVo);

        return i;

    }

    private void bindWithPrimaryTable(ZhgdAnnouncementVo zhgdAnnouncementVo) {
        List<Integer> fileIds = zhgdAnnouncementVo.getFileId();
        Integer fileType = zhgdAnnouncementVo.getFileType();
        fileIds.forEach((id) -> {
            ZhgdFile zhgdFile = new ZhgdFile();
            zhgdFile.setId(id);
            zhgdFile.setParentId(zhgdAnnouncementVo.getId());
            zhgdFile.setFileType(fileType);
            zhgdFileMapper.updateById(zhgdFile);
        });
    }



}
