package com.athena.plano_de_aula.api.exceptionhandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = "com.athena.plano_de_aula.api.controller")
public class ProductControllerAdvice {
	
	@ResponseBody
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<MessageExceptionHandler> productNotFound(ProductNotFoundException productNotFound){
		MessageExceptionHandler error = new MessageExceptionHandler(
				new Date(), HttpStatus.NOT_FOUND.value(), "Produto não encontrado");
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
