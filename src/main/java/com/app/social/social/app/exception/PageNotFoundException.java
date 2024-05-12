package com.app.social.social.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PageNotFoundException extends RuntimeException {
    public PageNotFoundException(){
        super("This page does not exist.");
    }
}
