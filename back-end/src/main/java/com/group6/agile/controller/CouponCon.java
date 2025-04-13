package com.group6.agile.controller;

import com.group6.agile.entity.Coupon;
import com.group6.agile.service.impl.Couponservice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon")
@RequiredArgsConstructor
public class CouponCon {
    private final Couponservice couponservice;
    @GetMapping("/tet")
    public String string(){
        return "hi";
    }
    @PostMapping("/create")
    public Coupon create (@RequestBody Coupon coupon) {
        return couponservice.create(coupon);
    }
}
