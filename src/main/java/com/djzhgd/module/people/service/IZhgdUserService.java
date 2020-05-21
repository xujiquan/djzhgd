package com.djzhgd.module.people.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.djzhgd.module.people.domain.ZhgdUser;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;


public interface IZhgdUserService extends IService<ZhgdUser> {
    Map<String, Object> uploadFile(MultipartFile uploadFile) throws IOException;
}
