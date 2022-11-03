package com.spring.test.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.test.entity.Product;
import com.spring.test.exception.ErrorMessage;
import com.spring.test.service.ProductService;

@RestController
@RequestMapping("/shop")
public class ProductController {
	
	Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService service;
	
	@GetMapping("/products")
	public List<Product> list() {
		return service.listAll();
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Integer id) {
		logger.trace("*** Inside getProduct ***");
		Product product = service.getProductById(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	/**
	 * @author KTANDUR
	 * Writting Java Bean Validation code  and Hibernate Validation code
	 * REST API Best practices are as below
	 * Always Vaidate requested data in the request payload
	 * If data is invalid , return status code 400 (Bad Request) with clear error message
	 * 
	 * 
	 * Also to handle exception, we need to write custom exception by using @controllerAdvice annotation
	 * Need to create separate excpetion class
	 */
	
	
	@PostMapping("/products")
	public ResponseEntity<?> addProduct(@RequestBody Product product) {
			service.saveProduct(product);
			return new ResponseEntity<>(product, HttpStatus.CREATED);
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Integer id) {
		try {
			Product existProduct = service.getProductById(id);
			service.saveProduct(product);
			
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(NoSuchElementException ex) {
			System.out.println("Product not found to update");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable Integer id) {
		service.deleteProductById(id);
	}
	
	
	
}
