package com.adriandlph.auth.exception.user;

import com.adriandlph.msrv.exception.MsrvException;

/**
 *
 * @author adriandlph
 * 
 */
public class DeleteUserException extends MsrvException {

	public DeleteUserException() {
	}

	public DeleteUserException(String message) {
		super(message);
	}

	public DeleteUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeleteUserException(Throwable cause) {
		super(cause);
	}

	public DeleteUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DeleteUserException(int code) {
		super(code);
	}

	public DeleteUserException(int code, String message) {
		super(code, message);
	}

	public DeleteUserException(int code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public DeleteUserException(int code, Throwable cause) {
		super(code, cause);
	}

	public DeleteUserException(int code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(code, message, cause, enableSuppression, writableStackTrace);
	}
	
}
