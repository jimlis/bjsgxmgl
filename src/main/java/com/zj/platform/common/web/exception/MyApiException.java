package com.zj.platform.common.web.exception;


/**
 * API异常基类
 */
public class MyApiException extends CommonException {

    private static final long serialVersionUID = -4891641110275580161L;

    public MyApiException() {
        super();
    }

    public MyApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MyApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyApiException(String message) {
        super(message);
    }

    public MyApiException(Throwable cause) {
        super(cause);
    }

}
