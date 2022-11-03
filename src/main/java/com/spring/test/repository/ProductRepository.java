package com.spring.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.test.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	

}
