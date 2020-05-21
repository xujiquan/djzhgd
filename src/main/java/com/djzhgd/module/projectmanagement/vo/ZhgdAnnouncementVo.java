package com.djzhgd.module.projectmanagement.vo;

import com.djzhgd.module.projectmanagement.domain.ZhgdAnnouncement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class ZhgdAnnouncementVo extends ZhgdAnnouncement {

    private String beginDate;

    private String endDate;

    private String TITLE;

    private String CONTENT;

    private String TREATMENT_PLAN;

    private String HANDLER;

    private String STATUS;

    private String HANDLER_TIME;

    private List<Integer> fileId;

    private List<Map<String, Object>> uploadAttachment = new ArrayList<>();

    private List<Map<String, Object>> uploadImage = new ArrayList<>();

    private String filePath;

    private Integer fileType;



}