package com.app.social.social.app.service.impl;

import com.app.social.social.app.entity.User;
import com.app.social.social.app.repositry.UserRepository;
import com.app.social.social.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.app.social.social.app.payload.LoginDto;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public void register(LoginDto model){
        User user = new User();
        user.setEmail(model.getEmail());
        user.setUsername(model.getUsername());
        user.setPassword(passwordEncoder.encode(model.getPassword()));
        user.setFirstName(model.getFirstName());
        user.setSecondName(model.getSecondName());
        userRepository.save(user);
    }


}
