package com.artiinfosystem.net.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.artiinfosystem.net.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>  {
	
	public Customer findByCustomerId(Long customerId);

}
