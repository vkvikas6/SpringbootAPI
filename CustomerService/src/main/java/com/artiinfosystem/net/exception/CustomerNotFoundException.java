package com.artiinfosystem.net.exception;

public class CustomerNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(Long customerId) {
		super("Customer Not found for Id: "+customerId);
	}
}
