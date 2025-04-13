package com.group6.agile.repository;

import com.group6.agile.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p FROM Product p " +
            "LEFT JOIN p.productDetails pd " +
            "WHERE p.category.id = :categoryId " +
            "GROUP BY p.id " +
            "ORDER BY " +
            "CASE WHEN :sortBy = 'price' AND :sortOrder = 'asc' THEN p.price END ASC, " +
            "CASE WHEN :sortBy = 'price' AND :sortOrder = 'desc' THEN p.price END DESC, " +
            "CASE WHEN :sortBy = 'name' AND :sortOrder = 'asc' THEN p.name END ASC, " +
            "CASE WHEN :sortBy = 'name' AND :sortOrder = 'desc' THEN p.name END DESC, " +
            "CASE WHEN :sortBy = 'popularity' AND :sortOrder = 'asc' THEN SUM(pd.soldQuantity) END ASC, " +
            "CASE WHEN :sortBy = 'popularity' AND :sortOrder = 'desc' THEN SUM(pd.soldQuantity) END DESC")
    List<Product> findByCategoryIdWithSorting(
            @Param("categoryId") String categoryId,
            @Param("sortBy") String sortBy,
            @Param("sortOrder") String sortOrder
    );
}
