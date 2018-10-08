package com.capgemini.customerapp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.customerapp.entity.Customer;
import com.capgemini.customerapp.exceptions.CustomerAlreadyRegisteredException;
import com.capgemini.customerapp.exceptions.CustomerNotFoundException;
import com.capgemini.customerapp.repository.CustomerRepository;
import com.capgemini.customerapp.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	public Customer addCustomer(Customer customer) throws CustomerAlreadyRegisteredException {
		Optional<Customer> customerFromDb = customerRepository.findById(customer.getCustomerId());
		if (!customerFromDb.isPresent()) {
			return customerRepository.save(customer);
		}
		throw new CustomerAlreadyRegisteredException("Customer Already present with id "+ customer.getCustomerId());
	}

	@Override
	public Customer authenticate(int customerId, String customerPassword) throws CustomerNotFoundException {
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if (optionalCustomer.isPresent()) {

			if (optionalCustomer.get().getCustomerPassword().equals(customerPassword)) {
				return optionalCustomer.get();
			}
			
		}
		throw new CustomerNotFoundException("Customer does not found Please Register!!!");
	}

	@Override
	public Customer getCustomerById(int customerId) throws CustomerNotFoundException {
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if (optionalCustomer.isPresent()) {
			Customer customer = optionalCustomer.get();

			return customer;
		}
		throw new CustomerNotFoundException("customer with this id not found");
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(int customerId) {

		customerRepository.deleteById(customerId);
	}

}