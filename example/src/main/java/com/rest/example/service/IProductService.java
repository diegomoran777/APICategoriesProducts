package com.rest.example.service;

import java.util.List;

import com.rest.example.Dto.ProductDTO;
import com.rest.example.entity.Product;
import com.rest.example.exception.customException.NotFoundException;

import org.springframework.http.ResponseEntity;

public interface IProductService {
    
    ResponseEntity<List<Product>> getProducts();

    ResponseEntity<List<Product>> getProductsByType(String type);

    ResponseEntity<Product> getProductById(Long id) throws NotFoundException;

    ResponseEntity<List<Product>> getProductsByCategoryId(Long id);

    ResponseEntity<?> deleteProductById(Long id);

    ResponseEntity<Product> saveUpdateProduct(ProductDTO pDTO);
}
