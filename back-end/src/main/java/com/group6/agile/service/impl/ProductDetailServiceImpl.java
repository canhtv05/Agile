package com.group6.agile.service.impl;

import com.group6.agile.dto.response.ProductDetailResponse;
import com.group6.agile.entity.ProductDetail;
import com.group6.agile.mapper.ProductDetailMapper;
import com.group6.agile.repository.ProductDetailRepository;
import com.group6.agile.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService {
    private final ProductDetailRepository productDetailRepository;
    private final ProductDetailMapper productDetailMapper;

    public List<ProductDetailResponse> getALl(){
        List<ProductDetail> productDetails = productDetailRepository.findAll();
        return productDetails.stream().map(p->productDetailMapper.toProductDetailResponse(p))
                .collect(Collectors.toList());
    }
}
