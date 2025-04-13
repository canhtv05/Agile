package com.group6.agile.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "coupons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coupon extends AbstractEntity<String> implements Serializable {
    private Double discountValue;

    private Integer usageLimit;

    private LocalDateTime validTo;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime validFrom;

    private Boolean isActive = true;
}
