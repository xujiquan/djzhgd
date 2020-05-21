package com.djzhgd.module.teamManagement.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.djzhgd.module.teamManagement.domain.ZhgdTeamEducation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: ZhgdTeamEducationVo
 * @Author: zhangheng
 * @DATE: 2020/5/20 17:29
 * @Version 1.0
 **/
@Data
public class ZhgdTeamEducationVo {

    private Integer id;

    private Integer deptId;

    private Integer tenantId;

    private String meetName;

    private String meetTimeVo;

    private String teamCode;

    private String teamLeader;

    private String videoUrl;

    private String zhgdUserId;

    private String videoName;

    private String fileName;

    private String disabled;

    private String createUserid;

    private String createUsername;

    private String createDatetimeVo;

    private String createIp;

    private String updateUserid;

    private String updateUsername;

    private String updateDatetimeVo;

    private String updateIp;

    private String startTime;

    private String endTime;

}
