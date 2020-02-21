package com.kailo.checksql;

import com.kailo.checksql.autoconfigure.CheckSqlProperties;
import com.kailo.checksql.mybatis.enums.CheckSqlTypeEnum;
import com.kailo.checksql.mybatis.exception.CheckSqlRuntimeException;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

@Log4j2
public class NoWhereTest extends BaseTest {

    @Test
    public void testListNoWhereNullSql() {
        CheckSqlProperties checkSqlProperties = new CheckSqlProperties();
        checkSqlProperties.setReturnType(CheckSqlTypeEnum.nullValue.name());
        checkSqlProperties.setCheckNoWhere(true);
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
        checkSqlProperties.setCheckNoWhere(true);
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

    @Test
    public void testListNoWhereManyJoins() {
        CheckSqlProperties checkSqlProperties = new CheckSqlProperties();
        checkSqlProperties.setReturnType(CheckSqlTypeEnum.exception.name());
        checkSqlInterceptor.setCheckSqlProperties(checkSqlProperties);
        checkSqlProperties.setCheckNoWhere(true);
        Map<String, Object> columnMap = new HashMap<>();
        try {
            List list = userMapper.selectUserInfo();
            Assert.assertFalse("预期抛异常，实际没有抛", true);
        } catch (Exception ex) {
            Throwable throwable = ex.getCause().getCause();
            Assert.assertEquals(true, throwable instanceof CheckSqlRuntimeException);
        }
    }
}
