package com.app.social.social.app.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceExistsException extends RuntimeException {
    public ResourceExistsException(){
        super("This resource already exists.");
    }
}
