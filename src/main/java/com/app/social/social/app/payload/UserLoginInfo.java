package com.app.social.social.app.payload;

import lombok.Data;

@Data
public class UserLoginInfo {
    private Long id;
    private String username;
    private String email;
    private String Name;
    private String token;
}
