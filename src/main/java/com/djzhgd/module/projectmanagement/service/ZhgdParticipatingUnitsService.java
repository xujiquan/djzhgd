package com.djzhgd.module.projectmanagement.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.djzhgd.module.projectmanagement.domain.ZhgdParticipatingUnits;
import com.djzhgd.module.projectmanagement.vo.ZhgdParticipatingUnitsVo;


/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: DemoService
 * @Author: zhangheng
 * @DATE: 2020/5/13 10:42
 * @Version 1.0
 **/
public interface ZhgdParticipatingUnitsService extends IService<ZhgdParticipatingUnits> {

    IPage<ZhgdParticipatingUnits> list(ZhgdParticipatingUnits zhgdParticipatingUnits);

}
