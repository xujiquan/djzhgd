package com.djzhgd.module.projectmanagement.vo;

import com.djzhgd.module.contract.domain.ZhgdDisputeResolution;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class ZhgdDisputeResolutionVo extends ZhgdDisputeResolution {

    private String statusDesc;

    private String beginDate;

    private String endDate;

    private List<Integer> fileId;

    private List<Map<String, Object>> uploadAttachment = new ArrayList<>();

    private List<Map<String, Object>> uploadImage = new ArrayList<>();

    private String filePath;

    private Integer fileType;


}