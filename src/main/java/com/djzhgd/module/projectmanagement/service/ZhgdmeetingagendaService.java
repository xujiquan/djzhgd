package com.djzhgd.module.projectmanagement.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.djzhgd.module.demo.domain.ZxMaterialInfo;
import com.djzhgd.module.projectmanagement.domain.Zhgdmeetingagenda;
import com.djzhgd.module.projectmanagement.vo.ZhgdmeetingagendaVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface ZhgdmeetingagendaService extends IService<Zhgdmeetingagenda> {

    IPage<Zhgdmeetingagenda> list(ZxMaterialInfo zxMaterialInfo);


    Map<String, Object> uploadFile(MultipartFile uploadFile) throws IOException;

    boolean updateandfile(ZhgdmeetingagendaVo zhgdmeetingagenda);

    void downloadFile(Integer id);
}
