/**
 * 
 */
package test.com.mymark.app.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.mymark.app.data.domain.Account;
import com.mymark.app.data.domain.Credential;
import com.mymark.app.data.domain.Customer;
import com.mymark.app.data.enums.AccountStatus;
import com.mymark.app.jpa.repository.AccountRepository;
import com.mymark.app.jpa.repository.CredentialRepository;
import com.mymark.app.jpa.repository.CustomerRepository;
import com.mymark.app.service.CustomerService;
import com.mymark.app.service.ServiceException;
import com.mymark.app.service.impl.CustomerServiceImpl;

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

	protected final static Account NEW_CUSTOMER_ACCOUNT = new Account(1L,AccountStatus.NEW, null);
	protected final static Credential NEW_CUSTOMER_CREDENTIAL = new Credential(1L,"newpassword");
	protected final static Customer NEW_CUSTOMER_INPUT = new Customer("new_user", "New", "User", "newUser@foo.com", NEW_CUSTOMER_ACCOUNT);
	protected final static Customer NEW_CUSTOMER = new Customer(1L, "new_user", "New", "User", "newUser@foo.com", NEW_CUSTOMER_ACCOUNT);

	protected final static Account SEARCH_CUSTOMER_ACCOUNT = new Account(2L,AccountStatus.NEW, null);
	protected final static Customer SEARCH_CUSTOMER = new Customer(2L, "existing_user", "Existing", "User", "existingUser@foo.com", SEARCH_CUSTOMER_ACCOUNT);
	
	//private static AbstractApplicationContext context;
	private static CustomerService customerService;

	private static CustomerRepository customerRepoMock;
	private static AccountRepository accountRepoMock;
	private static CredentialRepository credentialRepoMock;

	
	@BeforeClass
	public static void setup() {

//		context = new AnnotationConfigApplicationContext(MyMarkAppConfig.class);
//		customerService = (CustomerService) context.getBean("customerService");
//
//		removeCustomers();
//		removeNewCustomers();
//		addCustomers();
		
		accountRepoMock = mock(AccountRepository.class);
		when(accountRepoMock.save(NEW_CUSTOMER_ACCOUNT)).thenReturn(NEW_CUSTOMER_ACCOUNT);

		customerRepoMock = mock(CustomerRepository.class);
		when(customerRepoMock.findByUserName(NEW_CUSTOMER.getUserName())).
			thenReturn(null);
		when(customerRepoMock.findByEmail(NEW_CUSTOMER.getEmail())).
		thenReturn(null);
		when(customerRepoMock.save(any(Customer.class))).
		thenReturn(NEW_CUSTOMER);

		when(customerRepoMock.findByEmail(SEARCH_CUSTOMER.getEmail())).
		thenReturn(SEARCH_CUSTOMER);
		
		when(customerRepoMock.findByUserName(SEARCH_CUSTOMER.getUserName())).
		thenReturn(SEARCH_CUSTOMER);
		
		credentialRepoMock = mock(CredentialRepository.class);
		when(credentialRepoMock.save(any(Credential.class))).
		thenReturn(NEW_CUSTOMER_CREDENTIAL);
		
		customerService = new CustomerServiceImpl(customerRepoMock, credentialRepoMock, accountRepoMock);
	}
	
	@AfterClass
	public static void after() {
//		removeCustomers();
//		removeNewCustomers();
//		if (context != null) {
//			context.close();
//		}
	}


	@Test
	public void createNewCustomerMock() {

		log.info("Running test: createNewCustomerMock.");

		try {
			Customer c = customerService.createNewCustomer(
					NEW_CUSTOMER_INPUT.getFirstName(), NEW_CUSTOMER_INPUT.getLastName(), NEW_CUSTOMER_INPUT.getUserName(), NEW_CUSTOMER_INPUT.getEmail(), "newpassword");
			org.junit.Assert.assertNotNull("createNewCustomer returned null.", c);
			org.junit.Assert.assertEquals("createNewCustomer returned null.", c, NEW_CUSTOMER);
			//log.info("Mock Customer: " + c);
		} catch (ServiceException se) {
			
		}
		
	}

	@Test
	public void lookupCustomerByUserNameMock() {

		log.info("Running test: lookupCustomerByUserNameMock.");

		try {
			Customer c = customerService.lookupCustomerByUserName(SEARCH_CUSTOMER.getUserName());			
			org.junit.Assert.assertNotNull("lookupCustomerByUserName returned null.", c);
			org.junit.Assert.assertEquals("lookupCustomerByUserName returned null.", c, SEARCH_CUSTOMER);
			//log.info("Mock Customer: " + c);
		} catch (ServiceException se) {
			
		}		
	}

	@Test
	public void lookupCustomerByEmailMock() {

		log.info("Running test: lookupCustomerByEmailMock.");

		try {
			Customer c = customerService.lookupCustomerByEmail(SEARCH_CUSTOMER.getEmail());			
			org.junit.Assert.assertNotNull("lookupCustomerByEmail returned null.",c);
			org.junit.Assert.assertEquals("lookupCustomerByEmail returned null.", c, SEARCH_CUSTOMER);
			//log.info("Mock Customer: " + c);
		} catch (ServiceException se) {
			
		}		
	}
	
	
	@Ignore
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

	@Ignore
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

	@Ignore
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