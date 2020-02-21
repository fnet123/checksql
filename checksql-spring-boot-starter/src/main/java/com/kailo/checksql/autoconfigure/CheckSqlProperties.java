package com.kailo.checksql.autoconfigure;

import com.kailo.checksql.mybatis.CheckSqlTypeEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

/**
 * CheckSql Properties
 */
@ConfigurationProperties(value = "check-sql")
@Data
public class CheckSqlProperties {

    private String returnType = CheckSqlTypeEnum.exception.name();

    private boolean checkNoWhere = false;
    private boolean checkMaxJoinNumber = false;
    private int initMaxJoinNumber = 3;
}
