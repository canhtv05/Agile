package com.group6.agile.service.impl;

import com.group6.agile.dto.request.ReviewsRequest;
import com.group6.agile.dto.response.ProductDetailResponse;
import com.group6.agile.dto.response.ReviewResponse;
import com.group6.agile.entity.ProductDetail;
import com.group6.agile.entity.Review;
import com.group6.agile.repository.ProductDetailRepository;
import com.group6.agile.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewsService {
    private final ReviewRepository reviewRepository;
    private final ProductDetailRepository productDetailRepository;
    public ReviewResponse create(ReviewsRequest reviewsRequest){
        ProductDetail productDetail=productDetailRepository.findById(reviewsRequest.getId()).orElseThrow();
        Review review1=new Review();
        review1.setStar(reviewsRequest.getStar());
        review1.setComment(reviewsRequest.getComment());
        review1.setProductDetail(productDetail);
        Review save=reviewRepository.save(review1);
        return mapToResponse(save);
    }
    public ReviewResponse update(String id,ReviewsRequest reviewsRequest){
        Review review2=reviewRepository.findById(id).orElseThrow();
        ProductDetail productDetail=productDetailRepository.findById(reviewsRequest.getId()).orElseThrow();
        System.out.println(id);
        review2.setComment(reviewsRequest.getComment());
        review2.setStar(reviewsRequest.getStar());
        review2.setProductDetail(productDetail);
        Review save=reviewRepository.save(review2);
        return mapToResponse(save);
    }
    public ReviewResponse mapToResponse(Review review){
        ReviewResponse reviewResponse=new ReviewResponse();
        reviewResponse.setComment(review.getComment());
        reviewResponse.setStar(review.getStar());
        reviewResponse.setProductDetailId(review.getProductDetail().getId());
        return reviewResponse;
    }
}
