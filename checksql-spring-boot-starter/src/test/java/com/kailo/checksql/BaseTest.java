package com.kailo.checksql;

import com.kailo.checksql.mybatis.enums.interceptor.CheckSqlInterceptor;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ApplicationTest.class, DataSourceAutoConfiguration.class})
public abstract class BaseTest {

    protected CheckSqlInterceptor checkSqlInterceptor;

    @Autowired
    public void setCheckSqlInterceptor(CheckSqlInterceptor checkSqlInterceptor) {
        this.checkSqlInterceptor = checkSqlInterceptor;
    }

    protected UserMapper userMapper;

    @Autowired(required = false)
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

}
