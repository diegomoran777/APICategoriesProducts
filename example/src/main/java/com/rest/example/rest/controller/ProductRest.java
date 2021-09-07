package com.rest.example.rest.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import com.rest.example.Dto.ProductDTO;
import com.rest.example.entity.Product;
import com.rest.example.exception.customException.NotFoundException;
import com.rest.example.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductRest {

    @Autowired
    private ProductService productService;

    /**
     * Fill products for test
     * @return {@link Void}
     */
    @GetMapping("fill_products")
    public void fillProducts() {
        Arrays.asList(new ProductDTO("Fideos", "Almacen", 1L), new ProductDTO("Atun", "Enlatados", 1L), 
            new ProductDTO("Carne", "Frescos", 2L), new ProductDTO("Pan", "Almacen", 2L))
                .forEach(p -> productService.saveUpdateProduct(p)); 
    }

    /**
     * Endpoint that get all products from all categories
     * @return {@link ResponseEntity} {@link List} {@link Product}
     */
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return productService.getProducts();
    }

    /**
     * Get product by id
     * @param id {@link Long}
     * @exception NotFoundException
     * @return {@link ResponseEntity} {@link Product}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    /**
     * Get all products by type 
     * @param type {@link String}
     * @return {@link ResponseEntity} {@link List} {@link Product}
     */
    @GetMapping("/products_type/{type}")
    public ResponseEntity<List<Product>> getProductsByType(@PathVariable String type) {
        return productService.getProductsByType(type);
    }

    /**
     * Get all products by category id
     * @param id {@link Long}
     * @return {@link ResponseEntity} {@link List} {@link Product}
     */
    @GetMapping("/products/category/{id}")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable long id) {
        return productService.getProductsByCategoryId(id);
    }

    /**
     * Delete a product by id
     * @param id {@link Long}
     * @param productDTO
     * @return {@link ResponseEntity} 
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductByid(@PathVariable long id) {
        return productService.deleteProductById(id);
    }

    /**
     * Save or update a product depends if it has or don t has id
     * @param RequestBody {@link ProductDTO}
     * @return {@link ResponseEntity} {@link Product}
     */
    @PostMapping("/save_update")
    public ResponseEntity<Product> saveUpdateProduct(@Valid @RequestBody ProductDTO productDTO) {
        return productService.saveUpdateProduct(productDTO);
    }

}
