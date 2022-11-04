package com.spring.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.test.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	public List<Product> findByName(String name);
	
	public List<Product> findByNameIs(String name);
	public List<Product> findByNameEquals(String name);
	public List<Product> findByNameIsNot(String name);
	
	public List<Product> findByNameStartingWith(String name);
	
	public List<Product> findByNameContaining(String infix);
	
	public List<Product> findByNameLike(String infix);


}
