package com.djzhgd.module.people.domain.vo;

import com.djzhgd.module.people.domain.ZhgdSafeActivity;
import lombok.Data;

import java.util.List;

@Data
public class ZhgdSafeActivityVo extends ZhgdSafeActivity {

    private String beginDate;

    private String endDate;

    private List<String> fileNames;

    //用于查询总条数人员信息
    private Integer totals;//

    private java.lang.String deptCode;

    private java.lang.Integer num;

    //百分比
    private java.lang.Double bfb;

    private List<String> PreviewUrlList;

    //进场开始时间
    private String conveneStartTime;
    //进结束时间
    private String conveneEndTime;


    private String selectNames;

    private String yearMonth;//月份
    // 用于人员信息查询
    private String userid;
    private String technicalTypeDesc;
}