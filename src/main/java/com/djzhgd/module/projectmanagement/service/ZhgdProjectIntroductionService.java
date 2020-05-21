package com.djzhgd.module.projectmanagement.service;

import com.djzhgd.module.projectmanagement.domain.ZhgdProjectIntroduction;
import com.djzhgd.module.projectmanagement.vo.ZhgdParticipatingUnitsVo;
import com.djzhgd.module.projectmanagement.vo.ZhgdProjectIntroductionVo;
import com.djzhgd.module.result.PageResult;
import com.djzhgd.module.result.Result;

public interface ZhgdProjectIntroductionService {

    // 查询zhgdProjectIntroduction模块的列表(分页)
    PageResult<ZhgdProjectIntroductionVo> getZhgdProjectIntroductionList(PageResult<ZhgdProjectIntroductionVo> pageResult);

//     新增一条zhgdProjectIntroduction信息
    Result addZhgdProjectIntroduction(ZhgdProjectIntroductionVo zhgdProjectIntroductionVo);

    // 修改updateData数据
    int updateData(ZhgdProjectIntroduction zhgdProjectIntroduction);

    ZhgdProjectIntroduction detail(Integer id);
    // 删除一条数据
    int delete(Integer id);



}
