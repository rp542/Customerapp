package com.capgemini.customerapp;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.customerapp.controller.CustomerController;
import com.capgemini.customerapp.entity.Customer;
import com.capgemini.customerapp.service.CustomerService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CustomerControllerTest {

	private MockMvc mockMvc;
	@Mock
	private CustomerService customerService;;

	@InjectMocks
	private CustomerController customerController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void testAuthenticateCustomer() throws Exception {
		String content = "{\r\n" + "  \"customerId\": 12345,\r\n" + "  \"customerPassword\": \"12\"\r\n" + "}";
		Customer customer = new Customer(12345, "priyanka", "rajendranagar", "priyanka@gmail.com", "12");
		when(customerService.authenticate(Mockito.isA(Customer.class))).thenReturn(customer);
		mockMvc.perform(post("/customer/authentication").content(content).contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print()).andExpect(jsonPath("$.customerEmail").exists())
				.andExpect(jsonPath("$.customerEmail").value("priyanka@gmail.com"));
		verify(customerService).authenticate(Mockito.isA(Customer.class));
	}

	@Test
	public void testupdateCustomer() throws Exception {

		when(customerService.updateCustomer(Mockito.isA(Customer.class))).thenReturn(new Customer(12345, "priyanka", "rajendranagar", "priyanka@gmail.com", "121"));
		when(customerService.getCustomerById(12345)).thenReturn(new Customer(12345, "priyanka1", "priyanka1@gmail.com", "rajendranagar1", "121"));
		String content = "{\r\n" + "  \"customerId\": 12345,\r\n" + "  \"customerName\": \"priyanka1\",\r\n"
				+ "  \"customerAddress\": \"rajendranagar1\",\r\n" + "  \"customerEmail\": \"priyanka1@gmail.com\",\r\n"
				+ "  \"customerPassword\": \"121\"\r\n" + "}";

		mockMvc.perform(put("/customer").contentType(MediaType.APPLICATION_JSON).content(content)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(jsonPath("$.customerId").exists())
		.andExpect(jsonPath("$.customerName").exists())
		.andExpect(jsonPath("$.customerAddress").exists())
		.andExpect(jsonPath("$.customerEmail").exists())
		.andExpect(jsonPath("$.customerPassword").exists())
		.andExpect(jsonPath("$.customerId").value(12345))
		.andExpect(jsonPath("$.customerName").value("priyanka"))
		.andExpect(jsonPath("$.customerAddress").value("rajendranagar"))
		.andExpect(jsonPath("$.customerEmail").value("priyanka@gmail.com"))
		.andExpect(jsonPath("$.customerPassword").value("121"))
		.andDo(print());


	}

	@Test
	public void testGetCustomer() throws Exception {
		String content = "{\r\n" + "  \"customerId\": 12345,\r\n" + "  \"customerName\": \"priyanka\",\r\n"
				+ "  \"customerEmail\": \"priyanka@gmail.com\",\r\n" + "  \"customerAddress\": \"rajendranagar\",\r\n"
				+ "  \"customerPassword\": \"12\"\r\n" + "}";
		Customer customer = new Customer(12345, "priyanka", "priyanka@gmail.com", "rajendranagar", "12");
		when(customerService.getCustomerById(12345)).thenReturn(customer);
		mockMvc.perform(
				get("/customer/12345").contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(jsonPath("$.customerEmail").exists())
				.andExpect(jsonPath("$.customerName").value("priyanka"));
	}

	@Test
	public void testDelete() throws Exception {
		when(customerService.getCustomerById(1234))
				.thenReturn(new Customer(12345, "priyanka", "rajendranagar", "priyanka@gmail.com", "12"));
		mockMvc.perform(delete("/customer/1234").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(print());
	}
	@Test
	public void testaddCustomer() throws Exception {
		when(customerService.addCustomer(Mockito.isA(Customer.class)))
				.thenReturn(new Customer(12346, "priyankar", "priyanka1@gmail.com", "rajendranagarr", "121"));

		String content = "{\r\n" + "  \"customerId\": 12346,\r\n" + "  \"customerName\": \"priyankar\",\r\n"
				+ "  \"customerEmail\": \"priyanka1@gmail.com\",\r\n" + "  \"customerAddress\": \"rajendranagarr\",\r\n"
				+ "  \"customerPassword\": \"121\"\r\n" + "}";

		mockMvc.perform(post("/customer").contentType(MediaType.APPLICATION_JSON).content(content)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.customerId").exists())
				.andExpect(jsonPath("$.customerName").exists())
				.andExpect(jsonPath("$.customerAddress").exists())
				.andExpect(jsonPath("$.customerEmail").exists())
				.andExpect(jsonPath("$.customerPassword").exists())
				.andExpect(jsonPath("$.customerId").value(12346))
				.andExpect(jsonPath("$.customerName").value("priyankar"))
				.andExpect(jsonPath("$.customerAddress").value("priyanka1@gmail.com"))
				.andExpect(jsonPath("$.customerEmail").value("rajendranagarr"))
				.andExpect(jsonPath("$.customerPassword").value("121"))
				.andDo(print());

	}
	
	
}
