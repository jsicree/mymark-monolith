package test.com.mymark.api.client;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mymark.api.CustomerDto;
import com.mymark.api.ProductDetailsDto;
import com.mymark.api.ProductDto;
import com.mymark.api.ShoppingCartDto;
import com.mymark.api.client.ClientException;
import com.mymark.api.client.CustomerRestClient;
import com.mymark.api.client.ProductRestClient;
import com.mymark.api.client.ShoppingCartRestClient;

@RunWith(JUnit4.class)
public class ShoppingCartRestClientTest {

	private static CustomerRestClient customerClient;
	private static ShoppingCartRestClient cartClient;
	private static ProductRestClient productClient;

	private static CustomerDto cartCustomer;
	
	protected final static Logger log = LoggerFactory.getLogger(ShoppingCartRestClientTest.class);

	public static String SERVICE_URL = "http://localhost:8080/mymarkservice/api";
	public static String USERNAME = "appuser";
	public static String PASSWORD = "password";
	
//	public static String SERVICE_URL = "http://jrs-mymono-dev.us-east-1.elasticbeanstalk.com/api/customer";

	public static String[] CART_CUSTOMER = { "Cart", "RestTester", "restTester", "restTester@foo.com", "password1234" };
	
	public static String PRODUCT_CODE = "PROD-001";

	public ShoppingCartRestClientTest() {
		// TODO Auto-generated constructor stub
	}

	@BeforeClass
	public static void setup() {
		customerClient = new CustomerRestClient(SERVICE_URL, USERNAME, PASSWORD);
		cartClient = new ShoppingCartRestClient(SERVICE_URL, USERNAME, PASSWORD);
		productClient = new ProductRestClient(SERVICE_URL, USERNAME, PASSWORD);
		log.info("Creating cartCustomer");

		try {	
			cartCustomer = customerClient.createNewCustomer(CART_CUSTOMER[0], CART_CUSTOMER[1], CART_CUSTOMER[2],
					CART_CUSTOMER[3], CART_CUSTOMER[4]);
			log.info("cartCustomer: " + cartCustomer.getUserName());
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Test
	public void getShoppingCart() {

		try {
			ShoppingCartDto dto = cartClient.getShoppingCart(cartCustomer.getUserName());
			org.junit.Assert.assertNotNull("REST Client call to GET /shoppingcart has returned null.", dto);
			log.info("REST Client GET to /shoppingcart has returned cart for: " + dto.getUserName() + "; Num Items: " + dto.getLineItems().getLineItems().size() + "; Total: " + dto.getTotalPrice());
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void addRemoveItemsFromShoppingCart() {

		try {
			ShoppingCartDto dto = cartClient.getShoppingCart(cartCustomer.getUserName());
			org.junit.Assert.assertNotNull("REST Client call to GET /shoppingcart has returned null.", dto);
			log.info("REST Client GET to /shoppingcart has returned cart for: " + dto.getUserName() + "; Num Line Items: " + dto.getNumLineItems() + "; Total Qty: " + dto.getTotalQuantity() + "; Total: " + dto.getTotalPrice());

			ArrayList<ProductDto> productList = (ArrayList<ProductDto>) productClient.getProducts();
			if (!productList.isEmpty()) {
				int qty = 1;
				for (ProductDto prod : productList) {
					ProductDetailsDto prodDetails = productClient.getProductDetails(prod.getId());
					if (prodDetails != null && prodDetails.getAvailableInventory() > 0) {
						log.info("Adding product " + prodDetails.getName() + " to cart");
						dto = cartClient.addItemToShoppingCart(cartCustomer.getUserName(), prodDetails.getProductCode(), qty);
						log.info("REST Client GET to /shoppingcart after add has returned cart for: " + dto.getUserName() + "; Num Line Items: " + dto.getNumLineItems() + "; Total Qty: " + dto.getTotalQuantity() + "; Total: " + dto.getTotalPrice());						
					}
					qty += 1;					
				}
				for (ProductDto prod : productList) {
					ProductDetailsDto prodDetails = productClient.getProductDetails(prod.getId());
					log.info("Adding product " + prodDetails.getName() + " to cart");
					dto = cartClient.removeItemFromShoppingCart(cartCustomer.getUserName(), prodDetails.getProductCode(), 1);
					log.info("REST Client GET to /shoppingcart after remove has returned cart for: " + dto.getUserName() + "; Num Line Items: " + dto.getNumLineItems() + "; Total Qty: " + dto.getTotalQuantity() + "; Total: " + dto.getTotalPrice());						
				}
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@AfterClass
	public static void after() {
		// Clean up
		try {
			cartClient.removeAllItemsFromShoppingCart(cartCustomer.getUserName());
			customerClient.deleteCustomer(cartCustomer.getId());
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
