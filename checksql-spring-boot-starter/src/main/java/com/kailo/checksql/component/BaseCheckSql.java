package com.kailo.checksql.component;

import com.kailo.checksql.autoconfigure.CheckSqlProperties;

public abstract class BaseCheckSql implements CheckSql {

    protected CheckSqlProperties checkSqlProperties;

    public void setCheckSqlProperties(CheckSqlProperties checkSqlProperties) {
        this.checkSqlProperties = checkSqlProperties;
    }
}
