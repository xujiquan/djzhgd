package com.djzhgd.framework.tenant;

import com.google.common.collect.Maps;
import lombok.experimental.UtilityClass;

import java.util.Map;

/**
 * @Classname PreTenantContext
 * @Description 多租户上下文
 * @Author Created by suenle
 * @Date 2020-05-20 23:36
 * @Version 1.0
 */
@UtilityClass
public class PreTenantContextHolder {

    private static final String KEY_CURRENT_TENANT_ID = "KEY_CURRENT_TENANT_ID";
    private static final Map<String, Object> PRE_TENANT_CONTEXT = Maps.newConcurrentMap();

    /**
     * 设置租户Id
     * @param providerId
     */
    public void setCurrentTenantId(Long providerId) {
        PRE_TENANT_CONTEXT.put(KEY_CURRENT_TENANT_ID, providerId);
    }

    /**
     * 获取租户Id
     * @return
     */
    public Long getCurrentTenantId() {
        return (Long) PRE_TENANT_CONTEXT.get(KEY_CURRENT_TENANT_ID);
    }

    /**
     * 清除租户信息
     */
    public void clear() {
        PRE_TENANT_CONTEXT.clear();
    }
}
