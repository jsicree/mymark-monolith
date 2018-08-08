package com.mymark.api.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import com.mymark.api.ProductDetailsDto;
import com.mymark.api.ProductDetailsResponse;
import com.mymark.api.ProductDto;
import com.mymark.api.ProductsResponse;

@Component
public class ProductRestClient extends BaseRestClient {

	protected final static Logger log = LoggerFactory
			.getLogger(ProductRestClient.class);

	
	public ProductRestClient(String url, String username, String password) {
		super(url, username, password);        
	}
	
	public List<ProductDto> getProducts() throws ClientException {

		ProductsResponse response = null;

		try {

			HttpEntity<?> request = new HttpEntity<Object>(getHeaders());
	        ResponseEntity<ProductsResponse> resp = getRestTemplate().exchange(getServiceUrl() + "/products", HttpMethod.GET, request, ProductsResponse.class);
	        response = resp.getBody();
	        
		} catch (HttpStatusCodeException sce) {
			log.error("An HttpStatusCodeException was thrown calling the /products web service method. HTTP status code: " + sce.getRawStatusCode());
			log.error("ErrorResponse for HttpStatusCodeException: " + sce.getResponseBodyAsString());
 			throw new ClientException(sce.getResponseBodyAsString(), sce, sce.getRawStatusCode());			
		} catch (RestClientException rce) {
			log.error("A RestClientException was thrown calling the /products web service method.");
			throw new ClientException("RestClientException caught after call to /products web service method.", rce);					
		}
		
		return response.getProducts().getProducts();
	}

	public ProductDetailsDto getProductDetails(Long productId) throws ClientException {

		ProductDetailsResponse response = new ProductDetailsResponse();

		try {
			HttpEntity<?> request = new HttpEntity<Object>(getHeaders());
	        ResponseEntity<ProductDetailsResponse> resp = getRestTemplate().exchange(getServiceUrl() + "/product/" + productId, HttpMethod.GET, request, ProductDetailsResponse.class);
	        response = resp.getBody();
		} catch (HttpStatusCodeException sce) {
			log.error("An HttpStatusCodeException was thrown calling the /product/{productId} web service method. HTTP status code: " + sce.getRawStatusCode());
			log.error("ErrorResponse for HttpStatusCodeException: " + sce.getResponseBodyAsString());
 			throw new ClientException(sce.getResponseBodyAsString(), sce, sce.getRawStatusCode());			
		} catch (RestClientException rce) {
			log.error("A RestClientException was thrown calling the /product/{productId} web service method.");
			throw new ClientException("RestClientException caught after call to /product/{productId} web service method.", rce);					
		}
		
		return response.getProductDetails();
	}
	
	
}
