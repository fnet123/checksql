package com.kailo.checksql.mybatis.exception;

public class NoWhereRuntimeException extends RuntimeException {
    public NoWhereRuntimeException() {
        super();
    }
    public NoWhereRuntimeException(String message) {
        super(message);
    }
}
