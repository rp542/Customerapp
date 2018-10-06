package com.capgemini.customerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.customerapp.entity.Customer;
import com.capgemini.customerapp.exceptions.CustomerNotFoundException;
import com.capgemini.customerapp.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@PostMapping("/customer")
	public @ResponseBody ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(customerService.addCustomer(customer),
				HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		try {
			customerService.getCustomerById(customer.getCustomerId());
			return new ResponseEntity<Customer>(customerService.updateCustomer(customer), HttpStatus.OK);
		} catch (CustomerNotFoundException exception) {
			// logged the exception
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId) {
		try {
			Customer customerdb = customerService.getCustomerById(customerId);
			return new ResponseEntity<Customer>(customerdb, HttpStatus.OK);
		} catch (CustomerNotFoundException exception) {
			// logged the exception
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/customer/{customerId}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable int customerId) {
		try {
			Customer customerdb = customerService.getCustomerById(customerId);
			if (customerdb != null) {
				customerService.deleteCustomer(customerdb);
				return new ResponseEntity<Customer>(HttpStatus.OK);
			}
		} catch (CustomerNotFoundException exception) {
			// logged the exception
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
	@PostMapping("/customer/authentication")
	public ResponseEntity<Customer> authenticate(@RequestBody Customer customer) throws CustomerNotFoundException {
		
		return new ResponseEntity<Customer>(customerService.authenticate(customer), HttpStatus.FOUND);
	}
}
