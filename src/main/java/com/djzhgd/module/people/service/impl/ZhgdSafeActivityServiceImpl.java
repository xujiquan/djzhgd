package com.djzhgd.module.people.service.impl;

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
import com.djzhgd.module.people.domain.ZhgdLinkSafeUser;
import com.djzhgd.module.people.domain.ZhgdSafeActivity;
import com.djzhgd.module.people.domain.vo.ZhgdSafeActivityVo;
import com.djzhgd.module.people.mapper.ZhgdLinkSafeUserMapper;
import com.djzhgd.module.people.mapper.ZhgdSafeActivityMapper;
import com.djzhgd.module.people.service.IZhgdSafeActivityService;
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
@EnableConfigurationProperties(UploadFileProperties.class) // 启用资源配置读取类
public class ZhgdSafeActivityServiceImpl extends ServiceImpl<ZhgdSafeActivityMapper, ZhgdSafeActivity> implements IZhgdSafeActivityService {

    @Autowired
    private ZhgdSafeActivityMapper zhgdSafeActivityMapper;

    @Autowired
    private ZhgdLinkSafeUserMapper zhgdLinkSafeUserMapper;

    @Autowired
    private ZhgdFileMapper zhgdFileMapper;

    @Autowired
    private UploadFileProperties uploadFileProperties;


    @Override
    public IPage<ZhgdSafeActivity> list(ZhgdSafeActivityVo zhgdSafeActivityVo){

        LambdaQueryWrapper<ZhgdSafeActivity> queryWrapper = new LambdaQueryWrapper<>();
        try {
            if (zhgdSafeActivityVo.getDeptId() != null) {
                queryWrapper.eq(ZhgdSafeActivity::getDeptId, zhgdSafeActivityVo.getDeptId());
            }
            if (zhgdSafeActivityVo.getZhgdDeptId() != null) {
                queryWrapper.eq(ZhgdSafeActivity::getZhgdDeptId, zhgdSafeActivityVo.getZhgdDeptId());
            }
            if (zhgdSafeActivityVo.getName() != null) {
                queryWrapper.like(ZhgdSafeActivity::getName, zhgdSafeActivityVo.getName());
            }
            if (zhgdSafeActivityVo.getType() != null) {
                queryWrapper.eq(ZhgdSafeActivity::getType, zhgdSafeActivityVo.getType());
            }
            if (zhgdSafeActivityVo.getTechnicalType() != null) {
                queryWrapper.eq(ZhgdSafeActivity::getTechnicalType, zhgdSafeActivityVo.getTechnicalType());
            }
            if (org.apache.commons.lang3.StringUtils.isNotBlank(zhgdSafeActivityVo.getBeginDate())) {
                queryWrapper.ge(ZhgdSafeActivity::getStartTime, DateUtil.getYYYYMMDDHHMMSS(zhgdSafeActivityVo.getBeginDate()));
            }
            if (org.apache.commons.lang3.StringUtils.isNotBlank(zhgdSafeActivityVo.getEndDate())) {
                queryWrapper.le(ZhgdSafeActivity::getStartTime, DateUtil.getYYYYMMDDHHMMSS(zhgdSafeActivityVo.getEndDate()));
            }
        }catch (ParseException e){

        }
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        Page<ZhgdSafeActivity> page = new Page<ZhgdSafeActivity>(pageNum, pageSize);
        IPage<ZhgdSafeActivity> Page = zhgdSafeActivityMapper.selectPage(page, queryWrapper);
        return Page;
    }


