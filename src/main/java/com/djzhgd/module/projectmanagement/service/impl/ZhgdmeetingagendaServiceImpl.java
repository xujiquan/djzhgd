package com.djzhgd.module.projectmanagement.service.impl;

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
import com.djzhgd.module.demo.domain.ZxMaterialInfo;
import com.djzhgd.module.projectmanagement.domain.Zhgdmeetingagenda;
import com.djzhgd.module.projectmanagement.mapper.ZhgdmeetingagendaMapper;
import com.djzhgd.module.projectmanagement.service.ZhgdmeetingagendaService;
import com.djzhgd.module.projectmanagement.vo.ZhgdmeetingagendaVo;
import com.djzhgd.module.utils.RequestHeaderUtils;
import com.djzhgd.project.system.domain.ZhgdFile;
import com.djzhgd.project.system.mapper.ZhgdFileMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
public class ZhgdmeetingagendaServiceImpl extends ServiceImpl<ZhgdmeetingagendaMapper, Zhgdmeetingagenda> implements ZhgdmeetingagendaService {

    @Autowired
    private ZhgdmeetingagendaMapper zhgdmeetingagendamapper;
    @Autowired
    private UploadFileProperties uploadFileProperties;
    @Autowired
    private ZhgdFileMapper zhgdFileMapper;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;


    @Override
    public IPage<Zhgdmeetingagenda> list(ZxMaterialInfo zxMaterialInfo) {
        // 查询条件封装
        LambdaQueryWrapper<Zhgdmeetingagenda> queryWrapper = Wrappers.lambdaQuery();
       // 根据id倒序
        queryWrapper.orderByDesc(Zhgdmeetingagenda::getId);

        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        Page<Zhgdmeetingagenda> page = new Page<Zhgdmeetingagenda>(pageNum, pageSize);
        IPage<Zhgdmeetingagenda> Page = zhgdmeetingagendamapper.selectPage(page, queryWrapper);

        return Page;
    }
    @Override
    public Map<String, Object> uploadFile(MultipartFile uploadFile) throws IOException {
        Map<String, Object> map = new HashMap<>();
        //获取文件路径 没改模块的路径都不一样
        String filePath = this.uploadFileProperties.getZhgdmeeting();
        //上传到服务器并返回完整路径(
        final String path = FileUploadUtils.uploadFile(filePath, uploadFile);
        //从请求头里取得deptId
        Long deptId = RequestHeaderUtils.getDeptId(request);
        if (deptId!=null){
            //将文件存到数据库先不给父类parentId赋值
            ZhgdFile zhgdFile = new ZhgdFile();
            //模块分类标识 从前端传来
            zhgdFile.setForm(1);
            zhgdFile.setDeptId(deptId.intValue());
            //保存原始文件名称
            zhgdFile.setFileName(uploadFile.getOriginalFilename());
            //保存完整路径
            zhgdFile.setFilePath(path);
            //模块子级标识
            zhgdFile.setFileType(1);
            //版本号
            zhgdFile.setVersion(SystemConstant.INTEGER_ONE);
            int j = zhgdFileMapper.insert(zhgdFile);
            map.put("fileName",uploadFile.getOriginalFilename());
            map.put("id",zhgdFile.getId());
            map.put("filepath",path);
            map.put("zhgdfile",zhgdFile);
        }else{
        }
        return map;
    }

    @Override
    public boolean updateandfile(ZhgdmeetingagendaVo zhgdmeetingagenda) {
        Zhgdmeetingagenda meetingagenda = zhgdmeetingagendamapper.selectById(zhgdmeetingagenda.getId());
        List<Integer> lists=zhgdmeetingagenda.getMeetingfileid();
        lists.forEach((id) -> {
            ZhgdFile zhgdFile = new ZhgdFile();
            zhgdFile.setId(id);
            zhgdFile.setParentId(zhgdmeetingagenda.getId().intValue());
            zhgdFileMapper.updateById(zhgdFile);
        });
        BeanUtils.copyProperties(zhgdmeetingagenda, meetingagenda);
          zhgdmeetingagenda.setFilePath("1");
          zhgdmeetingagendamapper.updateById(zhgdmeetingagenda);
        return true;
    }
    @Override
    public void downloadFile(Integer id) {
        ZhgdFile zhgdFile= zhgdFileMapper.selectById(id);
        if(zhgdFile!=null){
            try {
                FileUploadUtils.downloadFile(zhgdFile.getFilePath(),zhgdFile.getFileName(),response) ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
