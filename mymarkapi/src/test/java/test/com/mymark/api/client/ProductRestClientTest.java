package test.com.mymark.api.client;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mymark.api.ProductDetailsDto;
import com.mymark.api.ProductDto;
import com.mymark.api.client.ClientException;
import com.mymark.api.client.ProductRestClient;

@RunWith(JUnit4.class)
public class ProductRestClientTest {

	private static ProductRestClient client;

	protected final static Logger log = LoggerFactory.getLogger(ProductRestClientTest.class);

//	public static String SERVICE_URL = "http://localhost:8080/mymarkservice/api";
	public static String USERNAME = "appuser";
	public static String PASSWORD = "password";
	public static String SERVICE_URL = "http://jrs-mymark-mono.us-east-1.elasticbeanstalk.com/mymarkservice/api";


	public ProductRestClientTest() {
		// TODO Auto-generated constructor stub
	}

	@BeforeClass
	public static void setup() {
		client = new ProductRestClient(SERVICE_URL, USERNAME, PASSWORD);
	}

	@Test
	public void getProducts() {

		try {
			ArrayList<ProductDto> prodList = (ArrayList<ProductDto>)client.getProducts();
			org.junit.Assert.assertNotNull("REST Client call to GET /products has returned null.", prodList);
			log.info("REST Client GET to /products has returned: ");
			for (ProductDto p : prodList) {
				log.info("\t" + p.getName() + " (" + p.getProductCode() + "): " + p.getPrice());
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getProductDetails() {

		try {
			ArrayList<ProductDto> prodList = (ArrayList<ProductDto>)client.getProducts();
			
			for (ProductDto pdto : prodList) {
				ProductDetailsDto details = client.getProductDetails(pdto.getId());
				org.junit.Assert.assertNotNull("REST Client call to GET /product/{productId} has returned null.", details);
				log.info("REST Client GET to /product/{productId} has returned: " + details.getName() + ", Avail. Inventory: " + details.getAvailableInventory());
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void after() {
	}

}
