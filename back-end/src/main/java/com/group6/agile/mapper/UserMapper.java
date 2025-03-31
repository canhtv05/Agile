package com.group6.agile.mapper;

import com.group6.agile.dto.response.UserDetailResponse;
import com.group6.agile.dto.response.UserResponse;
import com.group6.agile.dto.request.UserCreationRequest;
import com.group6.agile.dto.request.UserUpdateRequest;
import com.group6.agile.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "userStatus", ignore = true)
    User toUserRequest(UserCreationRequest request);

    @Mapping(target = "userStatus", ignore = true)
    @Mapping(target = "email", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromRequest(UserUpdateRequest request, @MappingTarget User user);

    UserDetailResponse toUserResponse(User user);

    UserResponse toUser(User user);
}