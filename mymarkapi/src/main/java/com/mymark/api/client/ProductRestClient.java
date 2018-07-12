package com.mymark.api.client;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.mymark.api.ProductDetailsDto;
import com.mymark.api.ProductDetailsResponse;
import com.mymark.api.ProductDto;
import com.mymark.api.ProductsResponse;

@Component
public class ProductRestClient {

	private String productUrl;

	private RestTemplate restTemplate;
	private HttpHeaders headers;

	protected final static Logger log = LoggerFactory
			.getLogger(ProductRestClient.class);

	
	public ProductRestClient() {
		super();
		restTemplate = new RestTemplate();
		headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
	}

	public ProductRestClient(String productUrl) {
		this();
		this.productUrl = productUrl;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	
	public List<ProductDto> getProducts() throws ClientException {

		ProductsResponse response = new ProductsResponse();

		try {
			response = restTemplate.getForObject(productUrl + "/products", ProductsResponse.class);
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
			response = restTemplate.getForObject(productUrl + "/product/" + productId, ProductDetailsResponse.class);
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
