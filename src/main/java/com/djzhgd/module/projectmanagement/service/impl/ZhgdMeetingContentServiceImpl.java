package com.djzhgd.module.projectmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djzhgd.framework.config.UploadFileProperties;
import com.djzhgd.framework.web.page.PageDomain;
import com.djzhgd.framework.web.page.TableSupport;
import com.djzhgd.module.projectmanagement.domain.ZhgdMeetingContent;
import com.djzhgd.module.projectmanagement.domain.Zhgdmeetingagenda;
import com.djzhgd.module.projectmanagement.mapper.ZhgdMeetingContentMapper;
import com.djzhgd.module.projectmanagement.mapper.ZhgdmeetingagendaMapper;
import com.djzhgd.module.projectmanagement.service.IZhgdMeetingContentService;
import com.djzhgd.module.projectmanagement.vo.ZhgdMeetingContentVo;
import com.djzhgd.project.system.domain.SysDept;
import com.djzhgd.project.system.domain.SysUser;
import com.djzhgd.project.system.domain.ZhgdFile;
import com.djzhgd.project.system.mapper.ZhgdFileMapper;
import com.djzhgd.project.system.service.ISysDeptService;
import com.djzhgd.project.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ZhgdMeetingContentServiceImpl extends ServiceImpl<ZhgdMeetingContentMapper, ZhgdMeetingContent> implements IZhgdMeetingContentService {
    @Autowired
    private ZhgdMeetingContentMapper zhgdmeetingcontentmapper;
    @Autowired
    private ZhgdmeetingagendaMapper zhgdmeetingagendamapper;
    @Autowired
    private IZhgdMeetingContentService izhgdmeetingcontentservice;

    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysDeptService deptservice;
    @Autowired
    private UploadFileProperties uploadFileProperties;
    @Autowired
    private ZhgdFileMapper zhgdFileMapper;

    public IPage<ZhgdMeetingContent> list(ZhgdMeetingContent Info) {

        LambdaQueryWrapper<ZhgdMeetingContent> queryWrapper = new LambdaQueryWrapper<>();
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        Page<ZhgdMeetingContent> page = new Page<ZhgdMeetingContent>(pageNum, pageSize);
        IPage<ZhgdMeetingContent> Page = zhgdmeetingcontentmapper.selectPage(page, queryWrapper);
        return Page;
    }

    public boolean savecontentandagenda(ZhgdMeetingContentVo zhgdmeetingcontent, List<Zhgdmeetingagenda> zhgdMeetingAgendas) {
        ZhgdMeetingContentVo zhgdmeetingcontents =zhgdmeetingcontent;
        zhgdmeetingcontents.setZhgdMeetingAgendas(null);
        zhgdmeetingcontents.setZhgdMeetingSigns(null);
        zhgdmeetingcontents.setZhgdmeetingcontent(null);
        Integer Id = zhgdmeetingcontentmapper.insert(zhgdmeetingcontents);
        int i = 0;
        for (Zhgdmeetingagenda zhgdMeetingAgenda : zhgdMeetingAgendas) {
            SysUser SysUser =userService.selectUserById(Long.valueOf(zhgdMeetingAgenda.getLeaderId()));
            if(SysUser!=null){
                SysDept SysDept = deptservice.selectDeptById(SysUser.getDeptId());
                zhgdMeetingAgenda.setParentId(zhgdmeetingcontents.getId().toString());
                Integer J =i;
                zhgdMeetingAgenda.setNode(J+"");
                zhgdMeetingAgenda.setZhgdDeptName(SysDept.getDeptName());
                zhgdMeetingAgenda.setZhgdDeptCode(SysDept.getDeptCode());
                zhgdMeetingAgenda.setZhgdDeptId(SysUser.getDeptId().toString());
                zhgdmeetingagendamapper.insert(zhgdMeetingAgenda);
                i++;
            }
        }
        return true;
    }

    @Override
    public ZhgdMeetingContentVo contentandagenda(Long id) {
        ZhgdMeetingContentVo zhgdmeetingcontentvo=new ZhgdMeetingContentVo();
        ZhgdMeetingContent zhgdmeetingcontent = zhgdmeetingcontentmapper.selectById(id);
        LambdaQueryWrapper<Zhgdmeetingagenda> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Zhgdmeetingagenda::getParentId, id);
        List<Zhgdmeetingagenda>  zhgdmeetingagenda = zhgdmeetingagendamapper.selectList(queryWrapper);
        zhgdmeetingcontentvo.setZhgdMeetingAgendas(zhgdmeetingagenda);
        zhgdmeetingcontentvo.setZhgdmeetingcontent(zhgdmeetingcontent);
        return zhgdmeetingcontentvo;
    }
    @Override
    public boolean removeandothers(Integer id) {
        boolean flg=false;
          ZhgdMeetingContent  zhgdmeetingcontent=  zhgdmeetingcontentmapper.selectById(id);
          LambdaQueryWrapper<ZhgdMeetingContent> qWrapper = new LambdaQueryWrapper<>();
          qWrapper.eq(ZhgdMeetingContent::getId, id);
          zhgdmeetingcontentmapper.delete(qWrapper);
        if(zhgdmeetingcontent!=null){
            LambdaQueryWrapper<Zhgdmeetingagenda> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Zhgdmeetingagenda::getParentId, id);
            queryWrapper.eq(Zhgdmeetingagenda::getDeptId, zhgdmeetingcontent.getDeptId());
            zhgdmeetingagendamapper.delete(queryWrapper);
            LambdaQueryWrapper<ZhgdFile> filewrapper = new LambdaQueryWrapper<>();
            filewrapper.eq(ZhgdFile::getDeptId, zhgdmeetingcontent.getDeptId());
            filewrapper.eq(ZhgdFile::getForm, "1");
            filewrapper.eq(ZhgdFile::getParentId, id);
            zhgdFileMapper.delete(filewrapper);
        }
        flg=true;
        return flg;
    }

    @Override
    public Integer synchronousUpdate(Integer meetingId, Integer currentNodeId, Integer UserId) {
        ZhgdMeetingContent zhgdMeetingContent = zhgdmeetingcontentmapper.selectById(meetingId);
        zhgdMeetingContent.setNodeNum(currentNodeId.toString());
        zhgdMeetingContent.setAssignController(UserId.toString());
        zhgdmeetingcontentmapper.updateById(zhgdMeetingContent);
        return meetingId;
    }

}
