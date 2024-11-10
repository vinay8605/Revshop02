package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
	List<Product> findAllByOrderByIdAsc();
	
	// Find products by category
    List<Product> findByCategory(String category);

    // Get all unique categories from the products table
    @Query("SELECT DISTINCT p.category FROM Product p")
    List<String> findAllCategories();
}


