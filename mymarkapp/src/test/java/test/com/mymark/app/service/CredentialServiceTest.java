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
import com.mymark.app.data.domain.Customer;
import com.mymark.app.service.CredentialService;
import com.mymark.app.service.CustomerService;
import com.mymark.app.service.ServiceException;

/**
 * @author Joseph Sicree
 *
 */
@Ignore
@RunWith(JUnit4.class)
public class CredentialServiceTest {

	public static String[][] customerData = {
			{ "Alex", "Lifeson", "lerxst", "lerxst@foo.com", "password" }, 
			{ "Geddy", "Lee", "dirk", "dirk@foo.com", "password" },
			{ "Neil", "Peart", "pratt", "pratt@foo.com", "password" } };


	protected final static Logger log = LoggerFactory.getLogger(CredentialServiceTest.class);

	private static CustomerService customerService;
	private static CredentialService credentialService;
	private static AbstractApplicationContext context;

	@BeforeClass
	public static void setup() {

		context = new AnnotationConfigApplicationContext(MyMarkAppConfig.class);
		customerService = (CustomerService) context.getBean("customerService");
		credentialService = (CredentialService) context.getBean("credentialService");

		removeCustomers();
		addCustomers();
	}

	@Ignore
	@Test
	public void createCredentials() {

		try {

			for (int x = 0; x < customerData.length; x++) {
				Customer customer = customerService.lookupCustomerByUserName(customerData[x][2]);
				Boolean isCredCreated = credentialService.addCredential(customer.getId(), customerData[x][4]);				
				org.junit.Assert.assertTrue("AddCredential has not returned true.", isCredCreated);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void after() {
		removeCustomers();
		if (context != null) {
			context.close();
		}
	}

	private static void addCustomers() {
		for (int x = 0; x < customerData.length; x++) {

			try {
				Customer c = customerService.lookupCustomerByUserName(customerData[x][2]);
				if (c == null) {
					customerService.createNewCustomer(customerData[x][0], customerData[x][1], customerData[x][2],
							customerData[x][3], customerData[x][4]);
				}
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private static void removeCustomers() {
		for (int x = 0; x < customerData.length; x++) {

			try {
				Customer c = customerService.lookupCustomerByUserName(customerData[x][2]);
				if (c != null) {
					credentialService.removeCredential(c.getId());
					customerService.deleteCustomer(c.getId());
				}
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}