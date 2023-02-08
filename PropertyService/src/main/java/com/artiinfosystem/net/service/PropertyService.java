package com.artiinfosystem.net.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.artiinfosystem.net.model.Property;
import com.artiinfosystem.net.repository.PropertyRepository;

@Service
public class PropertyService {

	@Autowired
	private PropertyRepository propertyRepository;

	public void addProperty(Property property) {
		propertyRepository.save(property);
	}

	public Property getPropertyById(Long propertyId) {
		return propertyRepository.findByPropertyId(propertyId);
	}

	public List<Property> getProperty() {
		return propertyRepository.findAll();
	}
}
