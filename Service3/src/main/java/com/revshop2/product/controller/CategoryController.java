package com.revshop2.product.controller;

import com.revshop2.product.dto.CategoryDTO;
import com.revshop2.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Fetch category and its subcategories
    @GetMapping("/{categoryName}/subcategories")
    public ResponseEntity<CategoryDTO> getCategoryWithSubcategories(@PathVariable String categoryName) {
        CategoryDTO category = categoryService.getCategoryWithSubcategories(categoryName);
        return ResponseEntity.ok(category);
    }

    // Add a new category (with optional parent category)
    @PostMapping
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedCategory = categoryService.addCategory(categoryDTO);
        return ResponseEntity.ok(savedCategory);
    }
}

