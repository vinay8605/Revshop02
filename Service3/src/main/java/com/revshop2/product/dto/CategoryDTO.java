package com.revshop2.product.dto;

import java.util.List;

public class CategoryDTO {
    private Integer categoryId;
    private String categoryName;
    private CategoryDTO parentCategory;  // Representing the parent category
    private List<CategoryDTO> subcategories; // List of subcategories

    // Getters and Setters
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryDTO getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryDTO parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<CategoryDTO> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<CategoryDTO> subcategories) {
        this.subcategories = subcategories;
    }
}
