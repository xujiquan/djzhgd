package com.djzhgd.module.projectmanagement.service;

import com.djzhgd.module.projectmanagement.vo.ZhgdAnnouncementVo;

import java.util.List;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: IZhgdAnnouncementService
 * @Author: xjq
 * @DATE: 2020/5/18 10:42
 * @Version 1.0
 **/
public interface IZhgdAnnouncementService {


    List<ZhgdAnnouncementVo> list(ZhgdAnnouncementVo announcementVo);

    ZhgdAnnouncementVo detail(Integer id);

    int delete(Integer id);


    int saveData(ZhgdAnnouncementVo zhgdAnnouncementVo);

    int updateData(ZhgdAnnouncementVo zhgdAnnouncementVo);

}
