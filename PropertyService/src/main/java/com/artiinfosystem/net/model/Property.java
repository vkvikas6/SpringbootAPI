package com.artiinfosystem.net.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Property {
	@Id
	private long propertyId;
	private long customerId;
	private String eirCode;
	private int houseIdentityNumber;
	private String street;
	private String area;
	private String county;
	private String country;
}
