package com.revshop2.product.service;

import com.revshop2.product.dto.CategoryDTO;
import com.revshop2.product.entity.Category;
import com.revshop2.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Method to fetch a category with subcategories
    public CategoryDTO getCategoryWithSubcategories(String categoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return convertToCategoryDTO(category);
    }

    // Method to convert a Category entity to CategoryDTO
    private CategoryDTO convertToCategoryDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryId(category.getCategoryId());
        dto.setCategoryName(category.getCategoryName());

        // Recursively fetch subcategories
        List<CategoryDTO> subcategoriesDTO = category.getSubcategories().stream()
                .map(this::convertToCategoryDTO)
                .collect(Collectors.toList());
        dto.setSubcategories(subcategoriesDTO);

        return dto;
    }

    // Method to add a new category (with optional parent category)
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category parentCategory = null;
        if (categoryDTO.getParentCategory() != null) {
            parentCategory = categoryRepository.findByCategoryName(categoryDTO.getParentCategory().getCategoryName())
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
        }

        // Create new Category object
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setParentCategory(parentCategory);  // Set the parent category if available

        // Save the new category
        category = categoryRepository.save(category);

        // Map the saved Category entity to DTO and return
        CategoryDTO savedCategoryDTO = new CategoryDTO();
        savedCategoryDTO.setCategoryId(category.getCategoryId());
        savedCategoryDTO.setCategoryName(category.getCategoryName());

        // If the category has a parent category, set it in the DTO
        if (parentCategory != null) {
            CategoryDTO parentDTO = new CategoryDTO();
            parentDTO.setCategoryId(parentCategory.getCategoryId());
            parentDTO.setCategoryName(parentCategory.getCategoryName());
            savedCategoryDTO.setParentCategory(parentDTO);
        }

        return savedCategoryDTO;
    }
}
