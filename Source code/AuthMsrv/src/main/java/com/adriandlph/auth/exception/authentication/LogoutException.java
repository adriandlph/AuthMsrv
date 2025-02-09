package com.adriandlph.auth.exception.authentication;

import com.adriandlph.msrv.exception.MsrvException;

/**
 *
 * @author adriandlph
 * 
 */
public class LogoutException extends MsrvException {

	public LogoutException() {
	}

	public LogoutException(String message) {
		super(message);
	}

	public LogoutException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogoutException(Throwable cause) {
		super(cause);
	}

	public LogoutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LogoutException(int code) {
		super(code);
	}

	public LogoutException(int code, String message) {
		super(code, message);
	}

	public LogoutException(int code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public LogoutException(int code, Throwable cause) {
		super(code, cause);
	}

	public LogoutException(int code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(code, message, cause, enableSuppression, writableStackTrace);
	}
	
}
