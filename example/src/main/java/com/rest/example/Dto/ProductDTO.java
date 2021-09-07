package com.rest.example.Dto;

import java.util.Date;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductDTO {

    private Long id;

	@Size(min = 2, message = "Should have min 2 characters")
    private String name;

	@Size(min = 2, message = "Should have min 2 characters")
    private String type;

	@NotNull
    private Long category_id;

	@FutureOrPresent
	private Date createAt;

	
	public ProductDTO(String name, String type, long category_id) {
		this.name = name;
		this.type = type;
		this.category_id = category_id;
		this.createAt = new Date();
	}

	public ProductDTO() {
		
	}
    
	
	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
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
	
	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

    
}
