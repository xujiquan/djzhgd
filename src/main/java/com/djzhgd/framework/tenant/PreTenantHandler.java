package com.djzhgd.framework.tenant;

import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.schema.Column;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname PreTenantHandler
 * @Description 租户处理器 -主要实现mybatis-plus https://mp.baomidou.com/guide/tenant.html
 * @Author Created by suenle
 * @Date 2020-05-20 23:36
 * @Version 1.0
 */
@Slf4j
@Component
public class PreTenantHandler implements TenantHandler {

    @Autowired
    private PreTenantConfigProperties configProperties;

    /**
     * 租户Id
     *
     * @return
     */
    @Override
    public Expression getTenantId(boolean where) {
        Long tenantId = PreTenantContextHolder.getCurrentTenantId();
        log.debug("当前租户为{}", tenantId);
        final boolean multipleTenantIds = true;//自己判断是单个tenantId还是需要多个id in(1,2,3)
        return multipleTenantIdCondition();
//        if (where && multipleTenantIds) {
//            return multipleTenantIdCondition(tenantId);
//        } else {
//            return singleTenantIdCondition(tenantId);
//        }

    }

    private Expression singleTenantIdCondition(Long tenantId) {
        if (tenantId == null) {
            return new NullValue();
        }{
            return new LongValue(1);//ID自己想办法获取到
        }

    }

    private Expression multipleTenantIdCondition() {
        final InExpression inExpression = new InExpression();
        inExpression.setLeftExpression(new Column(getTenantIdColumn()));
        final ExpressionList itemsList = new ExpressionList();
        final List<Expression> inValues = new ArrayList<>();
        inValues.add(new LongValue(1));//ID自己想办法获取到
        inValues.add(new LongValue(2));
        inValues.add(new LongValue(6));
        inValues.add(new LongValue(7));
        itemsList.setExpressions(inValues);
        inExpression.setRightItemsList(itemsList);
        return inExpression;
    }

    /**
     * 租户字段名
     *
     * @return
     */
    @Override
    public String getTenantIdColumn() {
        return configProperties.getTenantIdColumn();
    }

    /**
     * 根据表名判断是否进行过滤
     * 忽略掉一些表：如租户表（sys_tenant）本身不需要执行这样的处理
     *
     * @param tableName
     * @return
     */
    @Override
    public boolean doTableFilter(String tableName) {
        return configProperties.getIgnoreTenantTables().stream().anyMatch((e) -> e.equalsIgnoreCase(tableName));
    }
}
