/**
 * 
 */
package test.com.mymark.app.service;

import java.util.ArrayList;
import java.util.List;

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
import com.mymark.app.data.domain.Product;
import com.mymark.app.service.ProductService;
import com.mymark.app.service.ServiceException;

/**
 * @author Joseph Sicree
 *
 */
@RunWith(JUnit4.class)
public class ProductServiceTest {

	protected final static Logger log = LoggerFactory.getLogger(ProductServiceTest.class);

	private static final Long BAD_PRODUCT_ID = 999999L;
	
	private static AbstractApplicationContext context;
	private static ProductService productService;
	
	@BeforeClass
	public static void setup() {

		context = new AnnotationConfigApplicationContext(MyMarkAppConfig.class);
		productService = (ProductService) context.getBean("productService");
		
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