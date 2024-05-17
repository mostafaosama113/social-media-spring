package com.app.social.social.app.service.impl;

import com.app.social.social.app.entity.User;
import com.app.social.social.app.payload.RegisterDto;
import com.app.social.social.app.payload.UserLoginInfo;
import com.app.social.social.app.repositry.UserRepository;
import com.app.social.social.app.security.JwtProvider;
import com.app.social.social.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.app.social.social.app.payload.RegisterDto;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    public void register(RegisterDto model){
        User user = new User();
        user.setEmail(model.getEmail());
        user.setUsername(model.getUsername());
        user.setPassword(passwordEncoder.encode(model.getPassword()));
        user.setFirstName(model.getFirstName());
        user.setSecondName(model.getSecondName());
        userRepository.save(user);
    }
    public UserLoginInfo login(Authentication authentication){
        User user = (User)authentication.getPrincipal();
        UserLoginInfo loginInfo = new UserLoginInfo();
        loginInfo.setUsername(user.getUsername());
        loginInfo.setEmail(user.getEmail());
        loginInfo.setId(user.getId());
        loginInfo.setName(user.getFirstName() + " " + user.getSecondName());
        loginInfo.setToken(jwtProvider.createToken(authentication));
        return loginInfo;
    }

}
