package com.capgemini.customerapp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.customerapp.entity.Customer;
import com.capgemini.customerapp.exceptions.CustomerNotFoundException;
import com.capgemini.customerapp.repository.CustomerRepository;
import com.capgemini.customerapp.service.CustomerService;

@Service
public class CustomerSrviceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}


	@Override
	public Customer getCustomerById(int customerId) throws CustomerNotFoundException {
		Optional<Customer> optionalProduct = customerRepository.findById(customerId);
		if (optionalProduct.isPresent())
			return optionalProduct.get();
		System.out.println(optionalProduct.get());
		throw new CustomerNotFoundException("Customer does not exists");
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(Customer customer) {
		customerRepository.delete(customer);
	}

	@Override
	public Customer authenticate(Customer customer) throws CustomerNotFoundException{
		Optional<Customer> customerFromDb = customerRepository.findById(customer.getCustomerId());
		if (customerFromDb.isPresent()) {
			if (customerFromDb.get().getCustomerPassword().equals(customer.getCustomerPassword())) {
				return customerFromDb.get();
			}
			
		}
		throw new CustomerNotFoundException(
				"Auht failed!! Customer not found in database with id" + customer.getCustomerId());
	}
	

}
