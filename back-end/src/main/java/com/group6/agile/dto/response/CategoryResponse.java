package com.group6.agile.dto.response;

import java.time.Instant;
import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {

    private String id;
    private String name;
    private Instant createdAt;
    private Instant updatedAt;
    private List<ProductResponse> products;
}
