package com.app.social.social.app.service.impl;


import com.app.social.social.app.entity.User;
import com.app.social.social.app.payload.RegisterDto;
import com.app.social.social.app.payload.UserLoginInfoDto;
import com.app.social.social.app.repositry.UserRepository;
import com.app.social.social.app.security.JwtProvider;
import com.app.social.social.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public UserLoginInfoDto register(RegisterDto model) {
        //todo: check if the email or username are already exists.
        User user = new User();
        user.setUsername(model.getUsername());
        user.setEmail(model.getEmail());
        user.setPassword(passwordEncoder.encode(model.getPassword()));
        user.setFirstName(model.getFirstName());
        user.setSecondName(model.getSecondName());
        userRepository.save(user);
        String token = jwtProvider.generateToken(user);
        UserLoginInfoDto userLoginInfoDto = new UserLoginInfoDto();
        userLoginInfoDto.setUsername(model.getUsername());
        userLoginInfoDto.setToken(token);
        userLoginInfoDto.setEmail(model.getEmail());
        userLoginInfoDto.setId(user.getId());
        userLoginInfoDto.setName(user.getFirstName() + " "+ user.getSecondName());
        return userLoginInfoDto;
    }
}
