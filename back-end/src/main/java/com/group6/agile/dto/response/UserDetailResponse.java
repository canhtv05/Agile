package com.group6.agile.dto.response;

import com.group6.agile.common.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetailResponse implements Serializable {

    String id;
    String firstName;
    String lastName;
    Gender gender;
    LocalDate dob;
    String email;
    String phone;
    String profilePicture;
    Instant createdAt;
    Instant updatedAt;
}
