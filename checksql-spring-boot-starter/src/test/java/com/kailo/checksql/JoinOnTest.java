package com.kailo.checksql;

import com.kailo.checksql.autoconfigure.CheckSqlProperties;
import com.kailo.checksql.mybatis.enums.CheckSqlTypeEnum;
import com.kailo.checksql.mybatis.enums.exception.CheckSqlRuntimeException;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class JoinOnTest extends BaseTest {

    @Test
    public void testNoJoinOn() {
        CheckSqlProperties checkSqlProperties = new CheckSqlProperties();
        checkSqlProperties.setReturnType(CheckSqlTypeEnum.exception.name());
        checkSqlInterceptor.setCheckSqlProperties(checkSqlProperties);
        checkSqlProperties.setCheckNoWhere(true);
        Map<String, Object> columnMap = new HashMap<>();
        try {
            List list = userMapper.selectUserInfoNoJoinOn();
            Assert.assertFalse("预期抛异常，实际没有抛", true);
        } catch (Exception ex) {
            log.error(ex);
            Throwable throwable = ex.getCause().getCause();
            Assert.assertEquals(true, throwable instanceof CheckSqlRuntimeException);
        }
    }
}
