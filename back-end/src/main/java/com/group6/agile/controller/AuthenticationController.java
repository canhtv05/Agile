package com.group6.agile.controller;


import com.group6.agile.common.Meta;
import com.group6.agile.dto.ApiResponse;
import com.group6.agile.dto.response.LoginResponse;
import com.group6.agile.dto.response.UserDetailResponse;
import com.group6.agile.dto.request.AuthenticationRequest;
import com.group6.agile.dto.request.UserCreationRequest;
import com.group6.agile.dto.request.UserUpdateRequest;
import com.group6.agile.entity.User;
import com.group6.agile.mapper.UserMapper;
import com.group6.agile.service.AuthenticationService;
import com.group6.agile.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;
    UserMapper userMapper;
    UserService userService;

    @PostMapping("/register")
    public ApiResponse<UserDetailResponse> register(@RequestBody @Valid UserCreationRequest request) {
        var user = userService.createUser(request);

        return ApiResponse.<UserDetailResponse>builder()
                .message("User registered!")
                .data(user)
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody AuthenticationRequest request, HttpServletResponse response){
        LoginResponse loginResponse = authenticationService.login(request, response);
        User user = userService.findUserById(loginResponse.getUserId());
        UserDetailResponse userResponse = userMapper.toUserResponse(user);
        loginResponse.setUserId(null);

        Meta<LoginResponse> meta = Meta.<LoginResponse>builder()
                .tokenInfo(loginResponse)
                .build();

        return ApiResponse.builder()
                .message("Login successful!")
                .meta(meta)
                .data(userResponse)
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, HttpServletResponse response) throws ParseException{
        authenticationService.logout(token, response);
        return ApiResponse.<Void>builder()
                .message("Logout successful!")
                .build();
    }

    @PostMapping("/refresh-token")
    public ApiResponse<?> refreshToken(@CookieValue(name = "refreshToken") String refreshToken) throws ParseException {
        var data = authenticationService.refreshToken(refreshToken);
        return ApiResponse.builder()
                .message("Refresh token successful!")
                .data(data)
                .build();
    }

    @GetMapping("/me")
    public ApiResponse<UserDetailResponse> getMyInfo() {
        var user = userService.getCurrentUser();

        return ApiResponse.<UserDetailResponse>builder()
                .message("User found!")
                .data(userMapper.toUserResponse(user))
                .build();
    }

    @PatchMapping("/me/update")
    public ApiResponse<UserDetailResponse> updateUser(@Valid @RequestBody UserUpdateRequest request) {
        var user = userService.getCurrentUser();

        return ApiResponse.<UserDetailResponse>builder()
                .message("User updated!")
                .data(userService.updateUser(user.getId(), request))
                .build();
    }
}
