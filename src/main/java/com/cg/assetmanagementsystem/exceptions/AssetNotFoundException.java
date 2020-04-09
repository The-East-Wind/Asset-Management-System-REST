package com.cg.assetmanagementsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AssetNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public AssetNotFoundException(String message){
        super(message);
    }
    public AssetNotFoundException(String message,Throwable cause){
        super(message,cause);
    }
}
