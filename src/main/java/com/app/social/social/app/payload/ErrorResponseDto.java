package com.app.social.social.app.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private String error;
    private String message;
    private int status;
    private Date timestamp;
}
