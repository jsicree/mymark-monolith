package com.mymark.api.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import com.mymark.api.CartOperation;
import com.mymark.api.NewCustomerRequest;
import com.mymark.api.ShoppingCartDto;
import com.mymark.api.ShoppingCartResponse;
import com.mymark.api.UpdateShoppingCartRequest;

@Component
public class ShoppingCartRestClient extends BaseRestClient {


	protected final static Logger log = LoggerFactory
			.getLogger(ShoppingCartRestClient.class);
	
	public ShoppingCartRestClient(String url, String username, String password) {
		super(url, username, password);        
	}
	
	public ShoppingCartDto getShoppingCart(String userName) throws ClientException {
		ShoppingCartResponse response = new ShoppingCartResponse();

		try {

			HttpEntity<?> request = new HttpEntity<Object>(getHeaders());
	        ResponseEntity<ShoppingCartResponse> resp = getRestTemplate().exchange(getServiceUrl() + "/shoppingcart/" + userName, HttpMethod.GET, request, ShoppingCartResponse.class);
	        response = resp.getBody();
	        
		} catch (HttpStatusCodeException sce) {
			log.error("An HttpStatusCodeException was thrown calling the /shoppingcart web service method. HTTP status code: " + sce.getRawStatusCode());
			log.error("ErrorResponse for HttpStatusCodeException: " + sce.getResponseBodyAsString());
 			throw new ClientException(sce.getResponseBodyAsString(), sce, sce.getRawStatusCode());			
		} catch (RestClientException rce) {
			log.error("A RestClientException was thrown calling the /products web service method.");
			throw new ClientException("RestClientException caught after call to /products web service method.", rce);					
		}
		return response.getCart();		
	}
	
	public ShoppingCartDto addItemToShoppingCart(String userName, String productCode, Integer quantity) throws ClientException {
		ShoppingCartResponse response = new ShoppingCartResponse();

		try {

			HttpEntity<UpdateShoppingCartRequest> request = new HttpEntity<UpdateShoppingCartRequest>(new UpdateShoppingCartRequest(), getHeaders());
			request.getBody().setUserName(userName);
			request.getBody().setOperation(CartOperation.ADD);
			request.getBody().setProductCode(productCode);
			request.getBody().setQuantity(quantity);
			
	        ResponseEntity<ShoppingCartResponse> resp = getRestTemplate().exchange(getServiceUrl() + "/shoppingcart", HttpMethod.PUT, request, ShoppingCartResponse.class);
	        response = resp.getBody();
	        
		} catch (HttpStatusCodeException sce) {
			log.error("An HttpStatusCodeException was thrown calling the /shoppingcart web service method (PUT). HTTP status code: " + sce.getRawStatusCode());
			log.error("ErrorResponse for HttpStatusCodeException: " + sce.getResponseBodyAsString());
 			throw new ClientException(sce.getResponseBodyAsString(), sce, sce.getRawStatusCode());			
		} catch (RestClientException rce) {
			log.error("A RestClientException was thrown calling the /shoppingcart web service method (PUT).");
			throw new ClientException("RestClientException caught after call to /products web service method.", rce);					
		}
		return response.getCart();		
	}

	public ShoppingCartDto removeItemFromShoppingCart(String userName, String productCode, Integer quantity) throws ClientException {
		ShoppingCartResponse response = new ShoppingCartResponse();

		try {

			HttpEntity<UpdateShoppingCartRequest> request = new HttpEntity<UpdateShoppingCartRequest>(new UpdateShoppingCartRequest(), getHeaders());
			request.getBody().setUserName(userName);
			request.getBody().setOperation(CartOperation.REMOVE);
			request.getBody().setProductCode(productCode);
			request.getBody().setQuantity(quantity);
			
	        ResponseEntity<ShoppingCartResponse> resp = getRestTemplate().exchange(getServiceUrl() + "/shoppingcart", HttpMethod.PUT, request, ShoppingCartResponse.class);
	        response = resp.getBody();
	        
		} catch (HttpStatusCodeException sce) {
			log.error("An HttpStatusCodeException was thrown calling the /shoppingcart web service method (PUT). HTTP status code: " + sce.getRawStatusCode());
			log.error("ErrorResponse for HttpStatusCodeException: " + sce.getResponseBodyAsString());
 			throw new ClientException(sce.getResponseBodyAsString(), sce, sce.getRawStatusCode());			
		} catch (RestClientException rce) {
			log.error("A RestClientException was thrown calling the /shoppingcart web service method (PUT).");
			throw new ClientException("RestClientException caught after call to /products web service method.", rce);					
		}
		return response.getCart();		
	}

	public ShoppingCartDto removeAllItemsFromShoppingCart(String userName) throws ClientException {
		ShoppingCartResponse response = new ShoppingCartResponse();

		try {

			HttpEntity<UpdateShoppingCartRequest> request = new HttpEntity<UpdateShoppingCartRequest>(new UpdateShoppingCartRequest(), getHeaders());
			request.getBody().setUserName(userName);
			request.getBody().setOperation(CartOperation.REMOVE_ALL);
			
	        ResponseEntity<ShoppingCartResponse> resp = getRestTemplate().exchange(getServiceUrl() + "/shoppingcart", HttpMethod.PUT, request, ShoppingCartResponse.class);
	        response = resp.getBody();
	        
		} catch (HttpStatusCodeException sce) {
			log.error("An HttpStatusCodeException was thrown calling the /shoppingcart web service method (PUT). HTTP status code: " + sce.getRawStatusCode());
			log.error("ErrorResponse for HttpStatusCodeException: " + sce.getResponseBodyAsString());
 			throw new ClientException(sce.getResponseBodyAsString(), sce, sce.getRawStatusCode());			
		} catch (RestClientException rce) {
			log.error("A RestClientException was thrown calling the /shoppingcart web service method (PUT).");
			throw new ClientException("RestClientException caught after call to /products web service method.", rce);					
		}
		return response.getCart();		
	}
	
}
