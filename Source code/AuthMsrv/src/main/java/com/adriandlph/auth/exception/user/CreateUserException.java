package com.adriandlph.auth.exception.user;

import com.adriandlph.msrv.exception.MsrvException;

/**
 *
 * @author adriandlph
 * 
 */
public class CreateUserException extends MsrvException {

	public CreateUserException() {
	}

	public CreateUserException(String message) {
		super(message);
	}

	public CreateUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public CreateUserException(Throwable cause) {
		super(cause);
	}

	public CreateUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CreateUserException(int code) {
		super(code);
	}

	public CreateUserException(int code, String message) {
		super(code, message);
	}

	public CreateUserException(int code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public CreateUserException(int code, Throwable cause) {
		super(code, cause);
	}

	public CreateUserException(int code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(code, message, cause, enableSuppression, writableStackTrace);
	}
	
}
