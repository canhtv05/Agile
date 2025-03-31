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
@Table(name = "product")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends AbstractEntity<String> implements Serializable {

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category category;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "star")
    private Float star;

    @Column(name = "price")
    private Integer price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductDetail> productDetails = new HashSet<>();
}
