package com.cg.assetmanagementsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class DeleteAllottedAssetException extends Exception {
    public DeleteAllottedAssetException(String message) {
        super(message);
    }
}
