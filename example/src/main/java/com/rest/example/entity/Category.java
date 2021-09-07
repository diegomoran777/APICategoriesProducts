package com.rest.example.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Category")
@Entity
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ApiModelProperty(notes = "Categories name")
    private String name;

	@ApiModelProperty(notes = "Products from a category")
	@JsonIgnore
    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinColumn(name = "category_id")
    private List<Product> products;

	public Category(String name, List<Product> products) {
		this.name = name;
		this.setProducts(products);
	}

	public Category() {

	}
    
    public void addProduct(Product product) {
    	this.products.add(product);
    }

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    


}
