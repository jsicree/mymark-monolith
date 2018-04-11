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
import com.mymark.api.client.ClientException;
import com.mymark.api.client.CustomerRestClient;

@RunWith(JUnit4.class)
public class CustomerRestClientTest {

	private static CustomerRestClient client;
	
	protected final static Logger log = LoggerFactory.getLogger(CustomerRestClientTest.class);

	public static String[][] customerData = { 
			{ "John", "Smith", "jsmith01", "jsmith01@foo.com", "password1234"},
			{ "Jane", "Riggs", "jriggs01", "jriggs01@foo.com", "password1234"}, 
			{ "Bill", "Nye", "sciguy1234", "sciguy1234@foo.com", "password1234"},
			{ "Alex", "Lifeson", "lerxst", "lerxst@foo.com", "password1234"}, 
			{ "Geddy", "Lee", "dirk", "dirk@foo.com", "password1234"},
			{ "Neil", "Peart", "pratt", "pratt@foo.com", "password1234"} };
	
	
	public CustomerRestClientTest() {
		// TODO Auto-generated constructor stub
	}

	@BeforeClass
	public static void setup() {
		client = new CustomerRestClient("http://localhost:8080/mymarkservice/api/customer");		
	}

	@Test
	public void createNewCustomer() {

		ArrayList<Long> idList = new ArrayList<Long>();
		
		for (int x = 0; x < customerData.length; x++) {
			try {
				CustomerDto dto = client.createNewCustomer(
						customerData[x][0], customerData[x][1], customerData[x][2], customerData[x][3], customerData[x][4]);
				org.junit.Assert.assertNotNull("REST Client call to POST /customer has returned null.", dto);
				log.info("REST Client POST to /customer has returned: " + dto);
				idList.add(dto.getId());
			} catch (ClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (Long id: idList) {
			try {
				client.deleteCustomer(id);
			} catch (ClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}

	@AfterClass
	public static void after() {
	}
	
}
