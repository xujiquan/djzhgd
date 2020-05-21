package com.djzhgd.module.people.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djzhgd.common.utils.file.FileUploadUtils;
import com.djzhgd.framework.config.UploadFileProperties;
import com.djzhgd.module.people.domain.ZhgdUser;
import com.djzhgd.module.people.mapper.ZhgdUserMapper;
import com.djzhgd.module.people.service.IZhgdUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@EnableConfigurationProperties(UploadFileProperties.class) // 启用资源配置读取类
public class ZhgdUserServiceImpl extends ServiceImpl<ZhgdUserMapper, ZhgdUser> implements IZhgdUserService {
    @Autowired
    private UploadFileProperties uploadFileProperties;

    @Override
    public Map<String, Object> uploadFile(MultipartFile uploadFile) throws IOException {
        String filePath = this.uploadFileProperties.getZhgduser();
        final String fileName = FileUploadUtils.upload(filePath, uploadFile);
        Map<String, Object> map = new HashMap<>();

        map.put("fileName",fileName);

        return map;
    }
}
