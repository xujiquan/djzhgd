package com.djzhgd.module.contract.service;

import com.djzhgd.module.projectmanagement.vo.ZhgdDisputeResolutionVo;

import java.util.List;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: IZhgdDisputeResolutionService
 * @Author: xjq
 * @DATE: 2020/5/18 10:42
 * @Version 1.0
 **/
public interface IZhgdDisputeResolutionService {


    List<ZhgdDisputeResolutionVo> list(ZhgdDisputeResolutionVo disputeResolutionVo);

    ZhgdDisputeResolutionVo detail(Integer id);

    int delete(Integer id);

    int saveData(ZhgdDisputeResolutionVo zhgdDisputeResolutionVo);

    int updateData(ZhgdDisputeResolutionVo zhgdDisputeResolutionVo);


}
