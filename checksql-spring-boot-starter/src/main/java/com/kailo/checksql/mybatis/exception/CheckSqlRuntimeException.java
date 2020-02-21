package com.kailo.checksql.mybatis.exception;

public class CheckSqlRuntimeException extends RuntimeException {
    public CheckSqlRuntimeException() {
        super();
    }
    public CheckSqlRuntimeException(String message) {
        super(message);
    }
}
