package com.spring.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoadRESTApplication {

	//netstat -ano | findstr :<PORT>
	public static void main(String[] args) {
		SpringApplication.run(LoadRESTApplication.class, args);
	}
	
	/**
	 * @author KTANDUR
	 * Create table script for Product table. Need to Use MySql
	 * 
	 * CREATE TABLE `product` (
  		`id` int(11) NOT NULL AUTO_INCREMENT,
  		`name` varchar(50) NOT NULL,
  		`price` float NOT NULL,
  		PRIMARY KEY (`id`)
		);
	 * 
	 */

}
