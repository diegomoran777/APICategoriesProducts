package com.rest.example.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "Products from category")
@Entity
public class Product implements Serializable, Comparable<Product> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "Produc s name")
    @Column(name = "product_name", length = 50, nullable = false)
    private String name;

    @ApiModelProperty(notes = "Produc s type")
    @Column(name = "product_type", length = 50, nullable = false)
    private String type;
    
    @UpdateTimestamp
    private LocalDateTime lastUpdateDate;

    @CreationTimestamp
    private Date createAt;

    public Product(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Product() {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public int compareTo(Product p) {
        return (int) (this.getId() - p.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(!(obj instanceof Product)) {
            return false;
        }
        Product other = (Product) obj;
        return getType().equals(other.getType()) && getName().equals(other.getName());
        
    }
    @Override
    public String toString() {
        return "Name: " + getName() + "Type: " + getType().toString();
    }
}
