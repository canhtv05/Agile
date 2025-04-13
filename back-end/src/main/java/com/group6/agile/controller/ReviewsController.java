package com.group6.agile.controller;

import com.group6.agile.dto.request.ReviewsRequest;
import com.group6.agile.dto.response.ReviewResponse;
import com.group6.agile.entity.Review;
import com.group6.agile.service.impl.ReviewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewsController {
    private final ReviewsService reviewsService;
    @PutMapping("/update/{id}")
    public ReviewResponse update(@PathVariable("id") String id, @RequestBody ReviewsRequest reviewsRequest){
        return reviewsService.update(id, reviewsRequest);
    }
    @PostMapping("/create")
    public ReviewResponse createReviews(@RequestBody ReviewsRequest reviewsRequest){
        return reviewsService.create(reviewsRequest);
    }
}
