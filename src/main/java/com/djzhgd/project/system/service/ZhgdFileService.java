package com.djzhgd.project.system.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: ZhgdFileService
 * @Author: zhangheng
 * @DATE: 2020/5/21 16:08
 * @Version 1.0
 **/
public interface ZhgdFileService {

    // 上传文件
    Map<String, Object> uploadFile(MultipartFile uploadFile, Long deptId, Integer form, Integer fileType, String path) throws IOException;

    // 下载文件
    void download(Integer id, HttpServletResponse response) throws Exception ;

}
