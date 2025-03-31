package com.group6.agile.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.group6.agile.common.Meta;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ApiResponse<T> {

    @Builder.Default
    Integer code = 200;
    @Builder.Default
    String message = "Success";
    T data;

    Meta meta;
}
