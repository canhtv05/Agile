package com.group6.agile.service.impl;

import com.group6.agile.entity.Coupon;
import com.group6.agile.repository.Couponrepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class Couponservice {
    private final Couponrepo couponrepo;

    public Coupon  create (Coupon coupon) {
        Coupon coupon1=new Coupon();
        coupon1.setDiscountValue(coupon.getDiscountValue());
        coupon1.setValidTo(LocalDateTime.now().plusDays(30));

        coupon1.setIsActive(true);
        coupon1.setUsageLimit(coupon.getUsageLimit());
       return couponrepo.save(coupon1);
    }
}
