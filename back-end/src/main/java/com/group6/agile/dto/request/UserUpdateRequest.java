package com.group6.agile.dto.request;

import com.group6.agile.common.Gender;
import com.group6.agile.validator.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest implements Serializable {

    @Size(min = 3, message = "INVALID_FIRST_NAME")
    String firstName;

    @Size(min = 3, message = "INVALID_LAST_NAME")
    String lastName;

    Gender gender;

    @DobConstraint(min = 7, message = "INVALID_DOB")
    LocalDate dob;

    @Size(min = 10, message = "INVALID_PHONE")
    String phone;

    @Size(min = 3, message = "INVALID_PASSWORD")
    String password;

    String profilePicture;
}
