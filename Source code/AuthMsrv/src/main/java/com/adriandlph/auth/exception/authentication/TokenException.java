package com.adriandlph.auth.exception.authentication;

import com.adriandlph.msrv.exception.MsrvException;

/**
 *
 * @author adriandlph
 * 
 */
public class TokenException extends MsrvException {

	public TokenException() {
	}

	public TokenException(String message) {
		super(message);
	}

	public TokenException(String message, Throwable cause) {
		super(message, cause);
	}

	public TokenException(Throwable cause) {
		super(cause);
	}

	public TokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TokenException(int code) {
		super(code);
	}

	public TokenException(int code, String message) {
		super(code, message);
	}

	public TokenException(int code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public TokenException(int code, Throwable cause) {
		super(code, cause);
	}

	public TokenException(int code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(code, message, cause, enableSuppression, writableStackTrace);
	}
	
}
