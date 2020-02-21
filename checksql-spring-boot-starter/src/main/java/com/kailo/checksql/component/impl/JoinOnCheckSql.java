package com.kailo.checksql.component.impl;

import com.kailo.checksql.component.BaseCheckSql;
import com.kailo.checksql.utils.SqlParserUtils;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.mapping.SqlCommandType;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component("JoinOnCheckSql")
public class JoinOnCheckSql extends BaseCheckSql {
    @Override
    public boolean isCheck() {
        return true;
    }

    @Override
    public boolean check(SqlCommandType sqlCommandType, String sql) {
        List<Join> joins = null;
        if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
            Statement statement = SqlParserUtils.parse(sql);
            Update update = (Update) statement;
            joins = update.getJoins();
        } else if (SqlCommandType.DELETE.equals(sqlCommandType)) {
            Statement statement = SqlParserUtils.parse(sql);
            Delete delete = (Delete) statement;
            joins = delete.getJoins();
        } else if (SqlCommandType.SELECT.equals(sqlCommandType)) {
            Statement statement = SqlParserUtils.parse(sql);
            Select select = (Select) statement;
            SelectBody selectBody = select.getSelectBody();
            PlainSelect plainSelect = (PlainSelect) selectBody;
            joins = plainSelect.getJoins();
        }

        if (!CollectionUtils.isEmpty(joins)) {
            Expression expression ;
            boolean isLeft ;
            boolean isFull ;
            for (Join join : joins) {
                expression = join.getOnExpression();
                if (expression == null) {
                    return true;
                }
                isLeft = join.isLeft();
                if (isLeft) {
                    return true;
                }
                isFull = join.isFull();
                if (isFull) {
                    return true;
                }
            }
        }

        return false;
    }
}
