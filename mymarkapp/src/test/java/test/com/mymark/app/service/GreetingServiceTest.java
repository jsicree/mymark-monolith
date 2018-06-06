/**
 * 
 */
package test.com.mymark.app.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mymark.app.data.domain.Greeting;
import com.mymark.app.data.reference.Language;
import com.mymark.app.jpa.repository.GreetingRepository;
import com.mymark.app.service.GreetingService;
import com.mymark.app.service.ServiceException;
import com.mymark.app.service.impl.GreetingServiceImpl;

/**
 * @author Joseph Sicree
 *
 */
@RunWith(JUnit4.class)
public class GreetingServiceTest {

	protected final static Logger log = LoggerFactory.getLogger(GreetingServiceTest.class);

	private static final String MOCK_SIMPLE_MESSAGE = "Mock Simple Message";
	private static final String MOCK_NAMED_MESSAGE = "Mock Message for John Doe";
	private static final String TEST_NAME = "John Doe";
	
	
//	private static AbstractApplicationContext context;
	private static GreetingService greetingService;
	private static GreetingRepository greetingRepoMock;

	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void setup() {

//		context = new AnnotationConfigApplicationContext(MyMarkAppConfig.class);
//		greetingService = (GreetingService) context.getBean("greetingService");
		
		log.info("GreetingServiceTest is using mock objects!");
		greetingRepoMock = mock(GreetingRepository.class);
		when(greetingRepoMock.findByLanguage(Language.ENG))
		.thenReturn(new Greeting(1L, Language.ENG, MOCK_SIMPLE_MESSAGE, MOCK_NAMED_MESSAGE));
		when(greetingRepoMock.findByLanguage(Language.FRA))
		.thenReturn(new Greeting(1L, Language.FRA, MOCK_SIMPLE_MESSAGE, MOCK_NAMED_MESSAGE));
		when(greetingRepoMock.findByLanguage(Language.UNK))
		.thenThrow(ServiceException.class);		
		greetingService = new GreetingServiceImpl(greetingRepoMock);
	}

	@AfterClass
	public static void after() {
//		if (context != null) {
//			context.close();
//		}
	}


	@Test
	public void sayHello() {

		log.info("Running test: sayHello.");

		String result = null;
		try {
			result = greetingService.sayHello(Language.ENG);
			org.junit.Assert.assertNotNull("SayHello(Language) has returned null.", result);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void sayHelloInLanguage() {

		log.info("Running test: sayHelloInLanguage.");

		try {
			String message = greetingService.sayHello(Language.FRA);
			org.junit.Assert.assertNotNull("SayHello(Language) has returned null.", message);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void sayHelloUnknownLanguage() {

		log.info("Running test: sayHelloUnknownLanguage");

		try {
			String result = greetingService.sayHello(Language.UNK);
			org.junit.Assert.fail("Call to sayHello using unknown language did not throw ServiceException");
		} catch (ServiceException e) {
			// ServiceException was expected for this test
		}
	}

	@Test
	public void sayHelloByName() {

		log.info("Running test: sayHelloByName");

		try {
			String message = greetingService.sayHello(TEST_NAME);
			org.junit.Assert.assertNotNull("SayHello(name) has returned null.", message);
			//log.debug("SayHello(name) has returned: " + message);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void sayHelloByNameInLanguage() {

		log.info("Running test: sayHelloByNameInLanguage");

		try {
			String message = greetingService.sayHello(Language.FRA, TEST_NAME);
			org.junit.Assert.assertNotNull("SayHello(name) has returned null.", message);
			//org.junit.Assert.assertEquals("SayHello(name) did not return the expected message.", MOCK_NAMED_MESSAGE, message);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void sayHelloByNameUnknownLanguage() {

		log.info("Running test: sayHelloByNameUnknownLanguage");

		try {
			String message = greetingService.sayHello(Language.UNK, TEST_NAME);
			org.junit.Assert
					.fail("Call to sayHello(language, name) using unknown language did not throw ServiceException");
		} catch (ServiceException e) {
		}
	}
		

}
