package com.group6.agile.service;

import com.group6.agile.entity.User;
import com.group6.agile.exception.AppException;
import com.group6.agile.exception.ErrorCode;
import com.group6.agile.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomUserDetailService implements UserDetailsService {

    UserRepository userRepository;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_EMAIL_OR_PASSWORD));
    }
}
