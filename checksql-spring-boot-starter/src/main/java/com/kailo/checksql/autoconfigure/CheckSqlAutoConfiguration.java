package com.kailo.checksql.autoconfigure;

import com.kailo.checksql.component.CheckSql;
import com.kailo.checksql.component.impl.NoWhereCheckSql;
import com.kailo.checksql.mybatis.interceptor.CheckSqlInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Configuration
@ConditionalOnClass(CheckSqlInterceptor.class)
@EnableConfigurationProperties(value = CheckSqlProperties.class)
@ComponentScan(value = "com.kailo.checksql.component.impl")
@EnableTransactionManagement
@AllArgsConstructor
public class CheckSqlAutoConfiguration {

    private CheckSqlProperties checkSqlProperties;

    private List<CheckSql> checkSqlList;

    @Bean
    public CheckSqlInterceptor checkSqlInterceptor() {
        CheckSqlInterceptor checkSqlInterceptor = new CheckSqlInterceptor();
        checkSqlInterceptor.setCheckSqlProperties(checkSqlProperties);
        checkSqlInterceptor.setCheckSqlList(checkSqlList);
        return checkSqlInterceptor;
    }
}
