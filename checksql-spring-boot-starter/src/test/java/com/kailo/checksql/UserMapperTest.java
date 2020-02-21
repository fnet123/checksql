package com.kailo.checksql;

import com.kailo.checksql.autoconfigure.CheckSqlProperties;
import com.kailo.checksql.mybatis.CheckSqlTypeEnum;
import com.kailo.checksql.mybatis.exception.CheckSqlRuntimeException;
import com.kailo.checksql.mybatis.interceptor.CheckSqlInterceptor;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ApplicationTest.class, DataSourceAutoConfiguration.class})
public class UserMapperTest {

    private CheckSqlInterceptor checkSqlInterceptor;

    @Autowired
    public void setCheckSqlInterceptor(CheckSqlInterceptor checkSqlInterceptor) {
        this.checkSqlInterceptor = checkSqlInterceptor;
    }

    private UserMapper userMapper;

    @Autowired(required = false)
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Test
    public void testListNoWhereNullSql() {
        CheckSqlProperties checkSqlProperties = new CheckSqlProperties();
        checkSqlProperties.setReturnType(CheckSqlTypeEnum.nullValue.name());
        checkSqlInterceptor.setCheckSqlProperties(checkSqlProperties);
        Map<String, Object> columnMap = new HashMap<>();
        List list = userMapper.selectByMap(columnMap);
        List expected = new ArrayList();
        expected.add(null);
        Assert.assertEquals(expected, list);
    }

    @Test
    public void testListNoWhereException() {
        CheckSqlProperties checkSqlProperties = new CheckSqlProperties();
        checkSqlProperties.setReturnType(CheckSqlTypeEnum.exception.name());
        checkSqlInterceptor.setCheckSqlProperties(checkSqlProperties);
        Map<String, Object> columnMap = new HashMap<>();
        try {
            List list = userMapper.selectByMap(columnMap);
        } catch (Exception ex) {
            Throwable throwable = ex.getCause().getCause();
            Assert.assertEquals(true, throwable instanceof CheckSqlRuntimeException);
        }
    }

    @Test
    public void testListHasWhere() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "Jone");
        List<User> list = userMapper.selectByMap(columnMap);
        User user = list.get(0);
        Assert.assertEquals("Jone", user.getName());
    }
}
