package com.app.social.social.app.controller;

import com.app.social.social.app.payload.RegisterDto;
import com.app.social.social.app.payload.UserLoginInfo;
import com.app.social.social.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public void createUser(@RequestBody RegisterDto dto) {
        userService.register(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginInfo> login(Authentication authentication) {
        return ResponseEntity.ok(userService.login(authentication));
    }
}
