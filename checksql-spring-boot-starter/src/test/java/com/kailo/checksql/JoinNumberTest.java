package com.kailo.checksql;

import com.kailo.checksql.autoconfigure.CheckSqlProperties;
import com.kailo.checksql.mybatis.enums.CheckSqlTypeEnum;
import com.kailo.checksql.mybatis.exception.CheckSqlRuntimeException;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

@Log4j2
public class JoinNumberTest extends BaseTest {

    @Test
    public void testListJoin3Number() {
        CheckSqlProperties checkSqlProperties = new CheckSqlProperties();
        checkSqlProperties.setReturnType(CheckSqlTypeEnum.exception.name());
        checkSqlProperties.setInitMaxJoinNumber(3);
        checkSqlProperties.setCheckMaxJoinNumber(true);
        checkSqlInterceptor.setCheckSqlProperties(checkSqlProperties);
        try {
            List list = userMapper.selectUserInfo();
            Assert.assertFalse("预期抛异常，实际没有抛", true);
        } catch (Exception ex) {
            log.error(ex);
            Throwable throwable = ex.getCause().getCause();
            Assert.assertEquals(true, throwable instanceof CheckSqlRuntimeException);
        }
    }

    @Test
    public void testListJoin5Number() {
        CheckSqlProperties checkSqlProperties = new CheckSqlProperties();
        checkSqlProperties.setReturnType(CheckSqlTypeEnum.exception.name());
        checkSqlProperties.setInitMaxJoinNumber(5);
        checkSqlProperties.setCheckMaxJoinNumber(true);
        checkSqlInterceptor.setCheckSqlProperties(checkSqlProperties);
        try {
            List list = userMapper.selectUserInfo();
            Assert.assertTrue("预期不抛异常",true);
        } catch (Exception ex) {
            log.error(ex);
            Throwable throwable = ex.getCause().getCause();
            Assert.assertFalse("预期不抛异常",true);
        }
    }
}
