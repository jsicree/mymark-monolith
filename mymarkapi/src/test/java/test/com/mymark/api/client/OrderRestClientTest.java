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
import com.mymark.api.OrderDto;
import com.mymark.api.ProductDetailsDto;
import com.mymark.api.ProductDto;
import com.mymark.api.ShoppingCartDto;
import com.mymark.api.client.ClientException;
import com.mymark.api.client.CustomerRestClient;
import com.mymark.api.client.OrderRestClient;
import com.mymark.api.client.ProductRestClient;
import com.mymark.api.client.ShoppingCartRestClient;

@RunWith(JUnit4.class)
public class OrderRestClientTest {

	private static CustomerRestClient customerClient;
	private static ShoppingCartRestClient cartClient;
	private static ProductRestClient productClient;
	private static OrderRestClient orderClient;

	private static CustomerDto orderCustomer;
	
	protected final static Logger log = LoggerFactory.getLogger(OrderRestClientTest.class);

	public static String SERVICE_URL = "http://localhost:8080/mymarkservice/api";
//	public static String SERVICE_URL = "http://jrs-mymark-mono.us-east-1.elasticbeanstalk.com/mymarkservice/api";
	public static String USERNAME = "appuser";
	public static String PASSWORD = "password";
	
	public static String[] ORDER_CUSTOMER = { "Order", "RestTester", "restTester", "restTester@foo.com", "password1234" };
	
	public static String PRODUCT_CODE = "PROD-001";

	public OrderRestClientTest() {
		// TODO Auto-generated constructor stub
	}

	@BeforeClass
	public static void setup() {
		customerClient = new CustomerRestClient(SERVICE_URL, USERNAME, PASSWORD);
		cartClient = new ShoppingCartRestClient(SERVICE_URL, USERNAME, PASSWORD);
		productClient = new ProductRestClient(SERVICE_URL, USERNAME, PASSWORD);
		orderClient = new OrderRestClient(SERVICE_URL, USERNAME, PASSWORD);
		log.info("In setup....");

		try {	
			orderCustomer = customerClient.createNewCustomer(ORDER_CUSTOMER[0], ORDER_CUSTOMER[1], ORDER_CUSTOMER[2],
					ORDER_CUSTOMER[3], ORDER_CUSTOMER[4]);
			log.info("Created customer: " + orderCustomer.getUserName());
			populateShoppingCart();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		log.info("Setup complete.\n");
	}

	@Test
	public void createOrder() {

		try {
			OrderDto dto = orderClient.createOrder(orderCustomer.getUserName());
			org.junit.Assert.assertNotNull("REST Client call to POST /order has returned null.", dto);
			log.info("REST Client POST to /order has returned order for: " + dto.getUserName() + "; Num Items: " + dto.getLineItems().getLineItems().size() + "; Total: " + dto.getTotalPrice());
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void populateShoppingCart() {

		try {
			ShoppingCartDto dto = cartClient.getShoppingCart(orderCustomer.getUserName());
			org.junit.Assert.assertNotNull("REST Client call to GET /shoppingcart has returned null.", dto);
			//log.info("REST Client GET to /shoppingcart has returned cart for: " + dto.getUserName() + "; Num Line Items: " + dto.getNumLineItems() + "; Total Qty: " + dto.getTotalQuantity() + "; Total: " + dto.getTotalPrice());

			log.info("Fetching products");
			ArrayList<ProductDto> productList = (ArrayList<ProductDto>) productClient.getProducts();
			if (!productList.isEmpty()) {
				int qty = 1;
				for (ProductDto prod : productList) {
					ProductDetailsDto prodDetails = productClient.getProductDetails(prod.getId());
					if (prodDetails != null && prodDetails.getAvailableInventory() > 0) {
						log.info("Adding product " + prodDetails.getName() + " to cart");
						dto = cartClient.addItemToShoppingCart(orderCustomer.getUserName(), prodDetails.getProductCode(), qty);
					}
					qty += 1;					
				}
			}
			log.info("Shopping cart: " + dto.getUserName() + "; Num Line Items: " + dto.getNumLineItems() + "; Total Qty: " + dto.getTotalQuantity() + "; Total: " + dto.getTotalPrice());						
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@AfterClass
	public static void after() {
		// Clean up
//		try {
//			cartClient.removeAllItemsFromShoppingCart(cartCustomer.getUserName());
//			customerClient.deleteCustomer(cartCustomer.getId());
//		} catch (ClientException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
