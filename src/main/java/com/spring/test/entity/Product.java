package com.spring.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
public class Product {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length=50, unique=true)
	@NotBlank(message="Product Name Must Be Provided... It Should Not Blank")
	@Length(min = 5, max=50, message="Product name lenght must be 5 - 50 chars")
	private String name;
	
	
	@Min(value=100, message = "Product price must be more than 99Rs")
	@Max(value=100000, message = "Product price must be less than 100000Rs")
	private float price;
	
	public Product() {
		
	}
	public Product(Integer id, String name, float price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	

}
