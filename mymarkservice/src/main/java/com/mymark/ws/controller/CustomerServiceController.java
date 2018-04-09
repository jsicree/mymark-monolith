package com.mymark.ws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mymark.api.CustomerDto;
import com.mymark.api.CustomerResponse;
import com.mymark.api.GreetingResponse;
import com.mymark.api.NewCustomerRequest;
import com.mymark.app.data.DTOConverter;
import com.mymark.app.data.domain.Customer;
import com.mymark.app.service.CredentialService;
import com.mymark.app.service.CustomerService;
import com.mymark.app.service.ServiceException;
import com.mymark.ws.ApiException;

/**
 * Handles requests for the form page examples.
 */
@RestController
@RequestMapping("api")
public class CustomerServiceController {

	@Autowired
	protected CustomerService customerService;

	@Autowired
	protected CredentialService credentialService;
	
	@Autowired
	protected MessageSource messageSource;

	protected final static Logger log = LoggerFactory.getLogger(CustomerServiceController.class);

	public CustomerServiceController() {

	}

	@RequestMapping(value = "/customer", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<CustomerResponse> createNewCustomer(
			@RequestBody NewCustomerRequest request) throws ApiException {
		
		CustomerResponse response = new CustomerResponse();
		
		log.info("In createNewCustomer...");
		
		log.debug("++NewCustomerRequest++");
		log.debug("First Name:" + request.getFirstName());
		log.debug("Last Name:" + request.getLastName());
		log.debug("User Name:" + request.getUserName());
		log.debug("Email:" + request.getEmail());
		log.debug("Password:" + request.getPassword());
		log.debug("--NewCustomerRequest--");

		Customer newCustomer;
		try {
			newCustomer = customerService.createNewCustomer(request.getFirstName(), request.getLastName(), request.getUserName(), request.getEmail());
			if (newCustomer != null) {
				Boolean isCredentialCreated = credentialService.addCredential(newCustomer.getId(), request.getPassword());				
				if (isCredentialCreated) {
					CustomerDto dto = DTOConverter.toCustomerDto(newCustomer);
					response.setCustomer(dto);
				}
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<CustomerResponse> deleteCustomer(
			@PathVariable(required = true) Long id) throws ApiException {
		
		
		log.info("In deleteCustomer...");
		log.debug("Deleting customer: " + id);
		
		CustomerResponse response = new CustomerResponse();

		try {
			Customer customer = customerService.lookupCustomerById(id);
			if (customer != null) {
				credentialService.removeCredential(customer.getId());
				customerService.deleteCustomer(id);
				response.setCustomer(DTOConverter.toCustomerDto(customer));
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	
}