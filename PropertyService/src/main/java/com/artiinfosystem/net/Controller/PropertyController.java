package com.artiinfosystem.net.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.artiinfosystem.net.exception.NoSuchRecordFoundException;
import com.artiinfosystem.net.model.Customer;
import com.artiinfosystem.net.model.CustomerProperty;
import com.artiinfosystem.net.model.Property;
import com.artiinfosystem.net.service.PropertyService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/v1/property")
public class PropertyController {
	
	@Autowired
	private PropertyService propertyService;

	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping
	public ResponseEntity<String> addProperty(@RequestBody Property property) {
		propertyService.addProperty(property);
		URI uri= ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(property.getPropertyId()).toUri();
		return ResponseEntity.created(uri).body("Property Added succesfully");
	}
	
	@GetMapping
	public ResponseEntity<List<Property>> getProperty() {
		List<Property> properties = propertyService.getProperty();
		if(null ==properties) {
			throw new NoSuchRecordFoundException("No Record Found");
		}
		return ResponseEntity.ok(properties);
	}
	
	@GetMapping("/{propertyId}")
	@HystrixCommand(fallbackMethod = "handleGetPropertyById")
	public ResponseEntity<CustomerProperty> getPropertyById(@PathVariable Long propertyId) {
		CustomerProperty customerProperty = null;
		Property property=propertyService.getPropertyById(propertyId);
		if(null ==property) {
			throw new NoSuchRecordFoundException("No Record Found");
		}
		customerProperty = new CustomerProperty();
		customerProperty.setProperty(property);
		ResponseEntity<Customer> customer = restTemplate.getForEntity("http://CUSTOMER-SERVICES/v1/customers/"+property.getCustomerId(), Customer.class);
		if(HttpStatus.OK ==customer.getStatusCode()) {
			customerProperty.setCustomer(customer.getBody());
		}
		return ResponseEntity.ok(customerProperty);
	}
	
	
	public ResponseEntity<CustomerProperty> handleGetPropertyById(@PathVariable Long propertyId) {
		CustomerProperty customerProperty = null;
		Property property=propertyService.getPropertyById(propertyId);
		if(null ==property) {
			throw new NoSuchRecordFoundException("No Record Found");
		}
		customerProperty = new CustomerProperty();
		customerProperty.setProperty(property);
		return ResponseEntity.ok(customerProperty);
	}
}
