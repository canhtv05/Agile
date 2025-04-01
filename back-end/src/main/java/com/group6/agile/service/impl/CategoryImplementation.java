package com.group6.agile.service.impl;

import com.group6.agile.dto.response.CategoryResponse;
import com.group6.agile.entity.Category;
import com.group6.agile.mapper.CategoryMapper;
import com.group6.agile.repository.CategoryRepository;
import com.group6.agile.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public  class CategoryImplementation implements CategoryService {

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

    @Override
    public CategoryResponse create(Category categoryRequest) {
        Category category1=new Category();
        category1.setName(categoryRequest.getName());
        categoryRepository.save(category1);
        return categoryMapper.toCategoryResponse(category1);
    }

    @Override
    public CategoryResponse update(String id,Category categoryRequest) {
        Category category=categoryRepository.findById(id)
                .orElseThrow(()->new RuntimeException("ko có id category nay"));
        category.setName(categoryRequest.getName());
        categoryRepository.save(category);
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public ResponseEntity<String> delete(String id) {
        Category category=categoryRepository.findById(id).orElse(null);
        if(category==null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ko có id: "+id+" này");
        }
        categoryRepository.delete(category);
        return ResponseEntity.ok().body("delete thành công");
    }

}
