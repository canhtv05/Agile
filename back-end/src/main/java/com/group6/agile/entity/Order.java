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
@Table(name = "order")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order extends AbstractEntity<String> implements Serializable {

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails = new HashSet<>();
}
