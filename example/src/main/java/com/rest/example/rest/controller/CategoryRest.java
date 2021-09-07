package com.rest.example.rest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.rest.example.entity.Category;
import com.rest.example.repository.ICategoryRepository;
import com.rest.example.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryRest {
    
    @Autowired
    private ICategoryRepository categoryRepo;

    @Autowired
    private CategoryService service;

    /**
     * Fill category for test
     */
    @GetMapping("/fill_categories")
    public void fillCategories() {
        Arrays.asList(new Category("Congelados", new ArrayList<>()), new Category("Varios", new ArrayList<>()))
            .forEach(c -> categoryRepo.save(c));
    }

    /**
     * Get all categories
     * @return {@link ResponseEntity} {@link List} {@link Category}
     */
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return service.getCategories();
    }
}
