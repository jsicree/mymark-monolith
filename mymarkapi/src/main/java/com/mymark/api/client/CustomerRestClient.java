package com.mymark.api.client;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.mymark.api.CustomerDto;
import com.mymark.api.CustomerResponse;
import com.mymark.api.NewCustomerRequest;

@Component
public class CustomerRestClient {

	private String createNewCustomerUrl;

	private RestTemplate restTemplate;

	protected final static Logger log = LoggerFactory
			.getLogger(CustomerRestClient.class);

	
	public CustomerRestClient() {
		super();
		restTemplate = new RestTemplate();
		
	}

	public CustomerRestClient(String createNewCustomerUrl) {
		this();
		this.createNewCustomerUrl = createNewCustomerUrl;
	}

	public String getCreateNewCustomerUrl() {
		return createNewCustomerUrl;
	}

	public void setCreateNewCustomerUrl(String createNewCustomerUrl) {
		this.createNewCustomerUrl = createNewCustomerUrl;
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
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			HttpEntity<NewCustomerRequest> entity = new HttpEntity<NewCustomerRequest>(request, headers);
			response = restTemplate.postForObject(createNewCustomerUrl, entity, CustomerResponse.class);
		} catch (HttpStatusCodeException sce) {
			log.error("An HttpStatusCodeException was thrown calling the createNewCustomer service. HTTP status code: " + sce.getRawStatusCode());
			log.error("ErrorResponse for HttpStatusCodeException: " + sce.getResponseBodyAsString());
			throw new ClientException("HttpStatusCodeException caught after call to createNewCustomer service. HTTP status code: " + sce.getRawStatusCode(), sce);			
		}		
		return response.getCustomer();
	}
		
}
