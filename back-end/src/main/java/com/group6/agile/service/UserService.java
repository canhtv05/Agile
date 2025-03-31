package com.group6.agile.service;

import com.group6.agile.dto.response.UserDetailResponse;
import com.group6.agile.dto.response.UserResponse;
import com.group6.agile.dto.request.UserCreationRequest;
import com.group6.agile.dto.request.UserUpdateRequest;
import com.group6.agile.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {

    UserDetailResponse createUser(UserCreationRequest request);

    User findUserById(String id);

    User getCurrentUser();

    UserDetailResponse updateUser(String userId, UserUpdateRequest request);

    Page<UserResponse> searchUserByFullNameOrEmail(String query, int page, int size);
}
