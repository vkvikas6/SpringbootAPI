package com.artiinfosystem.net.model;

public enum CustomerType {
	LANDLORD("Landlord"), TENANT("Tenant") ;
	public final String customerType;
	private CustomerType(String customerType){
		this.customerType = customerType;
	}
	
	

}
