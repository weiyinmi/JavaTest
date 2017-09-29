package com.accenture.web.exception;

/**
 * Define the exception for service level and throw it to business level
 */
public class BusinessException extends Exception {


	private static final long serialVersionUID = -4139860061860830465L;

	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
        super(message,cause);
	}

	public BusinessException(Throwable cause) {
        super(cause);
	}

}
