package com.kailo.checksql.autoconfigure;

import com.kailo.checksql.mybatis.CheckSqlTypeEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * CheckSql Properties
 */
@ConfigurationProperties(value = "check-sql")
@Data
public class CheckSqlProperties {

    private String type = CheckSqlTypeEnum.exception.name();
}
