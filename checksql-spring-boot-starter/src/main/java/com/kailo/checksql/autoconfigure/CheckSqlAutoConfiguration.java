package com.kailo.checksql.autoconfigure;

import com.kailo.checksql.mybatis.CheckSqlTypeEnum;
import com.kailo.checksql.mybatis.interceptor.CheckSqlInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@ConditionalOnClass(CheckSqlInterceptor.class)
@EnableConfigurationProperties(value = CheckSqlProperties.class)
@EnableTransactionManagement
@AllArgsConstructor
public class CheckSqlAutoConfiguration {

    private CheckSqlProperties checkSqlProperties;

    @Bean
    public CheckSqlInterceptor checkSqlInterceptor() {
        CheckSqlInterceptor checkSqlInterceptor = new CheckSqlInterceptor();
        Properties properties = new Properties();
        properties.setProperty("checkSqlType", checkSqlProperties.getType());
        checkSqlInterceptor.setProperties(properties);
        return checkSqlInterceptor;
    }
}
