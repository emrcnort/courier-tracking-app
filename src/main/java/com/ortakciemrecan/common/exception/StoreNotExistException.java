package com.ortakciemrecan.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StoreNotExistException extends Exception {
    public StoreNotExistException() {
        super();
    }

}