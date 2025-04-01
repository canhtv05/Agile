package com.group6.agile.mapper;

import com.group6.agile.dto.response.ProductDetailResponse;
import com.group6.agile.entity.Product;
import com.group6.agile.entity.ProductDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductDetailMapper {
    @Mapping(source ="product.name",target = "productName")
    @Mapping(source ="color.name",target = "colorName")
    @Mapping(source ="size.name",target = "sizeName")

    ProductDetailResponse toProductDetailResponse(ProductDetail productDetails);
}
