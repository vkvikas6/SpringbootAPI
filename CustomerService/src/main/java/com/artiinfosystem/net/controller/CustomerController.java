package com.artiinfosystem.net.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.artiinfosystem.net.exception.CustomerNotFoundException;
import com.artiinfosystem.net.model.Customer;
import com.artiinfosystem.net.services.CustomerService;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<Object> add(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(customer.getCustomerId())
				.toUri();
		  ResponseEntity<Object> entity = ResponseEntity.created(location).build();
		  return entity;
	}

	@GetMapping
	public ResponseEntity<List<Customer>> fetchCustomer() {
		List<Customer>customerList= customerService.fetchCustomer();
		return ResponseEntity.ok().body(customerList);
	} 
	
	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> fetchCustomer(@PathVariable Long customerId) throws CustomerNotFoundException{
		ResponseEntity<Customer> responseEntity = null;
		Customer customer = customerService.fetchCustomerById(customerId);
		if (customer == null)
			throw new CustomerNotFoundException(customerId);
		else {
			responseEntity =ResponseEntity.ok().body(customer);
		}
		return responseEntity;
	} 

}
