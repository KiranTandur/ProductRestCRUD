package com.spring.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.spring.test.dto.BasicConfiguration;
import com.spring.test.entity.Product;
import com.spring.test.service.ProductService;

@RestController
@RequestMapping("/shop")
public class ProductController {
	
	Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService service;
	
	@Autowired
	private BasicConfiguration basicConfiguration;
	
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
	
	/**
	 * Below code is to implement Spring Derived Queries
	 * https://www.youtube.com/watch?v=QeRCW28jhFY&ab_channel=LearnCodeWithDurgesh
	 * 
	 * https://www.baeldung.com/spring-data-derived-queries
	 */
	
	@GetMapping("/byName/{name}")
	public List<Product> getProductByName(@PathVariable String name) {
		return service.listProductByName(name);
	}
	
	@GetMapping("/byNameIs/{name}")
	public List<Product> getProductByNameIs(@PathVariable String name) {
		return service.listProductByNameIs(name);
	}
	
	@GetMapping("/byNameEquals/{name}")
	public List<Product> getProductByNameEquals(@PathVariable String name) {
		return service.listProductByNameEquals(name);
	}
	
	@GetMapping("/byNameIsNot/{name}")
	public List<Product> getProductByNameIsNot(@PathVariable String name) {
		return service.listProductByNameIsNot(name);
	}
	
	@GetMapping("/byNameStartsWith/{name}")
	public List<Product> getProductByNameStartsWith(@PathVariable String name) {
		return service.listProductByNameStartsWith(name);
	}
	
	@GetMapping("/byNameContaining/{name}")
	public List<Product> getProductByNameContaining(@PathVariable String name) {
		return service.listProductByNameContaining(name);
	}
	
	@GetMapping("/byNameLike/{name}")
	public List<Product> getProductByNameLike(@PathVariable String name) {
		return service.listProductByNameLike(name);
	}
	
	@GetMapping("/byPriceLessThan/{price}")
	public List<Product> getProductByNameLike(@PathVariable float price) {
		return service.listProductByPriceLessThan(price);
	}
	
	/**
	 * Below code is for Native query definition 
	 * @Query Implementation
	 */
	
	@GetMapping("/getAllProducts")
	public List<Product> getAllProductsUsingNativeQuery() {
		//return service.listAllProdcutUsingNativeQuery();
		System.out.println("<--- Below Result Using JPQL Query 1 --->");
		List<Product> allProducts = service.listAllProdcutUsingJPQLQuery();
		allProducts.forEach(product->{
			System.out.println(product);
		});
		
		System.out.println("<-------------------------------------------->");
		System.out.println("!! Below Result Using JPQL Query 2 with where clause !!");
		
		List<Product> oneProduct = service.getProductByName("iPhone");
		oneProduct.forEach(p->{
			System.out.println(p);
		});
		
		
		//Native Query , as we use in MySql
		System.out.println("<-------------------------------------------->");
		System.out.println("<!! Below Result Using Native Query !!");
		
		List<Product> nativeProduct = service.listAllProdcutUsingNativeQuery();
		nativeProduct.forEach(p->{
			System.out.println(p);
		});
		
										
		return allProducts;
	}
	
	/**
	 * Below is the code for Spring Boot Profiles and Value Annotation
	 */
	
	@Value("${basic.message}")
	private String grretingMessage;
	
	@Value("${basic.hi : Default Value Popping up}")
	private String defaultValue;
	
	@Value("${basic.list.values}")
	private List<String> listValues;
		
	@Value("#{${dbvalues}}")
	private Map<String,String> mapDbValues;
	
	@GetMapping("/testProfileConfiguration")
	public Map<String, Object> checkDynamicConfiguration(){
		Map<String, Object> map = new HashMap<>();
		map.put("message",basicConfiguration.getMessage());
		map.put("number",basicConfiguration.getNumber());
		map.put("value",basicConfiguration.isValue());
		return map;
	}
	
	@GetMapping("/welcome")
	public String getGreetingMessage() {
		return grretingMessage+" : "+defaultValue+" : List :"+listValues+" : Config Map DB Vaues : "+mapDbValues;
	}
	
	
	
	
}
