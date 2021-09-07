package com.rest.example.repository;

import java.util.Optional;

import com.rest.example.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    
    @Transactional(readOnly = true)
    Category findByName(String name);

    @Transactional(readOnly = true)
    Optional<Category> findById(Long id);
    
}
