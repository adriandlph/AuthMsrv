package com.adriandlph.auth.exception.authentication;

import com.adriandlph.msrv.exception.MsrvException;

/**
 *
 * @author adriandlph
 * 
 */
public class LoginException extends MsrvException {

	public LoginException() {
	}

	public LoginException(String message) {
		super(message);
	}

	public LoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginException(Throwable cause) {
		super(cause);
	}

	public LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LoginException(int code) {
		super(code);
	}

	public LoginException(int code, String message) {
		super(code, message);
	}

	public LoginException(int code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public LoginException(int code, Throwable cause) {
		super(code, cause);
	}

	public LoginException(int code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(code, message, cause, enableSuppression, writableStackTrace);
	}
	
}
