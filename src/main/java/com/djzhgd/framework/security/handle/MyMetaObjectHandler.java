package com.djzhgd.framework.security.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.djzhgd.module.constants.SystemConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 填充器
 *
 * @author nieqiurong 2018-08-10 22:59:23.
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.fillStrategy(metaObject, "createDatetime",new Date()); // 起始版本 3.3.0(推荐使用)
        this.fillStrategy(metaObject, "updateDatetime", new Date()); // 起始版本 3.3.0(推荐使用)
        this.fillStrategy(metaObject, "disabled", SystemConstant.INTEGER_ZERO); // 起始版本 3.3.0(推荐使用)
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.fillStrategy(metaObject, "updateDatetime", new Date()); // 起始版本 3.3.0(推荐使用)
    }
}
