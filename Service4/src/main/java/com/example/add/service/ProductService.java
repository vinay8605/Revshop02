package com.example.add.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Product;
import com.example.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
  

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAllByOrderByIdAsc();
    }

    // Get product by ID
    public Optional<Product> getProductById(Integer productId) {
        return productRepository.findById(productId);
    }

    // Delete a product
    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }
    // Get products by category
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    // Add category to all products in that category
    public void addCategory(String category) {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            if (product.getCategory().equals(category)) {
                continue; // Skip if the category already exists
            }
            product.setCategory(category); // Set the category for this product
            productRepository.save(product); // Save the updated product
        }
    }

    // Remove category from all products
    public void removeCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);
        for (Product product : products) {
            product.setCategory(null);  // Set category to null or empty
            productRepository.save(product);
        }
    }

    // Get all unique categories from the products
    public List<String> getAllCategories() {
        return productRepository.findAllCategories();
    }
}
