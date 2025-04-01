package com.group6.agile.service;

import com.group6.agile.dto.response.ProductResponse;
import com.group6.agile.entity.Product;
import com.group6.agile.mapper.ProductMapper;
import com.group6.agile.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {

    ProductRepository productRepository;
    ProductMapper productMapper;

    public List<ProductResponse> getProductsByCategory(String categoryId, String sortBy, String sortOrder) {
        // Xác thực tham số
        if (categoryId == null || categoryId.isEmpty()) {
            throw new IllegalArgumentException("Category ID is required");
        }

        // Chuẩn hóa sortBy và sortOrder
        String sortByValue = (sortBy == null || sortBy.isEmpty()) ? "name" : sortBy.toLowerCase();
        String sortOrderValue = (sortOrder == null || sortOrder.isEmpty()) ? "asc" : sortOrder.toLowerCase();

        if (!sortByValue.equals("price") && !sortByValue.equals("name") && !sortByValue.equals("popularity")) {
            sortByValue = "name";
        }

        if (!sortOrderValue.equals("asc") && !sortOrderValue.equals("desc")) {
            sortOrderValue = "asc";
        }

        // Lấy danh sách sản phẩm
        List<Product> products = productRepository.findByCategoryIdWithSorting(categoryId, sortByValue, sortOrderValue);

        // Chuyển đổi sang DTO
        return products.stream().map(productMapper::toProductResponse).toList();
    }
}
