package com.rest.example.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.rest.example.Dto.ProductDTO;
import com.rest.example.entity.Category;
import com.rest.example.entity.Product;
import com.rest.example.exception.customException.NotFoundException;
import com.rest.example.repository.ICategoryRepository;
import com.rest.example.repository.IProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepo;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ICategoryRepository categoryRepo;

    @Override
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productRepo.findAll();

        return products.isEmpty() ? 
            ResponseEntity.status(HttpStatus.NO_CONTENT).body(products) :
            ResponseEntity.ok().body(products);
    }

    @Override
    public ResponseEntity<List<Product>> getProductsByType(String type) {

        try {
            List<Product> products = productRepo.findByType(type);
            
            return products.isEmpty() ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() : 
                ResponseEntity.ok().body(products);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Product> getProductById(Long id) throws NotFoundException {

            Optional<Product> product = productRepo.findById(id);
            
            return ResponseEntity.status(HttpStatus.OK)
                .body(product.orElseThrow(()-> new NotFoundException("USER NOT FOUND")));
            
            //Otras formas dependiendo la necesidad:
            /* if(product.isEmpty())
                throw new NotFoundException("USER NOT FOUND");
            return ResponseEntity.status(HttpStatus.OK).body(product.get()); */

        /* return product.isEmpty() ? 
                ResponseEntity.status(HttpStatus.NOT_FOUND).build() :
                ResponseEntity.status(HttpStatus.OK).body(product.get()); */
    }

    @Override
    public ResponseEntity<List<Product>> getProductsByCategoryId(Long id) {
        
        try {
            Optional<Category> category = categoryRepo.findById(id);
            List<Product> products = category.get().getProducts();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Arrays.asList());
        }
        
    }

    @Override
    public ResponseEntity<?> deleteProductById(Long id) {
        
        try {
            boolean productExists = getProductById(id).getStatusCode().equals(HttpStatus.OK);

            if(productExists) {
                productRepo.deleteById(id);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Product> saveUpdateProduct(ProductDTO pDTO) {

        Product p = new Product();
        p.setId(pDTO.getId());
        p.setName(pDTO.getName());
        p.setType(pDTO.getType());
        p.setCreateAt(pDTO.getCreateAt());

        try {

            if(p.getId() == null) {
                Category category = categoryService.getCategoryById(pDTO.getCategory_id()).getBody();
                category.addProduct(p);
                return ResponseEntity.status(HttpStatus.CREATED).body(p);
            }
            Product pSave = productRepo.save(p);
            return ResponseEntity.status(HttpStatus.OK).body(pSave);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }
    
}
