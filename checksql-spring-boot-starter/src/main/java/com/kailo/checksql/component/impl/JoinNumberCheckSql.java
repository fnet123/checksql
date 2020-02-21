package com.kailo.checksql.component.impl;

import com.kailo.checksql.component.BaseCheckSql;
import com.kailo.checksql.utils.SqlParserUtils;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.mapping.SqlCommandType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("JoinNumberCheckSql")
public class JoinNumberCheckSql extends BaseCheckSql {

    @Override
    public boolean isCheck() {
        return checkSqlProperties.isCheckMaxJoinNumber();
    }

    @Override
    public boolean check(SqlCommandType sqlCommandType, String sql) {
        int max = checkSqlProperties.getInitMaxJoinNumber();
        if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
            Statement statement = SqlParserUtils.parse(sql);
            Update update = (Update) statement;
            List<Join> joins = update.getJoins();
            int joinNumber = joins.size() + 1;
            return joinNumber > max;
        } else if (SqlCommandType.DELETE.equals(sqlCommandType)) {
            Statement statement = SqlParserUtils.parse(sql);
            Delete delete = (Delete) statement;
            List<Join> joins = delete.getJoins();
            int joinNumber = joins.size() + 1;
            return joinNumber > max;
        } else if (SqlCommandType.SELECT.equals(sqlCommandType)) {
            Statement statement = SqlParserUtils.parse(sql);
            Select select = (Select) statement;
            SelectBody selectBody = select.getSelectBody();
            PlainSelect plainSelect = (PlainSelect) selectBody;
            List<Join> joins = plainSelect.getJoins();
            int joinNumber = joins.size() + 1;
            return joinNumber > max;
        }
        return false;
    }
}
