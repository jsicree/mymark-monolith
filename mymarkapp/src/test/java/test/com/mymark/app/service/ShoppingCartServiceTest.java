/**
 * 
 */
package test.com.mymark.app.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
import com.mymark.app.data.domain.Product;
import com.mymark.app.data.domain.ShoppingCart;
import com.mymark.app.service.CustomerService;
import com.mymark.app.service.ProductService;
import com.mymark.app.service.ServiceException;
import com.mymark.app.service.ShoppingCartService;

/**
 * @author Joseph Sicree
 *
 */
@RunWith(JUnit4.class)
public class ShoppingCartServiceTest {

	protected final static Logger log = LoggerFactory.getLogger(ShoppingCartServiceTest.class);

	private static final Boolean USE_MOCK = Boolean.FALSE;
	
	private static AbstractApplicationContext context;
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
		} else {
			mockSetup();
		}
		
		try {
			Customer c = customerService.lookupCustomerByUserName("cartTester");
			if (c == null) {
				c = customerService.createNewCustomer(
						"John", "Doe", "cartTester", "cartTester@foo.com", "newpassword");
								
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
				
				Customer c = customerService.lookupCustomerByUserName("cartTester");
				if (c != null) {
					customerService.deleteCustomer(c.getId());
				}
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			context.close();
		}
	}
	
	@Test
	public void addItemsToShoppingCart() {

		log.info("Running test: addItemToShoppingCart.");

		try {
			Customer c = customerService.lookupCustomerByUserName("cartTester");
			log.info("Before Add: " + c.getShoppingCart().toString());

			ShoppingCart cart = shoppingCartService.getCartForCustomer(c.getId());

			Product p1 = productService.lookupProductByProductCode("PROD-001");
			log.info(p1.toString());

			Product p2 = productService.lookupProductByProductCode("PROD-002");
			log.info(p2.toString());
			
			shoppingCartService.addItemtoCart(c.getShoppingCart().getId(), p1.getId(), 1);
			c = customerService.lookupCustomerByUserName("cartTester");
			org.junit.Assert.assertEquals("Expected a shopping cart with 1 line items.", 1, c.getShoppingCart().getLineItems().size());
			log.info("After Add 1 P1: " + shoppingCartService.getCartForCustomer(c.getId()));

			shoppingCartService.addItemtoCart(c.getShoppingCart().getId(), p2.getId(), 1);
			c = customerService.lookupCustomerByUserName("cartTester");
			org.junit.Assert.assertEquals("Expected a shopping cart with 2 line items.", 2, c.getShoppingCart().getLineItems().size());
			log.info("After Add 1 P2: " + shoppingCartService.getCartForCustomer(c.getId()));

			shoppingCartService.addItemtoCart(c.getShoppingCart().getId(), p1.getId(), 1);
			c = customerService.lookupCustomerByUserName("cartTester");
			log.info("After Add 1 P1: " + shoppingCartService.getCartForCustomer(c.getId()));
			shoppingCartService.removeItemFromCart(c.getShoppingCart().getId(), p1.getId(), 1);
			c = customerService.lookupCustomerByUserName("cartTester");
			log.info("After Remove 1 P1: " + shoppingCartService.getCartForCustomer(c.getId()));
			shoppingCartService.removeItemFromCart(c.getShoppingCart().getId(), p1.getId(), 1);
			c = customerService.lookupCustomerByUserName("cartTester");
			log.info("After Remove 1 P1: " + shoppingCartService.getCartForCustomer(c.getId()));
						
			shoppingCartService.addItemtoCart(c.getShoppingCart().getId(), p1.getId(), 1);
			c = customerService.lookupCustomerByUserName("cartTester");
			log.info("After Add 1 P1: " + shoppingCartService.getCartForCustomer(c.getId()));

			shoppingCartService.removeAllItemsFromCart(c.getShoppingCart().getId());
			c = customerService.lookupCustomerByUserName("cartTester");
			org.junit.Assert.assertEquals("Expected a shopping cart with 0 line items.", 0, c.getShoppingCart().getLineItems().size());
			log.info("After Remove All Items: " + shoppingCartService.getCartForCustomer(c.getId()));
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

				
	}
		
}