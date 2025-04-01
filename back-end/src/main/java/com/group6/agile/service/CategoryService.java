package com.group6.agile.service;

import com.group6.agile.dto.response.CategoryResponse;
import com.group6.agile.entity.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> findAll();

    List<CategoryResponse> findAllByCategoryName(String categoryName);

    CategoryResponse create(Category category);
    CategoryResponse update(String id,Category category);
    ResponseEntity<String> delete(String id);
}
