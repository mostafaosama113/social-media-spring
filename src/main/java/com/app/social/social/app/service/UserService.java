package com.app.social.social.app.service;


import com.app.social.social.app.payload.RegisterDto;
import com.app.social.social.app.payload.UserLoginInfoDto;

import java.util.Map;

public interface UserService {
    UserLoginInfoDto register(RegisterDto model);

}
