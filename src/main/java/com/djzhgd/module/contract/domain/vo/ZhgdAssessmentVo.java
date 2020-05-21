package com.djzhgd.module.contract.domain.vo;

import com.djzhgd.module.contract.domain.ZhgdAssessment;
import lombok.Data;

import java.util.List;

@Data
public class ZhgdAssessmentVo extends ZhgdAssessment {

    private String beginDate;

    private String endDate;

    private List<String> fileNames;

    private String filePath;
}