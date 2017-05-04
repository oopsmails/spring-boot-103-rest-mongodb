package com.oopsmails.springboot.tutorial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oopsmails.springboot.tutorial.Repository.CustomerRepository;
import com.oopsmails.springboot.tutorial.domain.Customer;

@RestController
public class CustomerController {
	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping("/customers/{email:.+}")
	//	@RequestMapping(value = "/customers/{email:.+}", method = RequestMethod.GET)
	public Customer getCustomerByEmail(@PathVariable("email") String email) {
		return customerRepository.findByEmail(email);
	}

	@RequestMapping("/customers")
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@RequestMapping("/customers/firstname/{firstname}")
	public List<Customer> getCustomersByFirstName(@PathVariable("firstname") String firstName) {
		return customerRepository.findByFirstName(firstName);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/customers")
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
		customerRepository.save(customer);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	//	@RequestMapping(method = RequestMethod.DELETE, value = "/customers/{email}/") // works, but adding a slash at the end is very danger!
	@RequestMapping(method = RequestMethod.DELETE, value = "/customers/{email:.+}")
	public ResponseEntity<String> deleteColleague(@PathVariable("email") String email) {
		Customer customer = customerRepository.findByEmail(email);
		if (customer != null) {
			customerRepository.delete(customer);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
