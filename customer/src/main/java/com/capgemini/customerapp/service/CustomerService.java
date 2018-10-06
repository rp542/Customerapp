package com.capgemini.customerapp.service;

import com.capgemini.customerapp.entity.Customer;
import com.capgemini.customerapp.exceptions.CustomerNotFoundException;

public interface CustomerService {
	public Customer addCustomer(Customer customer);

	public Customer authenticate(Customer customer) throws CustomerNotFoundException;

	public Customer getCustomerById(int customerId) throws CustomerNotFoundException;

	public Customer updateCustomer(Customer customer);

	public void deleteCustomer(Customer productFromDb);

}
