package com.capgemini.customerapp;



import static org.junit.Assert.assertEquals;
/*
import static org.junit.Assert.assertEquals;*/
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.capgemini.customerapp.entity.Customer;
import com.capgemini.customerapp.exceptions.CustomerAlreadyRegisteredException;
import com.capgemini.customerapp.exceptions.CustomerNotFoundException;
import com.capgemini.customerapp.repository.CustomerRepository;
import com.capgemini.customerapp.service.impl.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CustomerServiceTest {

	@Mock
	CustomerRepository customerRepository;

	@InjectMocks
	CustomerServiceImpl customerServiceImpl;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
/*
	@Test
	public void testdeletecustomer() {
		Customer customer = new Customer(12345, "priyanka", "rajendranagar", "priyanka@gmail.com","12");
		customerServiceImpl.deleteCustomer(customer);;

	}
	*/
	@Test
	public void testAddCustomer() throws CustomerAlreadyRegisteredException {
		Customer customer = new Customer(123454, "priyanka", "rajendranagar", "priyanka@gmail.com","12");
		when(customerRepository.save(customer)).thenReturn(customer);
		assertEquals(customerServiceImpl.addCustomer(customer), customer);
	}
	@Test
	public void testFindProductById() throws CustomerNotFoundException {
		Customer customer = new Customer(123454, "priyanka", "rajendranagar", "priyanka@gmail.com","12");
		Optional<Customer> customer1 = Optional.of(customer);
		when(customerRepository.findById(123454)).thenReturn(customer1);
		assertEquals(customerServiceImpl.getCustomerById(123454), customer);
	}
	@Test
	public void testUpdateProduct(){
		Customer customer = new Customer(123454, "priyanka", "rajendranagar", "priyanka@gmail.com","12");
		Customer customer2 = new Customer(123454, "priyankar", "rajendranagarr", "priyarnka@gmail.com","12r");
		when(customerRepository.save(customer)).thenReturn(customer2);
		assertEquals(customerServiceImpl.updateCustomer(customer), customer2);
	}

}