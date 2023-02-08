package com.artiinfosystem.net;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.artiinfosystem.net.exception.CustomerNotFoundException;
/**
 * 
 * @author 179101
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class) 
	public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest request){
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class) 
	public ResponseEntity<String> HandleGlobalException(Exception ex, WebRequest request) {
		logger.error(ex.getMessage());
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
	}
}
