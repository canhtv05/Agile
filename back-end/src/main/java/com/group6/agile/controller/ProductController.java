package com.group6.agile.controller;

import com.group6.agile.dto.response.ProductResponse;
import com.group6.agile.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {

    ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(
            @RequestParam String categoryId,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortOrder) {
        try {
            List<ProductResponse> products = productService.getProductsByCategory(categoryId, sortBy, sortOrder);
            return ResponseEntity.ok(products);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
