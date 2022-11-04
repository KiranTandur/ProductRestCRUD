package com.spring.test.exception;

import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.test.dto.ResponseDto;

@ControllerAdvice
public class CheckControllerAdvice extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ErrorMessage.class)
	public ResponseEntity<String> handleInvalidInput(ErrorMessage invalidInputException){
		
		return new ResponseEntity<>("Invalid Inputfor Name..", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> noElimentFound(NoSuchElementException noSuchElementFoundException){
		return new ResponseEntity<>("Element is not Found in DB", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ResponseDto> constraintViolationException(ConstraintViolationException constraintViolationException){
		ResponseDto response1 = new ResponseDto();
		response1.setCode("400");
		response1.setStatus("Failure");
		response1.setMessage(constraintViolationException.getMessage());
		return new ResponseEntity<>(response1, HttpStatus.BAD_REQUEST);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseDto dataNotFounfException(DataNotFoundException dataNotFounfException){
		System.out.println("Coming inside");
		ResponseDto response1 = new ResponseDto();
		response1.setCode("404");
		response1.setStatus("Failure");
		response1.setMessage(dataNotFounfException.getMessage());
		return response1;
	}
	
	
	//public ResponseDto
	

}
