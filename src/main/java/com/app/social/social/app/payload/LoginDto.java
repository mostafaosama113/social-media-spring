package com.app.social.social.app.payload;

import lombok.Data;

@Data
public class LoginDto {
    private String firstName;
    private String secondName;
    private String username;
    private String email;
    private String password;
}
