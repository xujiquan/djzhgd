package com.djzhgd.module.teamManagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djzhgd.common.utils.file.FileUploadUtils;
import com.djzhgd.framework.web.page.PageDomain;
import com.djzhgd.framework.web.page.TableSupport;
import com.djzhgd.module.Jurisdiction.domain.SysUserT;
import com.djzhgd.module.Jurisdiction.mapper.SysUserTMapper;
import com.djzhgd.module.constants.SystemConstant;
import com.djzhgd.module.result.Result;
import com.djzhgd.module.teamManagement.domain.ZhgdTeamEducation;
import com.djzhgd.module.teamManagement.mapper.ZhgdTeamEducationMapper;
import com.djzhgd.module.teamManagement.service.ZhgdTeamEducationService;
import com.djzhgd.module.teamManagement.vo.ZhgdTeamEducationVo;
import com.djzhgd.module.utils.BeanHelper;
import com.djzhgd.module.utils.RequestHeaderUtils;
import com.djzhgd.project.system.domain.ZhgdFile;
import com.djzhgd.project.system.mapper.ZhgdFileMapper;
import com.djzhgd.project.utils.DateUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: ZhgdTeamEducationServiceImpl
 * @Author: zhangheng
 * @DATE: 2020/5/20 17:05
 * @Version 1.0
 **/
@Service
public class ZhgdTeamEducationServiceImpl extends ServiceImpl<ZhgdTeamEducationMapper, ZhgdTeamEducation> implements ZhgdTeamEducationService {

    @Value("${djzhgd.uploadFile.announcement}")
    private String announcement;
    @Autowired
    private ZhgdTeamEducationMapper zhgdTeamEducationMapper;
    @Autowired
    private SysUserTMapper sysUserTMapper;

    /**
     * 班前教育查询集合
     * @param zhgdTeamEducationVo
     * @return
     */
    @Transactional
    public IPage<ZhgdTeamEducation> list(ZhgdTeamEducationVo zhgdTeamEducationVo) throws ParseException {
        // 创建查询条件
        LambdaQueryWrapper<ZhgdTeamEducation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ZhgdTeamEducation::getDisabled, SystemConstant.INTEGER_ZERO);
        if(zhgdTeamEducationVo != null){
            if(StringUtils.isNotBlank(zhgdTeamEducationVo.getMeetName())){
                queryWrapper.eq(ZhgdTeamEducation::getMeetName, zhgdTeamEducationVo.getMeetName());
            }
            if(StringUtils.isNotBlank(zhgdTeamEducationVo.getStartTime())){
                queryWrapper.ge(ZhgdTeamEducation::getMeetTime, DateUtil.getYYYYMMDDHHMMSS(zhgdTeamEducationVo.getStartTime()));
            }
            if(StringUtils.isNotBlank(zhgdTeamEducationVo.getEndTime())){
                queryWrapper.le(ZhgdTeamEducation::getMeetTime, DateUtil.getYYYYMMDDHHMMSS(zhgdTeamEducationVo.getEndTime()));
            }
            if(StringUtils.isNotBlank(zhgdTeamEducationVo.getTeamCode())){
                queryWrapper.eq(ZhgdTeamEducation::getTeamCode, zhgdTeamEducationVo.getTeamCode());
            }
            if(zhgdTeamEducationVo.getZhgdUserId() != null){
                queryWrapper.eq(ZhgdTeamEducation::getZhgdUserId, zhgdTeamEducationVo.getZhgdUserId());
            }
        }
        queryWrapper.orderByDesc(ZhgdTeamEducation::getId);

        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();

        Page<ZhgdTeamEducation> page = new Page<>(pageNum, pageSize);
        IPage<ZhgdTeamEducation> Page = zhgdTeamEducationMapper.selectPage(page, queryWrapper);

        return Page;
    }

    /**
     * 根据班前教育ID查询详情
     * @param zhgdTeamEducation
     * @return
     */
    @Transactional
    public Result getZhgdTeamEducationDetail(ZhgdTeamEducation zhgdTeamEducation) {
        // 获取前端返回的班前教育ID
        Long id = 0L;
        if(zhgdTeamEducation != null && zhgdTeamEducation.getId() != null){
            id = zhgdTeamEducation.getId();
        }
        // 通过班前教育ID查询明细
        zhgdTeamEducation = zhgdTeamEducationMapper.selectById(id);
        ZhgdTeamEducationVo zhgdTeamEducationVo = null;
        if(zhgdTeamEducation != null){
            zhgdTeamEducationVo =new ZhgdTeamEducationVo();
            BeanUtils.copyProperties(zhgdTeamEducation, zhgdTeamEducationVo);
            if(zhgdTeamEducation.getMeetTime() != null){
                zhgdTeamEducationVo.setMeetTimeVo(DateUtil.getYYYYMMDDHHMMSS(zhgdTeamEducation.getMeetTime()));
            }
            if(zhgdTeamEducation.getCreateDatetime() != null){
                zhgdTeamEducationVo.setCreateDatetimeVo(DateUtil.getYYYYMMDDHHMMSS(zhgdTeamEducation.getCreateDatetime()));
            }
            if(zhgdTeamEducation.getUpdateDatetime() != null){
                zhgdTeamEducationVo.setUpdateDatetimeVo(DateUtil.getYYYYMMDDHHMMSS(zhgdTeamEducation.getUpdateDatetime()));
            }
            BeanHelper.nullToEmpty(zhgdTeamEducationVo);
        }
        return new Result().success(zhgdTeamEducationVo);
    }

    /**
     * 新增一条班前教育数据
     * @param zhgdTeamEducation
     * @return
     */
    @Transactional
    public Result addZhgdTeamEducation(ZhgdTeamEducation zhgdTeamEducation, HttpServletRequest request) {
        Long userId = RequestHeaderUtils.getUserId(request);
        SysUserT sysUserT = sysUserTMapper.selectById(userId);
        Long deptId = RequestHeaderUtils.getDeptId(request);
        if(zhgdTeamEducation != null){
            zhgdTeamEducation.setDeptId(deptId);
            zhgdTeamEducation.setCreateUserid(userId);
            zhgdTeamEducation.setCreateUsername(sysUserT.getUserName());
            zhgdTeamEducation.setUpdateUserid(userId);
            zhgdTeamEducation.setUpdateUsername(sysUserT.getUserName());
            zhgdTeamEducationMapper.insert(zhgdTeamEducation);
        }
        return new Result().success(zhgdTeamEducation);
    }

    /**
     * 修改一条班前教育信息
     * @param zhgdTeamEducation
     * @return
     */
    @Transactional
    public Result updateZhgdTeamEducation(ZhgdTeamEducation zhgdTeamEducation, HttpServletRequest request) {
        Long userId = RequestHeaderUtils.getUserId(request);
        SysUserT sysUserT = sysUserTMapper.selectById(userId);
        if(zhgdTeamEducation != null){
            zhgdTeamEducation.setUpdateDatetime(new Date());
            zhgdTeamEducation.setUpdateUserid(userId);
            zhgdTeamEducation.setUpdateUsername(sysUserT.getUserName());
            zhgdTeamEducationMapper.updateById(zhgdTeamEducation);
        }
        return new Result().success(zhgdTeamEducation);
    }

    /**
     * 根据ID批量删除班前教育数据
     * @param idList
     * @return
     */
    @Transactional
    public Result deleteById(List<Long> idList) {
        // 循环前端选取的待删除数据ID
        if(CollectionUtils.isNotEmpty(idList)){
            zhgdTeamEducationMapper.deleteBatchIds(idList);
        }
        return new Result().success();
    }

}
