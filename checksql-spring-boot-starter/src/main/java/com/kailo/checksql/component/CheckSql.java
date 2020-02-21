package com.kailo.checksql.component;

import org.apache.ibatis.mapping.SqlCommandType;

public interface CheckSql {

    boolean isCheck();

    boolean check(SqlCommandType sqlCommandType, String sql);
}
