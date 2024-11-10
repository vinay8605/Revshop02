package com.revshop2.product.controller;

import com.revshop2.product.entity.Product;
import com.revshop2.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

   @Autowired
   private ProductService productService;
//
//    @PostMapping
//    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
//        Product savedProduct = productService.saveProduct(product);
//        return ResponseEntity.ok(savedProduct);
//    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
//        Product product = productService.getProductById(id);
//        return ResponseEntity.ok(product);
//    }
//
//    @GetMapping("/category/{category}")
//    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
//        List<Product> products = productService.getProductsByCategory(category);
//        return ResponseEntity.ok(products);
//    }
}
