# ProductRestCRUD
Simple CRUD Application using Spring Boot and MySQL Database connectivity. Also I have implemented code using many annotations along with exception handling, i,e @ControllerAdvice. And have added spring @Value annotation based code. Adding to that also , have added code for JPA Derived Queries Implementation as well.

Below is the Create table script for product table

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
