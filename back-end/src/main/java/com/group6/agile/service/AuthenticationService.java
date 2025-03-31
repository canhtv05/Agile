package com.group6.agile.service;

import com.group6.agile.dto.response.LoginResponse;
import com.group6.agile.dto.response.RefreshTokenResponse;
import com.group6.agile.dto.request.AuthenticationRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.text.ParseException;

public interface AuthenticationService {

    LoginResponse login(AuthenticationRequest request, HttpServletResponse response);

    RefreshTokenResponse refreshToken(String refreshToken) throws ParseException;

    void logout(String token, HttpServletResponse response) throws ParseException;
}
