package com.artiinfosystem.net.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artiinfosystem.net.model.Property;


public interface PropertyRepository extends JpaRepository<Property, Long>{

	Property findByPropertyId(Long propertyId);

}
