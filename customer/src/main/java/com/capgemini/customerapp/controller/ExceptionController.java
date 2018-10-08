package com.capgemini.customerapp.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.customerapp.entity.ErrorMessage;
import com.capgemini.customerapp.exceptions.CustomerAlreadyRegisteredException;
import com.capgemini.customerapp.exceptions.CustomerNotFoundException;

@ControllerAdvice
@RestController
public class ExceptionController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<ErrorMessage> customerNotFoundException(CustomerNotFoundException customerNotFoundException,
			HttpServletRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(request.getRequestURI(), customerNotFoundException.getMessage(),
				LocalDateTime.now(), HttpStatus.NOT_FOUND);
		log.error(errorMessage.toString());
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = CustomerAlreadyRegisteredException.class)
	public ResponseEntity<ErrorMessage> customerAlreadyRegisteredException(CustomerAlreadyRegisteredException customerAlreadyRegisteredException,
			HttpServletRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(request.getRequestURI(), customerAlreadyRegisteredException.getMessage(),
				LocalDateTime.now(), HttpStatus.NOT_FOUND);
		log.error(errorMessage.toString());
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}


}