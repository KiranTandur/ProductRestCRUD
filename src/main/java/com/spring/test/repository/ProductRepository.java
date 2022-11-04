package com.spring.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.test.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	public List<Product> findByName(String name);
	
	public List<Product> findByNameIs(String name);
	public List<Product> findByNameEquals(String name);
	public List<Product> findByNameIsNot(String name);
	
	public List<Product> findByNameStartingWith(String name);
	
	public List<Product> findByNameContaining(String infix);
	
	public List<Product> findByNameLike(String infix);
	
	public List<Product> findByPriceLessThan(float infix);
	
	@Query("Select p FROM Product p")
	public List<Product> getAllProduct();
	
	@Query("Select p FROM Product p where p.name = :n")
	public List<Product> getProductByName(@Param("n") String name);
	
	//Native Query Declaration
	@Query(value = "Select * from product", nativeQuery=true)
	public List<Product> getProductsUsingNativeQuery();


}
