package com.group6.agile.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "color")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Color extends AbstractEntity<String> implements Serializable {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "color")
    private Set<ProductDetail> productDetails = new HashSet<>();
}
