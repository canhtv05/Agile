package com.group6.agile.service.impl;

import com.group6.agile.configuration.TokenProvider;
import com.group6.agile.dto.response.LoginResponse;
import com.group6.agile.dto.response.RefreshTokenResponse;
import com.group6.agile.dto.request.AuthenticationRequest;
import com.group6.agile.entity.User;
import com.group6.agile.exception.AppException;
import com.group6.agile.exception.ErrorCode;
import com.group6.agile.repository.UserRepository;
import com.group6.agile.service.AuthenticationService;
import com.group6.agile.service.RedisService;
import com.group6.agile.util.JwtUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImplementation implements AuthenticationService {

    UserRepository userRepository;
    TokenProvider tokenProvider;
    AuthenticationManager authenticationManager;
    JwtUtil jwtUtil;
    RedisService redisService;

    @Override
    public LoginResponse login(AuthenticationRequest request, HttpServletResponse response) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            // not found account
        } catch (InternalAuthenticationServiceException | BadCredentialsException e) {
            throw new AppException(ErrorCode.INVALID_EMAIL_OR_PASSWORD);
        }

        User user = (User) authentication.getPrincipal();

        String accessToken = tokenProvider.generateAccessToken(user);
        String refreshToken = tokenProvider.generateRefreshToken(user);
        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(jwtUtil.getRefreshableDuration().intValue()); // 2 weeks
        cookie.setPath("/");
        cookie.setSecure(true); // true nếu chỉ cho gửi qua HTTPS
        cookie.setDomain("localhost");

        response.addCookie(cookie);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .accessTokenTTL(jwtUtil.getValidDuration())
                .refreshTokenTTL(jwtUtil.getRefreshableDuration())
                .refreshToken(refreshToken)
                .userId(user.getId())
                .build();
    }


    @Override
    public void logout(String accessToken, HttpServletResponse response) throws ParseException {
        String email = tokenProvider.verifyAndExtractEmail(accessToken);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        long accessTokenExpired = tokenProvider.verifyAndExtractTokenExpired(accessToken);
        long currentTime = System.currentTimeMillis();

        // con han
        if (currentTime < accessTokenExpired) {
            try {
                String jwtId = tokenProvider.verifyToken(accessToken).getJWTClaimsSet().getJWTID();

                long ttl = accessTokenExpired - currentTime;
                redisService.save(jwtId, accessToken, ttl, TimeUnit.MILLISECONDS);

                user.setRefreshToken(null);
                userRepository.save(user);

                deleteRefreshToken(response);

                SecurityContextHolder.clearContext();
            } catch (ParseException e) {
                throw new AppException(ErrorCode.INVALID_TOKEN);
            }
        }
    }

    @Override
    public RefreshTokenResponse refreshToken(String refreshToken) throws ParseException {
        if (StringUtils.isBlank(refreshToken)) {
            throw new AppException(ErrorCode.REFRESH_TOKEN_INVALID);
        }

        String email = tokenProvider.verifyAndExtractEmail(refreshToken);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (!Objects.equals(user.getRefreshToken(), refreshToken) || StringUtils.isBlank(user.getRefreshToken())) {
            throw new AppException(ErrorCode.REFRESH_TOKEN_INVALID);
        }

        var signJWT = tokenProvider.verifyToken(refreshToken);
        if (Objects.isNull(signJWT)) {
            throw new AppException(ErrorCode.REFRESH_TOKEN_INVALID);
        }

        String accessToken = tokenProvider.generateAccessToken(user);
        String generateRefreshToken = tokenProvider.generateRefreshToken(user);
        return RefreshTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(generateRefreshToken)
                .build();
    }

    private void deleteRefreshToken(HttpServletResponse response) {
        Cookie cookie = new Cookie("refreshToken", "");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setDomain("localhost");
        response.addCookie(cookie);
    }
}
