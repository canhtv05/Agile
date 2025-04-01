package com.group6.agile.service;

import com.group6.agile.dto.response.ProductDetailResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductDetailService {
    List<ProductDetailResponse> getALl();
}
