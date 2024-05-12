package com.app.social.social.app.config;

import com.app.social.social.app.payload.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Map<HttpStatus, String> statusToErrorNameMap = new HashMap<>();

    static {
        statusToErrorNameMap.put(HttpStatus.BAD_REQUEST, "Bad Request");
        statusToErrorNameMap.put(HttpStatus.UNAUTHORIZED, "Unauthorized");
        statusToErrorNameMap.put(HttpStatus.CONFLICT, "CONFLICT");
        statusToErrorNameMap.put(HttpStatus.NOT_FOUND, "NOT FOUND");
        // Add more status codes as needed
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // Default status code

        ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);
        String errorName = "Unknown Error";
        if (responseStatus != null) {
            status = responseStatus.value(); // Retrieve status code from ResponseStatus annotation
            errorName =statusToErrorNameMap.getOrDefault(status, "Unknown Error");
        }

        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setErrorName(errorName);
        return new ResponseEntity<>(errorResponse, status);
    }

}
