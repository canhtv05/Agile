package com.group6.agile.controller;

import com.group6.agile.dto.ApiResponse;
import com.group6.agile.dto.response.CategoryResponse;
import com.group6.agile.entity.Category;
import com.group6.agile.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> create(@RequestBody Category categoryRequest){
        return ResponseEntity.ok(categoryService.create(categoryRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable("id")String id,@RequestBody Category categoryRequest){
        return ResponseEntity.ok(categoryService.update(id,categoryRequest));
    }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> delete(@PathVariable("id") String id){
        return categoryService.delete(id);
    }



}

