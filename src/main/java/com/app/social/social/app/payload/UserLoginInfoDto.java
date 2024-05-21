package com.app.social.social.app.payload;

import lombok.Data;

@Data
public class UserLoginInfoDto {
    private Long id;
    private String Name;
    private String username;
    private String email;
    private String token;
}
