package com.artiinfosystem.net.exception;

public class BadRequestException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3080831426983590069L;

	public BadRequestException(String message) {
		super(message);
	}
	
}
