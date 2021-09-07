package com.rest.example.service;


import java.util.List;

import com.rest.example.entity.Category;

import org.springframework.http.ResponseEntity;

public interface ICategoryService {
    
    ResponseEntity<Category> getCategoryById(Long id);

    ResponseEntity<Category> getCategoryByName(String name);

    ResponseEntity<Category> saveUpdateCategory(Category category);

    ResponseEntity<List<Category>> getCategories();
}
