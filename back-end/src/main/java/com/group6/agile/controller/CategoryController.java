package com.group6.agile.controller;

import com.group6.agile.dto.ApiResponse;
import com.group6.agile.dto.response.CategoryResponse;
import com.group6.agile.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {

    CategoryService categoryService;

    @GetMapping
    public ApiResponse<List<CategoryResponse>> findAll() {
        List<CategoryResponse> categories = categoryService.findAll();
        return ApiResponse.<List<CategoryResponse>>builder()
                .data(categories)
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<CategoryResponse>> findAllByCategoryName(@RequestParam(value = "q", required = false) String query) {
        List<CategoryResponse> categories = categoryService.findAllByCategoryName(query);
        return ApiResponse.<List<CategoryResponse>>builder()
                .data(categories)
                .build();
    }
}
