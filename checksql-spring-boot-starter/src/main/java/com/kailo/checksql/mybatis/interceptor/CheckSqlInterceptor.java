package com.kailo.checksql.mybatis.interceptor;

import com.kailo.checksql.mybatis.CheckSqlTypeEnum;
import com.kailo.checksql.mybatis.exception.NoWhereRuntimeException;
import lombok.extern.log4j.Log4j2;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Properties;

@Log4j2
@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class, Integer.class }) })
public class CheckSqlInterceptor implements Interceptor {

    private String checkSqlType;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler handler = (StatementHandler)invocation.getTarget();
        MetaObject statementHandler = SystemMetaObject.forObject(handler);
        MappedStatement mappedStatement = (MappedStatement) statementHandler.getValue("delegate.mappedStatement");
        BoundSql boundSql = handler.getBoundSql();
        String sql = boundSql.getSql();
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        if (isNoWhere(sqlCommandType, sql)) {
            noWhere(statementHandler);
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
        this.checkSqlType = properties.getProperty("checkSqlType");
    }

    private boolean isNoWhere(SqlCommandType sqlCommandType, String sql) throws JSQLParserException {
        if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
            Statement statement = CCJSqlParserUtil.parse(sql);
            Update update = (Update) statement;
            return update.getWhere() == null;
        } else if (SqlCommandType.DELETE.equals(sqlCommandType)) {
            Statement statement = CCJSqlParserUtil.parse(sql);
            Delete delete = (Delete) statement;
            return delete.getWhere() == null;
        } else if (SqlCommandType.SELECT.equals(sqlCommandType)) {
            Statement statement = CCJSqlParserUtil.parse(sql);
            Select select = (Select) statement;
            SelectBody selectBody = select.getSelectBody();
            PlainSelect plainSelect = (PlainSelect) selectBody;
            return plainSelect.getWhere() == null;
        }
        return true;
    }

    private void noWhere(MetaObject statementHandler) {
        if (CheckSqlTypeEnum.exception.name().equals(this.checkSqlType)) {
            throw new NoWhereRuntimeException("SQL Where cannot be empty!");
        } else {
            statementHandler.setValue("delegate.boundSql.sql", "SELECT 1");
        }
    }
}
