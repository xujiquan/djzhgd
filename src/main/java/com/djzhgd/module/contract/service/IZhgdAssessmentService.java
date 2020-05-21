package com.djzhgd.module.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.djzhgd.module.contract.domain.ZhgdAssessment;
import com.djzhgd.module.contract.domain.vo.ZhgdAssessmentVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: IZhgdAnnouncementService
 * @Author: xjq
 * @DATE: 2020/5/18 10:42
 * @Version 1.0
 **/
public interface IZhgdAssessmentService extends IService<ZhgdAssessment> {

    IPage<ZhgdAssessment> list(ZhgdAssessmentVo zhgdAssessmentVo);

    ZhgdAssessmentVo detail(Integer id);

    int delete(Integer id);


    boolean saveData(ZhgdAssessmentVo zhgdAssessmentVo);

    boolean updateData(ZhgdAssessmentVo zhgdAssessmentVo);

    Map<String, Object> uploadFile(MultipartFile uploadFile) throws IOException;
}
