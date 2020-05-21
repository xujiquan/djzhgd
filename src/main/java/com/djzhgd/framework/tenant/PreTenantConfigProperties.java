package com.djzhgd.framework.tenant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname PreTenantConfigProperties
 * @Description 多租户动态配置
 * @Author suenle
 * @Date 2020-05-20 23:36
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "djzhgd.tenant")
public class PreTenantConfigProperties {

    /**
     * 维护租户id
     */
    private String tenantIdColumn;

    /**
     * 多租户的数据表集合
     */
    private List<String> ignoreTenantTables = new ArrayList<>();
}
