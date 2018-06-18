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
import com.mymark.app.data.domain.Customer;
import com.mymark.app.service.CustomerService;
import com.mymark.app.service.ServiceException;

/**
 * @author Joseph Sicree
 *
 */
@RunWith(JUnit4.class)
public class CustomerServiceTest {

	public static String[][] customerData = { { "John", "Smith", "jsmith01", "jsmith01@foo.com" },
			{ "Jane", "Riggs", "jriggs01", "jriggs01@foo.com" }, { "Bill", "Nye", "sciguy1234", "sciguy1234@foo.com" },
			{ "Alex", "Lifeson", "lerxst", "lerxst@foo.com" }, { "Geddy", "Lee", "dirk", "dirk@foo.com" },
			{ "Neil", "Peart", "pratt", "pratt@foo.com" } };

	public static String[][] newCustomerData = { { "John", "Cleese", "lancelot", "lancelot@foo.com" },
			{ "Eric", "Idle", "robin", "robin@foo.com" } };

	public static String DEFAULT_PASSWORD = "password";
	
	protected final static Logger log = LoggerFactory.getLogger(CustomerServiceTest.class);

	private static CustomerService customerService;
	private static AbstractApplicationContext context;

	@BeforeClass
	public static void setup() {

		context = new AnnotationConfigApplicationContext(MyMarkAppConfig.class);
		customerService = (CustomerService) context.getBean("customerService");

		removeCustomers();
		removeNewCustomers();
		addCustomers();
	}

	@Test
	public void createNewCustomer() {

		log.info("Running test: createNewCustomer");

		try {

			for (int x = 0; x < newCustomerData.length; x++) {
				Customer customer = customerService.createNewCustomer(newCustomerData[x][0], 
						newCustomerData[x][1], newCustomerData[x][2], 
						newCustomerData[x][3], DEFAULT_PASSWORD);
				org.junit.Assert.assertNotNull("CreateNewCustomer has returned null.", customer);
				log.debug("CreateNewCustomer has returned: " + customer);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void lookupCustomerByEmail() {

		log.info("Running test: lookupCustomerByEmail");
		try {

			for (int x = 0; x < customerData.length; x++) {
				Customer customer = customerService.lookupCustomerByEmail(customerData[x][3]);
				org.junit.Assert.assertNotNull("LookupCustomerByEmail has returned null.", customer);
				log.debug("LookupCustomerByEmail has returned: " + customer);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void lookupCustomerByUserName() {

		log.info("Running test: lookupCustomerByUserName");
		try {

			for (int x = 0; x < customerData.length; x++) {
				Customer customer = customerService.lookupCustomerByUserName(customerData[x][2]);
				org.junit.Assert.assertNotNull("LookupCustomerByUserName has returned null.", customer);
				log.debug("LookupCustomerByUserName has returned: " + customer);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void after() {
		removeCustomers();
		removeNewCustomers();
		if (context != null) {
			context.close();
		}
	}

	private static void removeNewCustomers() {
		for (int x = 0; x < newCustomerData.length; x++) {

			try {
				Customer c = customerService.lookupCustomerByUserName(newCustomerData[x][2]);
				if (c != null) {
					customerService.deleteCustomer(c.getId());
				}
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void addCustomers() {
		for (int x = 0; x < customerData.length; x++) {

			try {
				Customer c = customerService.lookupCustomerByUserName(customerData[x][2]);
				if (c == null) {
					customerService.createNewCustomer(customerData[x][0], customerData[x][1], customerData[x][2],
							customerData[x][3], DEFAULT_PASSWORD);
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
					customerService.deleteCustomer(c.getId());
				}
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}