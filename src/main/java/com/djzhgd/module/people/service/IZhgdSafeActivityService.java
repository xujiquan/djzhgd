package com.djzhgd.module.people.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.djzhgd.module.people.domain.ZhgdSafeActivity;
import com.djzhgd.module.people.domain.vo.ZhgdSafeActivityVo;
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
public interface IZhgdSafeActivityService extends IService<ZhgdSafeActivity> {

    IPage<ZhgdSafeActivity> list(ZhgdSafeActivityVo zhgdSafeActivityVo);

    ZhgdSafeActivityVo detail(Integer id);

    int delete(Integer id);


    boolean saveData(ZhgdSafeActivityVo zhgdSafeActivityVo);

    boolean updateData(ZhgdSafeActivityVo zhgdSafeActivityVo);

    Map<String, Object> uploadFile(MultipartFile uploadFile) throws IOException;

    /**
     * 统计
     */
    List<ZhgdSafeActivityVo> getTrainCount(ZhgdSafeActivityVo zhgdSafeActivityVo);

    List<ZhgdSafeActivityVo> trainZBCount(ZhgdSafeActivityVo zhgdSafeActivityVo);
}
