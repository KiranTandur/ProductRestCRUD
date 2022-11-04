package com.spring.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.test.entity.Product;
import com.spring.test.exception.DataNotFoundException;
import com.spring.test.exception.ErrorMessage;
import com.spring.test.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	public List<Product> listAll(){
		return repository.findAll();
		
	}
	
	public void saveProduct(Product product) {
		/*if(product.getName() == null || product.getName().isEmpty()) {
			//throw new InvalidInputException("601", "Please enter valid name");
		}*/
			repository.save(product);
	}
	
	public Product getProductById(Integer id) {
		
		//return repository.findById(id).get();
		//Changing for exception handling
		Optional<Product> product = repository.findById(id);
		
		
		System.out.println("<000000Inside getOroduct ))))00000");
		if(!product.isPresent()) {
			System.out.println("<000000Inside getOroduct ))))00000");
			throw new DataNotFoundException("Id : "+id+" is not Found In DB");
		}
		return product.get(); 
	}
	
	public void deleteProductById(Integer id) {
		repository.deleteById(id);;
	}
	
	/**
	 * Below code is implementation of JPA Derived Queries
	 * @param name
	 * @return List<Product>
	 */
	
	public List<Product> listProductByName(String name){
		return repository.findByName(name);
	}
	
	public List<Product> listProductByNameIs(String name){
		return repository.findByNameIs(name);
	}
	
	public List<Product> listProductByNameEquals(String name){
		return repository.findByNameEquals(name);
	}
	
	public List<Product> listProductByNameIsNot(String name){
		return repository.findByNameIsNot(name);
	}
	
	public List<Product> listProductByNameStartsWith(String name){
		return repository.findByNameStartingWith(name);
	}
	
	public List<Product> listProductByNameContaining(String name){
		return repository.findByNameContaining(name);
	}
	
	public List<Product> listProductByNameLike(String name){
		String likePattern = name+"%";
		return repository.findByNameLike(likePattern);
	}
	
	public List<Product> listProductByPriceLessThan(float price){
		 List<Product> productList = repository.findByPriceLessThan(price);
		if(productList.isEmpty()) {
			throw new DataNotFoundException("Products Not Found for the Price : "+price);
		}
		return productList;
	}
	
}
