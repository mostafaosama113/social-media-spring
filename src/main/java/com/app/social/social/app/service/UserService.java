package com.app.social.social.app.service;


import com.app.social.social.app.payload.RegisterDto;
import com.app.social.social.app.payload.UserLoginInfo;
import org.springframework.security.core.Authentication;

import java.util.Map;

public interface UserService {
    void register(RegisterDto model);

    UserLoginInfo login(Authentication authentication);
}
