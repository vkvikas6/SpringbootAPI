package com.artiinfosystem.net.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Customer {
	@Id
	private long customerId;
	private String firstName;
	private String lastName;
	private CustomerType customerType;
	private int age;
	private Date customerDOB;
	private CustomerIdentityType customerIdentityType;
	private String identityNumber;
}
