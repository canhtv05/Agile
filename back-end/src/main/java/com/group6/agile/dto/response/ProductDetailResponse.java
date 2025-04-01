package com.group6.agile.dto.response;

import com.group6.agile.entity.Color;
import com.group6.agile.entity.OrderDetail;
import com.group6.agile.entity.Product;
import com.group6.agile.entity.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductDetailResponse {

    private String productName;


    private String imageUrl;

    private String colorName;


    private String sizeName;


    private Integer price;


    private Integer stockQuantity;


    private Integer soldQuantity;

}
