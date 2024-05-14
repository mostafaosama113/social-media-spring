package com.app.social.social.app.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotValidRequestException extends RuntimeException {
    public List<String> list;
    public NotValidRequestException(BindingResult bindingResult){
        super("Not valid request exception.");
        list = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(objectError ->{
            String name = objectError.getField();
            String error = objectError.getDefaultMessage();
            list.add(String.format("%s: %s", name, error));
        });
    }
}
