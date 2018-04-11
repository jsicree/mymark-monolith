package test.com.mymark.api.client;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mymark.api.CustomerDto;
import com.mymark.api.client.ClientException;
import com.mymark.api.client.CustomerRestClient;

@RunWith(JUnit4.class)
public class CustomerRestClientTest {

	private static CustomerRestClient client;
	
	protected final static Logger log = LoggerFactory.getLogger(CustomerRestClientTest.class);

	public CustomerRestClientTest() {
		// TODO Auto-generated constructor stub
	}

	@BeforeClass
	public static void setup() {
		client = new CustomerRestClient("http://localhost:8080/mymarkservice/api/customer");		
	}

	@Test
	public void createNewCustomer() {
		
		try {
			CustomerDto dto = client.createNewCustomer("Ronnie","Dio","ronnie-james","rjd@foo.com","password1234");
			org.junit.Assert.assertNotNull("REST Client call to /customer has returned null.", dto);
			log.info("REST Client call to /customer has returned: " + dto);

		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@AfterClass
	public static void after() {
	}
	
}
