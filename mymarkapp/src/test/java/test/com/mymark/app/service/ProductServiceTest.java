/**
 * 
 */
package test.com.mymark.app.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.mymark.app.data.domain.Credential;
import com.mymark.app.data.domain.Customer;
import com.mymark.app.data.domain.Product;
import com.mymark.app.jpa.repository.CredentialRepository;
import com.mymark.app.jpa.repository.CustomerRepository;
import com.mymark.app.jpa.repository.InventoryItemRepository;
import com.mymark.app.jpa.repository.ProductRepository;
import com.mymark.app.service.ProductService;
import com.mymark.app.service.ServiceException;
import com.mymark.app.service.impl.CustomerServiceImpl;
import com.mymark.app.service.impl.ProductServiceImpl;

/**
 * @author Joseph Sicree
 *
 */
@RunWith(JUnit4.class)
public class ProductServiceTest {

	protected final static Logger log = LoggerFactory.getLogger(ProductServiceTest.class);

	private static final Long BAD_PRODUCT_ID = 999999L;

	private static final Boolean USE_MOCK = Boolean.FALSE;
	
	private static AbstractApplicationContext context;
	private static ProductService productService;
	
	private static ProductRepository productRepoMock;
	private static InventoryItemRepository inventoryRepoMock;

	
	@BeforeClass
	public static void setup() {
		if (!USE_MOCK) {
			context = new AnnotationConfigApplicationContext(MyMarkAppConfig.class);
			productService = (ProductService) context.getBean("productService");
		} else {
			mockSetup();
		}
	}

	public static void mockSetup() {

		List<Product> productList = new ArrayList<Product>();		
		productList.add(new Product(1L, "Test Product 1","TEST-PROD-01", 25.00, "Test Product 1 short desc.",
				"Test Product 1 long desc."));
		productList.add(new Product(2L, "Test Product 2","TEST-PROD-02", 19.99, "Test Product 2 short desc.",
				"Test Product 2 long desc."));
		productList.add(new Product(3L, "Test Product 3","TEST-PROD-03", 49.99, "Test Product 3 short desc.",
				"Test Product 3 long desc."));
		
		
		productRepoMock = mock(ProductRepository.class);
		when(productRepoMock.findAll()).thenReturn(productList);
		
		when(productRepoMock.findById(productList.get(0).getId())).thenReturn(Optional.ofNullable(productList.get(0)));
		when(productRepoMock.findById(productList.get(1).getId())).thenReturn(Optional.ofNullable(productList.get(1)));
		when(productRepoMock.findById(productList.get(2).getId())).thenReturn(Optional.ofNullable(productList.get(2)));
		when(productRepoMock.findById(BAD_PRODUCT_ID)).thenReturn(null);

		inventoryRepoMock = mock(InventoryItemRepository.class);
		when(inventoryRepoMock.getAvailableInventory(productList.get(0).getId())).thenReturn(10L);
		when(inventoryRepoMock.getAvailableInventory(productList.get(1).getId())).thenReturn(20L);
		when(inventoryRepoMock.getAvailableInventory(productList.get(2).getId())).thenReturn(30L);
		when(inventoryRepoMock.getAvailableInventory(BAD_PRODUCT_ID)).thenReturn(null);

		productService = new ProductServiceImpl(productRepoMock, inventoryRepoMock);
	}
	
	@AfterClass
	public static void after() {
		if (context != null) {
			context.close();
		}
	}

	@Test
	public void getAllProducts() {

		log.info("Running test: getAllProducts.");

		try {
			List<Product> prodList = productService.getAllProducts();
			org.junit.Assert.assertNotNull("getAllProducts returned null.", prodList);
			for (Product p : prodList) {
				log.info(p.toString());
			}
			
		} catch (ServiceException se) {
			se.printStackTrace();
			org.junit.Assert.fail("getAllProducts() threw a ServiceException. " + se.getMessage());
		}
		
	}

	@Test
	public void getProduct() {

		log.info("Running test: getProductById.");
		List<Product> prodList = new ArrayList<Product>();

		try {
			prodList = productService.getAllProducts();
			org.junit.Assert.assertNotNull("Setup call to getAllProducts returned null.", prodList);

		} catch (ServiceException se) {
			se.printStackTrace();
			org.junit.Assert.fail("Setup call to getAllProducts threw a ServiceException. " + se.getMessage());
		}
		
		try {
			for (Product p : prodList) {
				Product prod = productService.lookupProductById(p.getId());
				org.junit.Assert.assertNotNull("lookupProductById returned null.", prod);
				log.info(prod.toString());
			}			
		} catch (ServiceException se) {
			se.printStackTrace();
			org.junit.Assert.fail("lookupProductById threw a ServiceException. " + se.getMessage());
		}
				
	}

	@Test
	public void getProductWithBadProductId() {

		log.info("Running test: getProductWithBadProductId.");

		try {
			Product prod = productService.lookupProductById(BAD_PRODUCT_ID);
			org.junit.Assert.assertNull("lookupProductById with bad product id did not return null.", prod);
		} catch (ServiceException se) {
			se.printStackTrace();
			org.junit.Assert.fail("lookupProductById with bad product id threw a ServiceException. " + se.getMessage());
		}
				
	}
	
	
	@Test
	public void getAvailableInventory() {

		log.info("Running test: getAvailableInventory.");
		List<Product> prodList = new ArrayList<Product>();
		
		try {
			prodList = productService.getAllProducts();
			org.junit.Assert.assertNotNull("Setup call to getAllProducts returned null.", prodList);

		} catch (ServiceException se) {
			se.printStackTrace();
			org.junit.Assert.fail("Setup call to getAllProducts threw a ServiceException. " + se.getMessage());
		}
		
		for (Product p : prodList) {

			try {
				Long count = productService.getAvailableInventory(p.getId());
				org.junit.Assert.assertNotNull("getAvailableInventory returned null.", count);
				log.info("Available inventory for " + p.getProductCode() + ": " + count);
				
			} catch (ServiceException se) {
				se.printStackTrace();
				org.junit.Assert.fail("getAvailableInventory() threw a ServiceException. " + se.getMessage());			
			}
		}
	}

	@Test
	public void getAvailableInventoryForUnknownProduct() {

		log.info("Running test: getAvailableInventoryForUnknownProduct.");

		try {
			Long count = productService.getAvailableInventory(BAD_PRODUCT_ID);
			org.junit.Assert.assertNull("getAvailableInventoryForUnknownProduct did not return null.", count);
			
		} catch (ServiceException se) {
			se.printStackTrace();
			org.junit.Assert.fail("getAvailableInventory() threw a ServiceException. " + se.getMessage());			
		}
		
	}
	
}