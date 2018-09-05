package com.mymark.ws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mymark.api.CreateOrderRequest;
import com.mymark.api.OrderResponse;
import com.mymark.api.ShoppingCartDto;
import com.mymark.api.ShoppingCartResponse;
import com.mymark.app.data.DTOConverter;
import com.mymark.app.data.domain.Customer;
import com.mymark.app.data.domain.Order;
import com.mymark.app.data.domain.ShoppingCart;
import com.mymark.app.service.CustomerService;
import com.mymark.app.service.OrderService;
import com.mymark.app.service.ServiceException;
import com.mymark.ws.ApiException;
import com.mymark.ws.ApiMessages;

/**
 * Handles requests for the form page examples.
 */
@CrossOrigin
@RestController
@RequestMapping("api")
public class OrderServiceController {

//	@Autowired
//	protected ShoppingCartService cartService;

	@Autowired
	protected OrderService orderService;

	@Autowired
	protected CustomerService customerService;
		
	@Autowired
	protected MessageSource messageSource;

	protected final static Logger log = LoggerFactory.getLogger(OrderServiceController.class);

	public OrderServiceController() {

	}
	
	@RequestMapping(value = "/order/{username}/{orderId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<OrderResponse> getOrderByUserNameAndId(
			@PathVariable(required = true) String username, @PathVariable(required = true) Long orderId) throws ApiException {
		
		OrderResponse response = new OrderResponse();
		log.info("In getOrderByUserNameAndId...");
		
		try {

			Customer c = customerService.lookupCustomerByUserName(username);
			if (c != null) {
				Order order = orderService.getOrder(orderId);
				response.setOrder(DTOConverter.toOrderDto(order));
			}
						
		} catch (ServiceException e) {
			log.error("ServiceException thrown while getting order.", e);
			e.printStackTrace();
			throw new ApiException(ApiMessages.SERVICE_EXCEPTION_MSG, e);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}		

	@RequestMapping(value = "/order", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<OrderResponse> createOrder(
			@RequestBody CreateOrderRequest request) throws ApiException {
		
		OrderResponse response = new OrderResponse();
		log.info("In createOrder...");
		
		try {

			Customer customer = customerService.lookupCustomerByUserName(request.getUserName());
			if (customer != null) {
				Order order = orderService.createOrder(customer.getId());
				
				response.setOrder(DTOConverter.toOrderDto(order));
			}
						
		} catch (ServiceException e) {
			log.error("ServiceException thrown while creating order.", e);
			e.printStackTrace();
			throw new ApiException(ApiMessages.SERVICE_EXCEPTION_MSG, e);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}		
	
//	@RequestMapping(value = "/shoppingcart", method = RequestMethod.PUT, produces = "application/json")
//	public ResponseEntity<ShoppingCartResponse> updateCart(
//			@RequestBody UpdateShoppingCartRequest request) throws ApiException {
//		
//		ShoppingCartResponse response = new ShoppingCartResponse();
//		log.info("In updateCart...");
//		
//		try {
//
//			Customer c = customerService.lookupCustomerByUserName(request.getUserName());
//			if (c != null) {
//				if (CART_ADD_OPERATION.equalsIgnoreCase(request.getOperation().name())) {
//					Product p = productService.lookupProductByProductCode(request.getProductCode());
//					cartService.addItemtoCart(c.getShoppingCart().getId(), p.getId(), request.getQuantity());					
//				} else if (CART_REMOVE_OPERATION.equalsIgnoreCase(request.getOperation().name())) {
//					Product p = productService.lookupProductByProductCode(request.getProductCode());
//					cartService.removeItemFromCart(c.getShoppingCart().getId(), p.getId(), request.getQuantity());										
//				} else if (CART_REMOVE_ALL_OPERATION.equalsIgnoreCase(request.getOperation().name())) {
//					cartService.removeAllItemsFromCart(c.getShoppingCart().getId());										
//				}
//				
//				ShoppingCart cart = cartService.getCartForCustomer(c.getId());
//				cart.getCustomer();
//				ShoppingCartDto dto = DTOConverter.toShoppingCartDto(cart);
//				response.setCart(dto);
//			}
//						
//		} catch (ServiceException e) {
//			log.error("ServiceException thrown while updating shopping cart.", e);
//			e.printStackTrace();
//			throw new ApiException(ApiMessages.SERVICE_EXCEPTION_MSG, e);
//		}
//
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}		
	
}