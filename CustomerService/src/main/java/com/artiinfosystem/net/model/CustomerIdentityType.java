package com.artiinfosystem.net.model;

public enum CustomerIdentityType {

	PASSPORT("Passport"), IRP("IRP-Card");
	private final String customerIdentityType;
	private CustomerIdentityType(String customerIdentityType) {
		this.customerIdentityType = customerIdentityType;
	}
	
}
