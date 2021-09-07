package com.rest.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.rest.example.Dto.ProductDTO;
import com.rest.example.entity.Category;
import com.rest.example.exception.customException.NotFoundException;
import com.rest.example.repository.ICategoryRepository;
import com.rest.example.repository.IProductRepository;
import com.rest.example.service.ProductService;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@RunWith(JUnit4.class)
@SpringBootTest
class ExampleApplicationTests {

	@Autowired 
	ProductService productService;

	@Autowired 
	IProductRepository productRepo;
	
	@Autowired 
	ICategoryRepository categoryRepo;

	@Before
	public void init() {

		Arrays.asList(new Category("Congelados", new ArrayList<>()), new Category("Varios", new ArrayList<>()))
            .forEach(c -> categoryRepo.save(c));

		Arrays.asList(new ProductDTO("Fideos", "Almacen", 1L), new ProductDTO("Atun", "Enlatados", 1L), 
            new ProductDTO("Carne", "Frescos", 2L), new ProductDTO("Pan", "Almacen", 2L))
                .forEach(p -> productService.saveUpdateProduct(p));
	}

	@Test
	void getProducts() {
		assertEquals(HttpStatus.OK, productService.getProducts().getStatusCode());
		assertEquals(4, productService.getProducts().getBody().size());

		productRepo.deleteAll();
		assertEquals(HttpStatus.NO_CONTENT, productService.getProducts().getStatusCode());
	}

	@Test
    public void getProductsByType() {
		assertEquals(HttpStatus.OK, productService.getProductsByType("Almacen").getStatusCode());
		assertEquals(2, productService.getProductsByType("Almacen").getBody().size());

		productRepo.deleteAll();
		assertEquals(HttpStatus.NO_CONTENT, productService.getProductsByType("Almacen").getStatusCode());
	}

	@Test
	public void getProductById() throws NotFoundException {
		assertEquals(HttpStatus.OK, productService.getProductById(1L).getStatusCode());
		assertEquals("USER NOT FOUND", productService.getProductById(0L).getBody());		
	}

	@Test
	public void getProductByCategoryId() {
		assertEquals(HttpStatus.OK, productService.getProductsByCategoryId(1L).getStatusCode());
		assertEquals(HttpStatus.NO_CONTENT, productService.getProductsByCategoryId(0L).getBody());		
	}

	@Test
	public void deleteProductById() {
		assertEquals(HttpStatus.OK, productService.deleteProductById(1L).getStatusCode());
		assertEquals(HttpStatus.NOT_FOUND, productService.deleteProductById(0L).getBody());
	}

	@Test
	public void saveUpdateProduct() {

		ProductDTO pUpload = new ProductDTO("Fideos de verdura", "Almacen", 1L);
		pUpload.setId(1L);

		assertEquals(HttpStatus.OK, productService.saveUpdateProduct(pUpload).getStatusCode());
		assertEquals("Fideos de verdura", productService.saveUpdateProduct(pUpload).getBody().getName());

		ProductDTO pSave = new ProductDTO();
		pSave.setCategory_id(1L);
		pSave.setCreateAt(new Date());
		pSave.setName("Atun");
		pSave.setType("Almacen");

		assertEquals(HttpStatus.CREATED, productService.saveUpdateProduct(pSave).getStatusCode());
		assertEquals("Atun", productService.saveUpdateProduct(pSave).getBody().getName());
	}

}
