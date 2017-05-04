package com.oopsmails.springboot.tutorial.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.oopsmails.springboot.tutorial.domain.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
	public List<Customer> findByFirstName(String firstName);

	public List<Customer> findByLastName(String lastName);
	
	public Customer findByEmail(String email);
}
