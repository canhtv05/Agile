package com.group6.agile.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private Float star;
    private Integer price;
    private Instant createdAt;
    private Instant updatedAt;
}
