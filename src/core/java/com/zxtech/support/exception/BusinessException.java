package com.zxtech.support.exception;



/**

 */
public class BusinessException extends BaseException {

	private static final long serialVersionUID = 5394041231546831105L;

	public BusinessException(String errorCode, String defaultMessage) {
		super(errorCode, defaultMessage);
	}
	
	public BusinessException(String errorCode) {
		super(errorCode, "系统繁忙，请稍后再试.");
	}

	public BusinessException(Throwable cause, String errorCode,
			String defaultMessage) {
		super(cause, errorCode, defaultMessage);
	}

	public BusinessException(Throwable cause, String errorCode) {
		super(cause, errorCode);
	}

	
}
