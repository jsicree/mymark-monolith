package com.mymark.api.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import com.mymark.api.CreateOrderRequest;
import com.mymark.api.NewCustomerRequest;
import com.mymark.api.OrderDto;
import com.mymark.api.OrderResponse;

@Component
public class OrderRestClient extends BaseRestClient {

	protected final static Logger log = LoggerFactory
			.getLogger(OrderRestClient.class);

	
	public OrderRestClient(String url, String username, String password) {
		super(url, username, password);        
	}
	
	public OrderDto getOrder(String userName, Long orderId) throws ClientException {

		OrderResponse response = null;

		try {

			HttpEntity<?> request = new HttpEntity<Object>(getHeaders());
	        ResponseEntity<OrderResponse> resp = getRestTemplate().exchange(getServiceUrl() + "/order/" + userName + "/" + orderId , HttpMethod.GET, request, OrderResponse.class);
	        response = resp.getBody();
	        
		} catch (HttpStatusCodeException sce) {
			log.error("An HttpStatusCodeException was thrown calling the /order web service method. HTTP status code: " + sce.getRawStatusCode());
			log.error("ErrorResponse for HttpStatusCodeException: " + sce.getResponseBodyAsString());
 			throw new ClientException(sce.getResponseBodyAsString(), sce, sce.getRawStatusCode());			
		} catch (RestClientException rce) {
			log.error("A RestClientException was thrown calling the /order web service method.");
			throw new ClientException("RestClientException caught after call to /order web service method.", rce);					
		}
		
		return response.getOrder();
	}

	public OrderDto createOrder(String userName) throws ClientException {

		OrderResponse response = null;

		try {

	        HttpEntity<CreateOrderRequest> request = new HttpEntity<CreateOrderRequest>(new CreateOrderRequest(), getHeaders());
			request.getBody().setUserName(userName);

	        ResponseEntity<OrderResponse> resp = getRestTemplate().exchange(getServiceUrl() + "/order", HttpMethod.POST, request, OrderResponse.class);
	        response = resp.getBody();
	        
		} catch (HttpStatusCodeException sce) {
			log.error("An HttpStatusCodeException was thrown calling the /order web service method. HTTP status code: " + sce.getRawStatusCode());
			log.error("ErrorResponse for HttpStatusCodeException: " + sce.getResponseBodyAsString());
 			throw new ClientException(sce.getResponseBodyAsString(), sce, sce.getRawStatusCode());			
		} catch (RestClientException rce) {
			log.error("A RestClientException was thrown calling the /order web service method.");
			throw new ClientException("RestClientException caught after call to /order web service method.", rce);					
		}
		
		return response.getOrder();
	}
	
}
