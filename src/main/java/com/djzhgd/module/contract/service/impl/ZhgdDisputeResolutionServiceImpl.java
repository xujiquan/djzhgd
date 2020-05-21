package com.djzhgd.module.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djzhgd.framework.web.page.PageDomain;
import com.djzhgd.framework.web.page.TableSupport;
import com.djzhgd.module.contract.domain.ZhgdDisputeResolution;
import com.djzhgd.module.contract.mapper.ZhgdDisputeResolutionMapper;
import com.djzhgd.module.contract.service.IZhgdDisputeResolutionService;
import com.djzhgd.module.projectmanagement.vo.ZhgdDisputeResolutionVo;
import com.djzhgd.project.system.domain.ZhgdFile;
import com.djzhgd.project.system.mapper.ZhgdFileMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PROJECT_NAME: dj_zhgd 东交智慧品控项目
 * @ClassName: DemoServiceImpl
 * @Author: zhangheng
 * @DATE: 2020/5/13 10:42
 * @Version 1.0
 **/
@Slf4j
@Service
public class ZhgdDisputeResolutionServiceImpl extends ServiceImpl<ZhgdDisputeResolutionMapper, ZhgdDisputeResolution> implements IZhgdDisputeResolutionService {

    @Autowired
    private ZhgdDisputeResolutionMapper disputeResolutionMapper;
    @Autowired
    private ZhgdFileMapper zhgdFileMapper;
    @Value("djzhgd.upload-file.disputeResolution")
    private String disputeResolution;

    @Override
    public List<ZhgdDisputeResolutionVo> list(ZhgdDisputeResolutionVo disputeResolutionVo) {

        // 查询条件封装
        LambdaQueryWrapper<ZhgdDisputeResolution> queryWrapper = Wrappers.lambdaQuery();
        if (disputeResolutionVo != null) {
            // 根据字段查询
            if (disputeResolutionVo.getDeptId() != null) {
                queryWrapper.eq(ZhgdDisputeResolution::getDeptId, disputeResolutionVo.getDeptId());
            }
            if (disputeResolutionVo.getTenantId() != null) {
                queryWrapper.eq(ZhgdDisputeResolution::getTenantId, disputeResolutionVo.getTenantId());
            }
            if (disputeResolutionVo.getTenantId() != null) {
                queryWrapper.eq(ZhgdDisputeResolution::getTenantId, disputeResolutionVo.getBeginDate());
            }
            if (StringUtils.isNotBlank(disputeResolutionVo.getBeginDate())) {
                queryWrapper.apply("handle_time>=TO_DATE({0}, 'yyyy-MM-dd')", disputeResolutionVo.getBeginDate());
            }
            if (StringUtils.isNotBlank(disputeResolutionVo.getEndDate())) {
                queryWrapper.apply("handle_time<=TO_DATE({0}, 'yyyy-MM-dd')", disputeResolutionVo.getEndDate());
            }
        }

        // 根据AnnDate倒序
        queryWrapper.orderByDesc(ZhgdDisputeResolution::getHandleTime);
        // 设置当前页和页容量
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        Page<ZhgdDisputeResolution> page = new Page<>(pageNum, pageSize);
        IPage<ZhgdDisputeResolution> disputeResolutionIPage = disputeResolutionMapper.selectPage(page, queryWrapper);
        // 封装Vo对象
        List<ZhgdDisputeResolution> disputeResolutions = disputeResolutionIPage.getRecords();
        List<ZhgdDisputeResolutionVo> disputeResolutionVos = new ArrayList<>();
        disputeResolutions.forEach(item -> {
            final ZhgdDisputeResolutionVo target = new ZhgdDisputeResolutionVo();
            BeanUtils.copyProperties(item, target);
            disputeResolutionVos.add(target);
        });

        return disputeResolutionVos;
    }

public static void main(String[] args){
    String projectPath = System.getProperty("user.dir");
    System.out.println(projectPath);
}

    @Override
    public ZhgdDisputeResolutionVo detail(Integer id) {
        ZhgdDisputeResolution disputeResolution = disputeResolutionMapper.selectById(id);

        ZhgdDisputeResolutionVo target = new ZhgdDisputeResolutionVo();
        BeanUtils.copyProperties(disputeResolution, target);

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
        int i = disputeResolutionMapper.deleteById(id);
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
    public int saveData(ZhgdDisputeResolutionVo zhgdDisputeResolutionVo) {

        ZhgdDisputeResolution target = new ZhgdDisputeResolution();
        BeanUtils.copyProperties(zhgdDisputeResolutionVo, target);
        //target.setVersion(SystemConstant.INTEGER_ONE);
        int i = disputeResolutionMapper.insert(target);
        //接收表单中fileId数组，关联主表数据
        bindWithPrimaryTable(zhgdDisputeResolutionVo);

        return i;
    }

    @Override
    @Transactional
    public int updateData(ZhgdDisputeResolutionVo zhgdDisputeResolutionVo) {

        ZhgdDisputeResolution target = new ZhgdDisputeResolution();
        BeanUtils.copyProperties(zhgdDisputeResolutionVo, target);
        int i = disputeResolutionMapper.updateById(target);

        //解除与主表关联，设置zhgdFile的disabled=1
        unbindWithPrimaryTable(zhgdDisputeResolutionVo.getId(), 100);
        //接收表单中fileId数组，关联主表数据
        bindWithPrimaryTable(zhgdDisputeResolutionVo);

        return i;

    }

    private void bindWithPrimaryTable(ZhgdDisputeResolutionVo zhgdDisputeResolutionVo) {
        List<Integer> fileIds = zhgdDisputeResolutionVo.getFileId();
        Integer fileType = zhgdDisputeResolutionVo.getFileType();
        fileIds.forEach((id) -> {
            ZhgdFile zhgdFile = new ZhgdFile();
            zhgdFile.setId(id);
            zhgdFile.setParentId(zhgdDisputeResolutionVo.getId());
            zhgdFile.setFileType(fileType);
            zhgdFileMapper.updateById(zhgdFile);
        });
    }


}
