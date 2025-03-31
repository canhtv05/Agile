package com.group6.agile.service;

import com.group6.agile.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> findAll();

    List<CategoryResponse> findAllByCategoryName(String categoryName);
}
