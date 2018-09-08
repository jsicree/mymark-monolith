/**
 * 
 */
package test.com.mymark.app.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.mymark.app.config.MyMarkAppConfig;
import com.mymark.app.data.domain.CartLineItem;
import com.mymark.app.data.domain.Customer;
import com.mymark.app.data.domain.Order;
import com.mymark.app.data.domain.Product;
import com.mymark.app.data.domain.ShoppingCart;
import com.mymark.app.service.CustomerService;
import com.mymark.app.service.OrderService;
import com.mymark.app.service.ProductService;
import com.mymark.app.service.ServiceException;
import com.mymark.app.service.ShoppingCartService;

/**
 * @author Joseph Sicree
 *
 */
@RunWith(JUnit4.class)
public class OrderServiceTest {

	protected final static Logger log = LoggerFactory.getLogger(OrderServiceTest.class);

	private static final Boolean USE_MOCK = Boolean.FALSE;
	
	private static AbstractApplicationContext context;
	private static OrderService orderService;
	private static CustomerService customerService;
	private static ShoppingCartService shoppingCartService;
	private static ProductService productService;
//	
//	private static ProductRepository productRepoMock;
//	private static InventoryItemRepository inventoryRepoMock;

	
	@BeforeClass
	public static void setup() {
		if (!USE_MOCK) {
			context = new AnnotationConfigApplicationContext(MyMarkAppConfig.class);
			customerService = (CustomerService) context.getBean("customerService");
			shoppingCartService = (ShoppingCartService) context.getBean("shoppingCartService");
			productService = (ProductService) context.getBean("productService");
			orderService = (OrderService) context.getBean("orderService");
		} else {
			mockSetup();
		}
		
		try {
			Customer c = customerService.lookupCustomerByUserName("orderTester");
			if (c != null) {
				orderService.deleteAllOrdersForCustomer(c);
				customerService.deleteCustomer(c.getId());
			}
			
			c = customerService.lookupCustomerByUserName("orderTester");
			if (c == null) {
				c = customerService.createNewCustomer(
						"John", "Doe", "orderTester", "orderTester@foo.com", "newpassword");
				
				ShoppingCart cart = shoppingCartService.getCartForCustomer(c.getId());

				Product p1 = productService.lookupProductByProductCode("PROD-001");
				log.info(p1.toString());
				shoppingCartService.addItemtoCart(c.getShoppingCart().getId(), p1.getId(), 2);

				c = customerService.lookupCustomerByUserName("orderTester");
				log.info("After Add 2 P1: " + shoppingCartService.getCartForCustomer(c.getId()));
				
				Product p2 = productService.lookupProductByProductCode("PROD-002");
				log.info(p2.toString());
				shoppingCartService.addItemtoCart(c.getShoppingCart().getId(), p2.getId(), 3);
				
				c = customerService.lookupCustomerByUserName("orderTester");
				log.info("After Add 3 P2: " + shoppingCartService.getCartForCustomer(c.getId()));


			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

	public static void mockSetup() {

//		List<Product> productList = new ArrayList<Product>();		
//		productList.add(new Product(1L, "Test Product 1","TEST-PROD-01", 25.00, "Test Product 1 short desc.",
//				"Test Product 1 long desc."));
//		productList.add(new Product(2L, "Test Product 2","TEST-PROD-02", 19.99, "Test Product 2 short desc.",
//				"Test Product 2 long desc."));
//		productList.add(new Product(3L, "Test Product 3","TEST-PROD-03", 49.99, "Test Product 3 short desc.",
//				"Test Product 3 long desc."));
//		
//		
//		productRepoMock = mock(ProductRepository.class);
//		when(productRepoMock.findAll()).thenReturn(productList);
//		
//		when(productRepoMock.findById(productList.get(0).getId())).thenReturn(Optional.ofNullable(productList.get(0)));
//		when(productRepoMock.findById(productList.get(1).getId())).thenReturn(Optional.ofNullable(productList.get(1)));
//		when(productRepoMock.findById(productList.get(2).getId())).thenReturn(Optional.ofNullable(productList.get(2)));
//		when(productRepoMock.findById(BAD_PRODUCT_ID)).thenReturn(null);
//
//		inventoryRepoMock = mock(InventoryItemRepository.class);
//		when(inventoryRepoMock.getAvailableInventory(productList.get(0).getId())).thenReturn(10L);
//		when(inventoryRepoMock.getAvailableInventory(productList.get(1).getId())).thenReturn(20L);
//		when(inventoryRepoMock.getAvailableInventory(productList.get(2).getId())).thenReturn(30L);
//		when(inventoryRepoMock.getAvailableInventory(BAD_PRODUCT_ID)).thenReturn(null);
//
//		productService = new ProductServiceImpl(productRepoMock, inventoryRepoMock);
	}
	
	@AfterClass
	public static void after() {
		if (context != null) {
			try {
				Customer c = customerService.lookupCustomerByUserName("orderTester");
				if (c != null) {
					orderService.deleteAllOrdersForCustomer(c);
					customerService.deleteCustomer(c.getId());
				}
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			context.close();
		}
	}
	
	@Test
	public void createOrder() {

		log.info("Running test: createOrder.");

		try {
			Customer c = customerService.lookupCustomerByUserName("orderTester");
			
			if (c.getShoppingCart().getLineItems() != null && !c.getShoppingCart().getLineItems().isEmpty()) {

				// Create an order using the customer's shopping cart
				Order o = orderService.createOrder(c.getId());
				org.junit.Assert.assertNotNull("Order for customer was null.", o);
				log.info("Created order for user: " + c.getUserName());
				log.info("Order after create: " + o);

				// Submit the order
				o = orderService.submitOrder(o.getId());
				log.info("Order after submit: " + o);
				
				// Submit the order
				o = orderService.cancelOrder(o.getId());
				log.info("Order after cancel: " + o);

				
			} else {
				org.junit.Assert.fail("User orderTester was expected to have at least 1 item in shopping cart");
			}
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
		
}