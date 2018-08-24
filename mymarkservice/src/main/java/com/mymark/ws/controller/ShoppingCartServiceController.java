package com.mymark.ws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mymark.api.ShoppingCartDto;
import com.mymark.api.ShoppingCartResponse;
import com.mymark.app.data.DTOConverter;
import com.mymark.app.data.domain.Customer;
import com.mymark.app.data.domain.ShoppingCart;
import com.mymark.app.service.CustomerService;
import com.mymark.app.service.ServiceException;
import com.mymark.app.service.ShoppingCartService;
import com.mymark.ws.ApiException;
import com.mymark.ws.ApiMessages;

/**
 * Handles requests for the form page examples.
 */
@CrossOrigin
@RestController
@RequestMapping("api")
public class ShoppingCartServiceController {

	@Autowired
	protected ShoppingCartService cartService;

	@Autowired
	protected CustomerService customerService;
	
	@Autowired
	protected MessageSource messageSource;

	protected final static Logger log = LoggerFactory.getLogger(ShoppingCartServiceController.class);

	public ShoppingCartServiceController() {

	}
	
	@RequestMapping(value = "/product/{username}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ShoppingCartResponse> getCartByUserName(
			@PathVariable(required = true) String username) throws ApiException {
		
		ShoppingCartResponse response = new ShoppingCartResponse();
		log.info("In getCartByUserName...");
		
		try {

			Customer c = customerService.lookupCustomerByUserName(username);
			if (c != null) {
				ShoppingCart cart = cartService.getCartForCustomer(c.getId());				
				ShoppingCartDto dto = DTOConverter.toShoppingCartDto(cart);
				response.setCart(dto);
			}
						
		} catch (ServiceException e) {
			log.error("ServiceException thrown while getting shopping cart.", e);
			e.printStackTrace();
			throw new ApiException(ApiMessages.SERVICE_EXCEPTION_MSG, e);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}		
	
}