package com.djzhgd.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "djzhgd.upload-file") // 读取资源配置类
@Data
public class UploadFileProperties {

    private String announcement;

    private String disputeResolution;

    private String assessment;

    private String tenderingBidding;
    //人员信息
    private String zhgduser;
    //项目会议
    private String zhgdmeeting;

    private String safeActivity;

}
