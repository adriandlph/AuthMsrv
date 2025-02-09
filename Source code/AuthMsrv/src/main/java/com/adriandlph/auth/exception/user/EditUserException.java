package com.adriandlph.auth.exception.user;

import com.adriandlph.msrv.exception.MsrvException;

/**
 *
 * @author adriandlph
 * 
 */
public class EditUserException extends MsrvException{

	public EditUserException() {
	}

	public EditUserException(String message) {
		super(message);
	}

	public EditUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public EditUserException(Throwable cause) {
		super(cause);
	}

	public EditUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EditUserException(int code) {
		super(code);
	}

	public EditUserException(int code, String message) {
		super(code, message);
	}

	public EditUserException(int code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public EditUserException(int code, Throwable cause) {
		super(code, cause);
	}

	public EditUserException(int code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(code, message, cause, enableSuppression, writableStackTrace);
	}
	
}
