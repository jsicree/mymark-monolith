package com.mymark.api.client;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.mymark.api.CustomerDto;
import com.mymark.api.CustomerResponse;
import com.mymark.api.NewCustomerRequest;

@Component
public class CustomerRestClient {

	private String customerUrl;

	private RestTemplate restTemplate;
	private HttpHeaders headers;

	protected final static Logger log = LoggerFactory
			.getLogger(CustomerRestClient.class);

	
	public CustomerRestClient() {
		super();
		restTemplate = new RestTemplate();
		headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
	}

	public CustomerRestClient(String customerUrl) {
		this();
		this.customerUrl = customerUrl;
	}

	public String getCustomerUrl() {
		return customerUrl;
	}

	public void setCustomerUrl(String customerUrl) {
		this.customerUrl = customerUrl;
	}

	
	public CustomerDto createNewCustomer(String firstName, String lastName, String userName, String email, String password) throws ClientException {

		CustomerResponse response = new CustomerResponse();

		NewCustomerRequest request = new NewCustomerRequest();
		request.setFirstName(firstName);
		request.setLastName(lastName);
		request.setUserName(userName);
		request.setEmail(email);
		request.setPassword(password);

		try {
			HttpEntity<NewCustomerRequest> entity = new HttpEntity<NewCustomerRequest>(request, headers);
			response = restTemplate.postForObject(customerUrl, entity, CustomerResponse.class);
		} catch (HttpStatusCodeException sce) {
			log.error("An HttpStatusCodeException was thrown calling the /customer web service method. HTTP status code: " + sce.getRawStatusCode());
			log.error("ErrorResponse for HttpStatusCodeException: " + sce.getResponseBodyAsString());
 			throw new ClientException(sce.getResponseBodyAsString(), sce, sce.getRawStatusCode());			
		} catch (RestClientException rce) {
			log.error("A RestClientException was thrown calling the /customer web service method.");
			throw new ClientException("RestClientException caught after call to /customer web service method.", rce);					
		}
		return response.getCustomer();
	}

	public void deleteCustomer(Long id) throws ClientException {

		log.info("Deleting customer: " + id);
		try {
			restTemplate.delete(customerUrl + "/" + id);
			log.info("Customer deleted.");
		} catch (HttpStatusCodeException sce) {
			log.error("An HttpStatusCodeException was thrown calling the /customer web service method. HTTP status code: " + sce.getRawStatusCode());
			log.error("ErrorResponse for HttpStatusCodeException: " + sce.getResponseBodyAsString());
			throw new ClientException("HttpStatusCodeException caught after call to /customer web service method. HTTP status code: " + sce.getRawStatusCode(), sce);			
		} catch (RestClientException rce) {
			log.error("A RestClientException was thrown calling the /customer web service method.");
			throw new ClientException("RestClientException caught after call to /customer web service method.", rce);					
		}
	}
	
}
