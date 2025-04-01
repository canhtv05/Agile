package com.group6.agile.controller;

import com.group6.agile.dto.response.ProductDetailResponse;
import com.group6.agile.entity.ProductDetail;
import com.group6.agile.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/productdetails")
public class ProductDetailController {
    private final ProductDetailService productDetailService;

    @GetMapping()
    ResponseEntity<List<ProductDetailResponse>> getAllProductDetails() {
        return ResponseEntity.ok(productDetailService.getALl());
    }

}
