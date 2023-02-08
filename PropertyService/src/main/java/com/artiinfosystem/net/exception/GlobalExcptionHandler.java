package com.artiinfosystem.net.exception;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@ControllerAdvice
public class GlobalExcptionHandler {
	
	@ExceptionHandler(NoSuchRecordFoundException.class)
	public ResponseEntity<String> handleException(WebRequest request, NoSuchRecordFoundException ex) {
		URI locaUri = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUri();
		return ResponseEntity.notFound().location(locaUri).build();
	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(WebRequest request, Exception ex) {
		return ResponseEntity.internalServerError().body("Technical error occured");
	}

}
