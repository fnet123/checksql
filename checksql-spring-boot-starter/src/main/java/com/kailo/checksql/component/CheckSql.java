package com.kailo.checksql.component;

import com.kailo.checksql.autoconfigure.CheckSqlProperties;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * 校验组件
 */
public interface CheckSql {

    /**
     * 加载 CheckSqlProperties
     * @param checkSqlProperties
     */
    void setCheckSqlProperties(CheckSqlProperties checkSqlProperties);

    /**
     * 根据配置返回是否执行该组件
     * @return
     */
    boolean isCheck();

    /**
     * 校验方案，返回true代表命中，false跳过
     * @param sqlCommandType
     * @param sql
     * @return
     */
    boolean check(SqlCommandType sqlCommandType, String sql);
}
