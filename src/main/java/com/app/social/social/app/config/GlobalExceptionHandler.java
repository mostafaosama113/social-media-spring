package com.app.social.social.app.config;

import com.app.social.social.app.exception.NotValidRequestException;
import com.app.social.social.app.exception.ResourceNotFoundException;
import com.app.social.social.app.payload.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.app.social.social.app.utils.AppConstants.UNKNOWN_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Map<HttpStatus, String> statusToErrorNameMap = new HashMap<>();

    static {
        statusToErrorNameMap.put(HttpStatus.BAD_REQUEST, "BAD REQUEST");
        statusToErrorNameMap.put(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
        statusToErrorNameMap.put(HttpStatus.CONFLICT, "CONFLICT");
        statusToErrorNameMap.put(HttpStatus.NOT_FOUND, "NOT FOUND");
        // Add more status codes as needed
    }

    public static void checkAndFire(Object Dto , BindingResult bindingResult){
        if(Dto == null){
            throw new ResourceNotFoundException("Dto cannot be null.");
        }
        if(bindingResult.hasErrors()){
            throw new NotValidRequestException(bindingResult);
        }
    }
    public static void checkAndFire(Object Dto){
        if(Dto == null){
            throw new ResourceNotFoundException("Dto cannot be null.");
        }
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // Default status code

        ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);
        String errorName = UNKNOWN_ERROR;
        if (responseStatus != null) {
            status = responseStatus.value(); // Retrieve status code from ResponseStatus annotation
            errorName =statusToErrorNameMap.getOrDefault(status, UNKNOWN_ERROR);
        }

        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setError(errorName);
        errorResponse.setStatus(status.value());
        errorResponse.setTimestamp(new Date());
        if(ex instanceof NotValidRequestException){
            errorResponse.setList(((NotValidRequestException) ex).getList());
        }
        return new ResponseEntity<>(errorResponse, status);
    }

}
