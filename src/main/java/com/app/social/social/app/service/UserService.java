package com.app.social.social.app.service;


import com.app.social.social.app.payload.LoginDto;

public interface UserService {
    void register(LoginDto model);
}
