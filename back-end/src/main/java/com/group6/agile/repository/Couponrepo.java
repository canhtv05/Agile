package com.group6.agile.repository;

import com.group6.agile.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Couponrepo extends JpaRepository<Coupon, String> {
}
