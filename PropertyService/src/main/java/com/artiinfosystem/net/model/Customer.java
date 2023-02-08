package com.artiinfosystem.net.model;

import java.util.Date;

import lombok.Data;

@Data
public class Customer {
	private long customerId;
	private String firstName;
	private String lastName;
	private String customerType;
	private int age;
	private Date customerDOB;
	private String customerIdentityType;
	private String identityNumber;
}
