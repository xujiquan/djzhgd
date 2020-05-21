package com.djzhgd.module.teamManagement.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.djzhgd.module.result.Result;
import com.djzhgd.module.teamManagement.domain.ZhgdTeamEducation;
import com.djzhgd.module.teamManagement.vo.ZhgdTeamEducationVo;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: ZhgdTeamEducationService
 * @Author: zhangheng
 * @DATE: 2020/5/20 17:05
 * @Version 1.0
 **/
public interface ZhgdTeamEducationService {

    // 班前教育查询集合
    IPage<ZhgdTeamEducation> list(ZhgdTeamEducationVo zhgdTeamEducationVo) throws ParseException;

    // 根据班前教育ID查询详情
    Result getZhgdTeamEducationDetail(ZhgdTeamEducation zhgdTeamEducation);

    // 新增一条班前教育信息
    Result addZhgdTeamEducation(ZhgdTeamEducation zhgdTeamEducation, HttpServletRequest request);

    // 修改一条班前教育信息
    Result updateZhgdTeamEducation(ZhgdTeamEducation zhgdTeamEducation, HttpServletRequest request);

    // 根据ID批量删除班前教育数据
    Result deleteById(List<Long> idList);

}
