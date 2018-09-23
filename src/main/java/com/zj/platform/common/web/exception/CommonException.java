package com.zj.platform.common.web.exception;

/**
 * 自定义异常
 * @author  zj
 */
public class CommonException extends RuntimeException {

	public CommonException() {
		super();
	}

	public CommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CommonException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommonException(String message) {
		super(message);
	}

	public CommonException(Throwable cause) {
		super(cause);
	}
}
