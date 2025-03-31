package com.group6.agile.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "cart_item")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItem extends AbstractEntity<String> implements Serializable {

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "product_detail_id")
    @ManyToOne
    private ProductDetail productDetail;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Integer price;
}
