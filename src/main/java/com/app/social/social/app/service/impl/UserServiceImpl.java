package com.app.social.social.app.service.impl;


import com.app.social.social.app.entity.User;
import com.app.social.social.app.exception.ResourceExistsException;
import com.app.social.social.app.exception.ResourceNotFoundException;
import com.app.social.social.app.exception.WrongPasswordException;
import com.app.social.social.app.payload.LoginDto;
import com.app.social.social.app.payload.RegisterDto;
import com.app.social.social.app.payload.UserLoginInfoDto;
import com.app.social.social.app.repositry.UserRepository;
import com.app.social.social.app.security.JwtProvider;
import com.app.social.social.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final AuthenticationManager authenticationManager;
    @Override
    public UserLoginInfoDto register(RegisterDto model) {
        if(userRepository.existsByUsername(model.getUsername()) ||userRepository.existsByEmail(model.getEmail())){
            throw new ResourceExistsException("Username or Email already registered");
        }
        User user = new User();
        user.setUsername(model.getUsername());
        user.setEmail(model.getEmail());
        user.setPassword(passwordEncoder.encode(model.getPassword()));
        user.setFirstName(model.getFirstName());
        user.setSecondName(model.getSecondName());
        userRepository.save(user);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                model.getUsername(),
                model.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(user);
        UserLoginInfoDto userLoginInfoDto = new UserLoginInfoDto();
        userLoginInfoDto.setUsername(model.getUsername());
        userLoginInfoDto.setToken(token);
        userLoginInfoDto.setEmail(model.getEmail());
        userLoginInfoDto.setId(user.getId());
        userLoginInfoDto.setName(user.getFirstName() + " "+ user.getSecondName());
        return userLoginInfoDto;
    }
    @Override
    public UserLoginInfoDto login(LoginDto model) {
        User user = userRepository.findByUsername(model.getUsername()).orElseThrow(
                () -> new ResourceNotFoundException("User" , "username" , model.getUsername())
        );
        if (!passwordEncoder.matches(model.getPassword(), user.getPassword())) {
            throw new WrongPasswordException();
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                model.getUsername(),
                model.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(user);
        UserLoginInfoDto userLoginInfoDto = new UserLoginInfoDto();
        userLoginInfoDto.setUsername(model.getUsername());
        userLoginInfoDto.setToken(token);
        userLoginInfoDto.setEmail(user.getEmail());
        userLoginInfoDto.setId(user.getId());
        userLoginInfoDto.setName(user.getFirstName() + " " + user.getSecondName());
        return userLoginInfoDto;
    }
}
