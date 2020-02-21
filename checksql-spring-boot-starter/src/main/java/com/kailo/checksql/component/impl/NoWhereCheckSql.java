package com.kailo.checksql.component.impl;

import com.kailo.checksql.autoconfigure.CheckSqlProperties;
import com.kailo.checksql.component.CheckSql;
import com.kailo.checksql.utils.SqlParserUtils;
import lombok.AllArgsConstructor;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.mapping.SqlCommandType;
import org.springframework.stereotype.Component;

@Component("noWhereCheckSql")
@AllArgsConstructor
public class NoWhereCheckSql implements CheckSql {

    private CheckSqlProperties checkSqlProperties;

    public boolean isCheck() {
        return checkSqlProperties.isCheckNoWhere();
    }

    public boolean check(SqlCommandType sqlCommandType, String sql)  {
        if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
            Statement statement = SqlParserUtils.parse(sql);
            Update update = (Update) statement;
            return update.getWhere() == null;
        } else if (SqlCommandType.DELETE.equals(sqlCommandType)) {
            Statement statement = SqlParserUtils.parse(sql);
            Delete delete = (Delete) statement;
            return delete.getWhere() == null;
        } else if (SqlCommandType.SELECT.equals(sqlCommandType)) {
            Statement statement = SqlParserUtils.parse(sql);
            Select select = (Select) statement;
            SelectBody selectBody = select.getSelectBody();
            PlainSelect plainSelect = (PlainSelect) selectBody;
            return plainSelect.getWhere() == null;
        }
        return true;
    }
}
