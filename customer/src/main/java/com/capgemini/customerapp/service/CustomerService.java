package com.capgemini.customerapp.service;


import com.capgemini.customerapp.entity.Customer;
import com.capgemini.customerapp.exceptions.CustomerAlreadyRegisteredException;
import com.capgemini.customerapp.exceptions.CustomerNotFoundException;

public interface CustomerService {

	public Customer addCustomer(Customer customer) throws CustomerAlreadyRegisteredException ;

	public Customer authenticate(int customerId, String customerPassword) throws CustomerNotFoundException;

	public Customer updateCustomer(Customer customer);

	public Customer getCustomerById(int customerId) throws CustomerNotFoundException;

	public void deleteCustomer(int customerId);

}