    @Override
    public ZhgdSafeActivityVo detail(Integer id) {
        final ZhgdSafeActivity zhgdSafeActivity = zhgdSafeActivityMapper.selectById(id);
        final ZhgdSafeActivityVo target = new ZhgdSafeActivityVo();
        BeanUtils.copyProperties(zhgdSafeActivity, target);
        LambdaQueryWrapper<ZhgdFile> queryWrapper = Wrappers.lambdaQuery();
        // 根据字段查询
        if (target.getId() != null) {
            queryWrapper.eq(ZhgdFile::getParentId, target.getId());
        }
        if (target.getDeptId() != null) {
            queryWrapper.eq(ZhgdFile::getDeptId, target.getDeptId());
        }
        queryWrapper.eq(ZhgdFile::getForm, 3);
        List<ZhgdFile> zhgdFileList = zhgdFileMapper.selectList(queryWrapper);
        List<String> fileNames = new ArrayList<>();
        zhgdFileList.forEach(item -> {
            fileNames.add(item.getFileName());
        });
        target.setFileNames(fileNames);

        LambdaQueryWrapper<ZhgdLinkSafeUser> zhgdLinkSafeUserWrapper = Wrappers.lambdaQuery();
        // 根据字段查询
        if (target.getId() != null) {
            zhgdLinkSafeUserWrapper.eq(ZhgdLinkSafeUser::getSafeActivityId, target.getId());
        }
        if (target.getDeptId() != null) {
            zhgdLinkSafeUserWrapper.eq(ZhgdLinkSafeUser::getDeptId, target.getDeptId());
        }
        List<ZhgdLinkSafeUser> zhgdLinkSafeUserList = zhgdLinkSafeUserMapper.selectList(zhgdLinkSafeUserWrapper);
        List<String> userIds = new ArrayList<>();
        zhgdLinkSafeUserList.forEach(item -> {
            userIds.add(item.getUserId().toString());
        });
        String userId = null;
        if(userIds!=null && !userIds.isEmpty()){
            userId = String.join(",", userIds);
        }
        target.setUserid(userId);
        target.setFileNames(fileNames);
        target.setFilePath(this.uploadFileProperties.getAssessment());
        return target;
    }

    @Override
    public int delete(Integer id) {
        final int i = zhgdSafeActivityMapper.deleteById(id);
        return i;
    }

    @Override
    public boolean saveData(ZhgdSafeActivityVo zhgdSafeActivityVo) {
        boolean flag = true;
        ZhgdSafeActivityServiceImpl.log.info("ZhgdParticipatingUnits Modular Add Start");
        try {
            final ZhgdSafeActivity target = new ZhgdSafeActivity();
            BeanUtils.copyProperties(zhgdSafeActivityVo, target);
            target.setVersion(SystemConstant.INTEGER_ONE);
            final int i = zhgdSafeActivityMapper.insert(target);
            //保存培训附件
            for (String fileName : zhgdSafeActivityVo.getFileNames()) {
                ZhgdFile zhgdFile = new ZhgdFile();
                zhgdFile.setParentId(target.getId());
                zhgdFile.setForm(3);
                zhgdFile.setDeptId(target.getDeptId());
                zhgdFile.setFileName(fileName);
                zhgdFile.setFilePath(this.uploadFileProperties.getSafeActivity());
                zhgdFile.setVersion(SystemConstant.INTEGER_ONE);
                int j = zhgdFileMapper.insert(zhgdFile);
            }
            //保存培训参与人员
            String userid=zhgdSafeActivityVo.getUserid();
            String[] userids = userid.split(",");
            for(String userId : userids){
                ZhgdLinkSafeUser zhgdLinkSafeUser = new ZhgdLinkSafeUser();
                zhgdLinkSafeUser.setSafeActivityId(target.getId());
                zhgdLinkSafeUser.setUserId(Integer.parseInt(userId));
                zhgdLinkSafeUser.setDeptId(target.getDeptId());
                zhgdLinkSafeUserMapper.insert(zhgdLinkSafeUser);
            }
            ZhgdSafeActivityServiceImpl.log.info("ZhgdParticipatingUnits Modular Add End");
            return flag;
        } catch (Exception e) {
            ZhgdSafeActivityServiceImpl.log.info("ZhgdParticipatingUnits Modular Add Error：" + e.getMessage());
            flag=false;
            return flag;
        }
    }

