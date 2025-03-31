package com.group6.agile.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "product_detail")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetail extends AbstractEntity<String> implements Serializable {

    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;

    @Column(name = "image_url")
    private String imageUrl;

    @JoinColumn(name = "color_id")
    @ManyToOne
    private Color color;

    @JoinColumn(name = "size_id")
    @ManyToOne
    private Size size;

    @Column(name = "price")
    private Integer price;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name = "sold_quantity")
    private Integer soldQuantity;

    @OneToMany(mappedBy = "productDetail", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails = new HashSet<>();
}
