package com.app.social.social.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ColumnsNotExistsException extends RuntimeException{
    public ColumnsNotExistsException(String column){
        super(String.format("column %s not exists" , column));
    }
}
