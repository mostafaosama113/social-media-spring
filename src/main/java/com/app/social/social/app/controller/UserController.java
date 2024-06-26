package com.app.social.social.app.controller;

import com.app.social.social.app.config.GlobalExceptionHandler;
import com.app.social.social.app.payload.LoginDto;
import com.app.social.social.app.payload.RegisterDto;
import com.app.social.social.app.payload.UserLoginInfoDto;
import com.app.social.social.app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<UserLoginInfoDto> register(@Valid  @RequestBody(required = false) RegisterDto model , BindingResult bindingResult){
        GlobalExceptionHandler.checkAndFire(model , bindingResult);
        return new ResponseEntity<>(userService.register(model) , HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<UserLoginInfoDto> register(@Valid @RequestBody(required = false) LoginDto model , BindingResult bindingResult){
        GlobalExceptionHandler.checkAndFire(model , bindingResult);
        return new ResponseEntity<>(userService.login(model) , HttpStatus.OK);
    }

}
