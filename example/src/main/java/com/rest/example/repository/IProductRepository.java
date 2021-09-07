package com.rest.example.repository;

import java.util.List;
import java.util.Optional;

import com.rest.example.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    
    @Transactional(readOnly = true)
    List<Product> findByType(String type);

    @Transactional(readOnly = true)
    Optional<Product> findById(Long id);

}
