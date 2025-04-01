package com.group6.agile.mapper;

import com.group6.agile.dto.response.CategoryResponse;
import com.group6.agile.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toCategoryResponse(Category category);
}
