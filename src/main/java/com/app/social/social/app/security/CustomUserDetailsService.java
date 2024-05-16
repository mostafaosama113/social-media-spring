package com.app.social.social.app.security;

import com.app.social.social.app.entity.User;
import com.app.social.social.app.repositry.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!userRepository.existsByUsername(username)) {
            throw new UsernameNotFoundException("Username not exist!");
        }
        User user = userRepository.findByUsername(username).get();
        return user;
    }
}
