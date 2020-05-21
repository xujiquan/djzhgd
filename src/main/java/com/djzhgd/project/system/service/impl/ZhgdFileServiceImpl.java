package com.djzhgd.project.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djzhgd.common.utils.file.FileUploadUtils;
import com.djzhgd.project.system.domain.ZhgdFile;
import com.djzhgd.project.system.mapper.ZhgdFileMapper;
import com.djzhgd.project.system.service.ZhgdFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: ZhgdFileServiceImpl
 * @Author: zhangheng
 * @DATE: 2020/5/21 16:08
 * @Version 1.0
 **/
@Service
public class ZhgdFileServiceImpl extends ServiceImpl<ZhgdFileMapper, ZhgdFile> implements ZhgdFileService {

    @Autowired
    private ZhgdFileMapper zhgdFileMapper;

    /**
     * 附件、图片上传
     * @param uploadFile 上传文件
     * @param form       文件来源
     * @param fileType   文件类型
     * @param path       文件保存路径
     * @throws IOException
     */
    @Transactional
    public Map<String, Object> uploadFile(MultipartFile uploadFile, Long deptId, Integer form, Integer fileType, String path) throws IOException {
        // 获取前端传来的文件名称
        String originalFilename = uploadFile.getOriginalFilename();
        // 上传文件
        String filePath = FileUploadUtils.uploadFile(path, uploadFile);
        // 保存后返回到前端的数据
        Map<String, Object> map = new HashMap<>();
        map.put("originalFilename", originalFilename);
        map.put("filePath", filePath);
        // 将数据保存到 zhgd_file文件表中
        ZhgdFile file = new ZhgdFile();
        if (deptId != null) {
            file.setDeptId(Integer.valueOf(deptId.toString()));
        }
        file.setForm(form);
        file.setFileName(originalFilename);
        file.setFilePath(filePath);
        file.setFileType(fileType);
        file.setVersion(1);
        zhgdFileMapper.insert(file);
        // 返回入库后的id给前端，用来提交整个表单
        map.put("id", file.getId());
        return map;
    }

    /**
     * 下载附件
     * @param id
     * @param response
     */
    @Transactional
    public void download(Integer id, HttpServletResponse response) throws Exception {
        // 获取需要下载的附件ID
        ZhgdFile file = zhgdFileMapper.selectById(id);
        // 下载操作
        FileUploadUtils.downloadFile(file.getFilePath(),file.getFileName(), response);
    }

}