    @Override
    public boolean updateData(ZhgdSafeActivityVo zhgdSafeActivityVo) {
        boolean flag = true;
        ZhgdSafeActivityServiceImpl.log.info("ZhgdParticipatingUnits Modular Add Start");
        try {
            final ZhgdSafeActivity target = new ZhgdSafeActivity();
            BeanUtils.copyProperties(zhgdSafeActivityVo, target);
            final int i = zhgdSafeActivityMapper.updateById(target);
            LambdaQueryWrapper<ZhgdFile> queryWrapper = Wrappers.lambdaQuery();
            // 根据字段查询
            if (target.getId() != null) {
                queryWrapper.eq(ZhgdFile::getParentId, target.getId());
            }
            if (target.getDeptId() != null) {
                queryWrapper.eq(ZhgdFile::getDeptId, target.getDeptId());
            }
            queryWrapper.eq(ZhgdFile::getForm, 3);
            //删除旧的培训文件
            zhgdFileMapper.delete(queryWrapper);
            LambdaQueryWrapper<ZhgdLinkSafeUser> zhgdLinkSafeUserWrapper = Wrappers.lambdaQuery();
            // 根据字段查询
            if (target.getId() != null) {
                zhgdLinkSafeUserWrapper.eq(ZhgdLinkSafeUser::getSafeActivityId, target.getId());
            }
            if (target.getDeptId() != null) {
                zhgdLinkSafeUserWrapper.eq(ZhgdLinkSafeUser::getDeptId, target.getDeptId());
            }
            //删除旧的培训参与人员
            zhgdLinkSafeUserMapper.delete(zhgdLinkSafeUserWrapper);
            //保存培训附件
            for (String fileName : zhgdSafeActivityVo.getFileNames()) {
                ZhgdFile zhgdFile = new ZhgdFile();
                zhgdFile.setParentId(target.getId());
                zhgdFile.setForm(3);
                zhgdFile.setDeptId(target.getDeptId());
                zhgdFile.setFileName(fileName);
                zhgdFile.setFilePath(this.uploadFileProperties.getSafeActivity());
                zhgdFile.setVersion(SystemConstant.INTEGER_ONE);
                int j = zhgdFileMapper.insert(zhgdFile);
            }
            //保存培训参与人员
            String userid=zhgdSafeActivityVo.getUserid();
            String[] userids = userid.split(",");
            for(String userId : userids){
                ZhgdLinkSafeUser zhgdLinkSafeUser = new ZhgdLinkSafeUser();
                zhgdLinkSafeUser.setSafeActivityId(target.getId());
                zhgdLinkSafeUser.setUserId(Integer.parseInt(userId));
                zhgdLinkSafeUser.setDeptId(target.getDeptId());
                zhgdLinkSafeUserMapper.insert(zhgdLinkSafeUser);
            }
            ZhgdSafeActivityServiceImpl.log.info("ZhgdParticipatingUnits Modular Add End");
            return flag;
        } catch (Exception e) {
            ZhgdSafeActivityServiceImpl.log.info("ZhgdParticipatingUnits Modular Add Error：" + e.getMessage());
            flag=false;
            return flag;
        }
    }

    @Override
    public Map<String, Object> uploadFile(MultipartFile uploadFile) throws IOException {

        String filePath = this.uploadFileProperties.getSafeActivity();
        final String fileName = FileUploadUtils.upload(filePath, uploadFile);
        Map<String, Object> map = new HashMap<>();

        map.put("fileName", fileName);

        return map;
    }

    @Override
    public List<ZhgdSafeActivityVo> getTrainCount(ZhgdSafeActivityVo zhgdSafeActivityVo) {
        return zhgdSafeActivityMapper.getTrainCount(zhgdSafeActivityVo);
    }

    @Override
    public List<ZhgdSafeActivityVo> trainZBCount(ZhgdSafeActivityVo zhgdSafeActivityVo) {
        return zhgdSafeActivityMapper.trainZBCount(zhgdSafeActivityVo);
    }

}
