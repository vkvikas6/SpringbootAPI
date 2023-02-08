package com.artiinfosystem.net.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artiinfosystem.net.exception.BadRequestException;
import com.artiinfosystem.net.model.Customer;
import com.artiinfosystem.net.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	public List<Customer>fetchCustomer() {
		return customerRepository.findAll();
	}
	
	public Customer fetchCustomerById(Long customerId) {
		return customerRepository.findByCustomerId(customerId);
	}


	public void validateRequest(Customer customer) {
		if (customer.getCustomerId()==0) {
			throw new BadRequestException("Missing Madatory Field in Request");
		}
	}

}
