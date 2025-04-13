package com.group6.agile.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public  class Review extends AbstractEntity<String> implements Serializable {
    @ManyToOne
    @JoinColumn(name = "productDetail_id")
    private ProductDetail productDetail;
    private Double star;
    private String comment;
}
