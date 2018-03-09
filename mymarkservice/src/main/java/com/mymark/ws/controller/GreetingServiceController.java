package com.mymark.ws.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mymark.api.ErrorResponse;
import com.mymark.api.GreetingDto;
import com.mymark.api.GreetingRequest;
import com.mymark.api.GreetingResponse;
import com.mymark.app.service.GreetingService;
import com.mymark.app.service.ServiceException;
import com.mymark.ws.ApiException;
import com.mymark.ws.ApiMessages;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Handles requests for the form page examples.
 */
@RestController
@RequestMapping("api/greeting")
public class GreetingServiceController {

	@Autowired
	protected GreetingService greetingService;

	@Autowired
	protected MessageSource messageSource;

	protected final static Logger log = LoggerFactory.getLogger(GreetingServiceController.class);

	@Autowired
	@Qualifier("greetingRequestValidator")
	private Validator greetingValidator;

	@InitBinder("greetingRequest")
	public void setupCreateCustomerBinder(WebDataBinder binder) {
		binder.addValidators(greetingValidator);
	}

	public GreetingServiceController() {

	}

	
	@ApiOperation(value = "${ApiOp.GreetingServiceController.sayHello.value}",
		      notes = "${ApiOp.GreetingServiceController.sayHello.notes}")
	@ApiResponses(value = {
	         @ApiResponse(code = 200, message = "The operation was successful.", response = GreetingResponse.class),
	        @ApiResponse(code = 422, message = "The operation input failed validation", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "An internal server error has occurred", response = ErrorResponse.class)})	
	@RequestMapping(value = "/v1/sayHello", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<GreetingResponse> sayHello(
			@ApiParam(value = "${ApiParam.CustomerServiceController.createCustomer.request}", required = true)			
			@Valid @RequestBody GreetingRequest request)
			throws ApiException {
		log.info("In sayHello...");
		GreetingResponse response = new GreetingResponse();

		GreetingDto greeting = new GreetingDto();

		try {
			if (request.getName() != null) {
				if (request.getName().equalsIgnoreCase("BAD_NAME")) {
					throw new ApiException("Request contained name " + request.getName());
				} else {
					greeting.setMessage(greetingService.sayHello(request.getName()));				
				}
			} else {
				greeting.setMessage(greetingService.sayHello());								
			}
			response.setGreeting(greeting);
		} catch (ServiceException e) {
			log.error("ServiceException thrown while creating customer.", e);
			e.printStackTrace();
			throw new ApiException(ApiMessages.SERVICE_EXCEPTION_MSG, e);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}