package com.group6.agile.mapper;

import com.group6.agile.dto.response.ProductResponse;
import com.group6.agile.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toProductResponse(Product product);
}
