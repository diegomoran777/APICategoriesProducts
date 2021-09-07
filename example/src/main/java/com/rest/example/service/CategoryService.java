package com.rest.example.service;

import java.util.List;
import java.util.Optional;

import com.rest.example.entity.Category;
import com.rest.example.repository.ICategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepo;

    @Override
    public ResponseEntity<Category> getCategoryById(Long id) {

        try {
            Optional<Category> category = categoryRepo.findById(id);
            return ResponseEntity.of(category);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Category> getCategoryByName(String name) {

        try {
            Category category = categoryRepo.findByName(name);

            return category != null ?
                ResponseEntity.status(HttpStatus.OK).body(category) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Category> saveUpdateCategory(Category category) {

        try {
            Category c = categoryRepo.save(category);

            return c.getId() == null ? 
                ResponseEntity.status(HttpStatus.CREATED).body(c) :
                ResponseEntity.status(HttpStatus.OK).body(c);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }

    @Override
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryRepo.findAll();

        return categories.isEmpty() ? 
            ResponseEntity.status(HttpStatus.NO_CONTENT).body(categories) :
            ResponseEntity.ok().body(categories);
    }

}
