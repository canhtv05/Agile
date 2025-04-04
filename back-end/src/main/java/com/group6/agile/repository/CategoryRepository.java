package com.group6.agile.repository;

import com.group6.agile.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    List<Category> findAllByNameContainingIgnoreCase(String name);
}
