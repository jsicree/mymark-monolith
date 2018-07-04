/**
 * 
 */
package test.com.mymark.app.service;

import java.util.List;

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
import com.mymark.app.data.domain.Customer;
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

	
	private static AbstractApplicationContext context;
	private static ProductService productService;

//	private static CustomerRepository customerRepoMock;
//	private static AccountRepository accountRepoMock;
//	private static CredentialRepository credentialRepoMock;

	
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
			
		}
		
	}

}