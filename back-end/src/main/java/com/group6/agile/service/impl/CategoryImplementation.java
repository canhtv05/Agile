package com.group6.agile.service.impl;

import com.group6.agile.dto.response.CategoryResponse;
import com.group6.agile.mapper.CategoryMapper;
import com.group6.agile.repository.CategoryRepository;
import com.group6.agile.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryImplementation implements CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();
    }

    @Override
    public List<CategoryResponse> findAllByCategoryName(String categoryName) {
        return categoryRepository.findAllByNameContainingIgnoreCase(categoryName).stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();
    }
}
