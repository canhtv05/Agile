package com.group6.agile.configuration;

import com.group6.agile.entity.User;
import com.group6.agile.exception.AppException;
import com.group6.agile.exception.ErrorCode;
import com.group6.agile.service.CustomUserDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.ParseException;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtTokenValidator extends OncePerRequestFilter {

    TokenProvider tokenProvider;
    CustomUserDetailService customUserDetailService;
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        header = header.substring(7);

        String email = null;
        try {
            email = tokenProvider.verifyAndExtractEmail(header);
        } catch (ParseException e) {
            filterChain.doFilter(request, response);
            return;
        }

        if (email == null) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        User user = customUserDetailService.loadUserByUsername(email);

        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null,
                user.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}
