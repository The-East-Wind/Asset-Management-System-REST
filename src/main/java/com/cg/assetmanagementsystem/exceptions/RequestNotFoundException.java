package com.cg.assetmanagementsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RequestNotFoundException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RequestNotFoundException(String message) {
        super(message);
    }

    public RequestNotFoundException(String message,Throwable cause) {
        super(message,cause);
    }
}
