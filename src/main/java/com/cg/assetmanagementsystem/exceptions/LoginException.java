package com.cg.assetmanagementsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class LoginException extends Exception {
	private static final long serialVersionUID = 1L;
	public LoginException(String message){
		super(message);
	}
	public LoginException(String message,Throwable cause) {
		super(message, cause);
	}
	public LoginException() {
	}
}
