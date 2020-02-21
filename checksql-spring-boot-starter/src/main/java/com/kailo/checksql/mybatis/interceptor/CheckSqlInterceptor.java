package com.kailo.checksql.mybatis.interceptor;

import com.kailo.checksql.autoconfigure.CheckSqlProperties;
import com.kailo.checksql.component.CheckSql;
import com.kailo.checksql.mybatis.CheckSqlTypeEnum;
import com.kailo.checksql.mybatis.exception.CheckSqlRuntimeException;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.util.CollectionUtils;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

@Log4j2
@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class, Integer.class }) })
public class CheckSqlInterceptor implements Interceptor {

    private CheckSqlProperties checkSqlProperties;
    private List<CheckSql> checkSqlList;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler handler = (StatementHandler)invocation.getTarget();
        MetaObject statementHandler = SystemMetaObject.forObject(handler);
        MappedStatement mappedStatement = (MappedStatement) statementHandler.getValue("delegate.mappedStatement");
        BoundSql boundSql = handler.getBoundSql();
        String sql = boundSql.getSql();
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        if (!CollectionUtils.isEmpty(checkSqlList)) {
            checkSqlList.stream().forEach(item -> {
                if (!item.isCheck()) {
                    return;
                }
                if (item.check(sqlCommandType, sql)) {
                    processCheckError(item, statementHandler, sql);
                }
            });
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
    }

    public void setCheckSqlProperties(CheckSqlProperties checkSqlProperties) {
        this.checkSqlProperties = checkSqlProperties;
    }

    public void setCheckSqlList(List<CheckSql> checkSqlList) {
        this.checkSqlList = checkSqlList;
    }

    private void processCheckError(CheckSql checkSql, MetaObject statementHandler, String sql) {
        if (CheckSqlTypeEnum.exception.name().equals(checkSqlProperties.getReturnType())) {
            String errorMessages = String.format("%s%s SQL:(%s)", checkSql.getClass(), " Validate was fail! ", sql);
            throw new CheckSqlRuntimeException(errorMessages);
        } else {
            statementHandler.setValue("delegate.boundSql.sql", "SELECT 1");
        }
    }
}
