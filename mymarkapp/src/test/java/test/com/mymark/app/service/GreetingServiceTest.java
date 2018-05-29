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
	private static final String MOCK_NAME = "John Doe";
	
	
//	private static GreetingService greetingService;
//	private static AbstractApplicationContext context;
	private static GreetingServiceImpl serviceImpl;
	private static GreetingRepository greetingRepoMock;

	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void setup() {

//		context = new AnnotationConfigApplicationContext(MyMarkAppConfig.class);
//		greetingService = (GreetingService) context.getBean("greetingService");
		
		greetingRepoMock = mock(GreetingRepository.class);
		when(greetingRepoMock.findByLanguage(Language.ENG))
		.thenReturn(new Greeting(1L, Language.ENG, MOCK_SIMPLE_MESSAGE, MOCK_NAMED_MESSAGE));
		when(greetingRepoMock.findByLanguage(Language.FRA))
		.thenReturn(new Greeting(1L, Language.FRA, MOCK_SIMPLE_MESSAGE, MOCK_NAMED_MESSAGE));
		when(greetingRepoMock.findByLanguage(Language.UNK))
		.thenThrow(ServiceException.class);

		serviceImpl = new GreetingServiceImpl(greetingRepoMock);
	}


	@Test
	public void sayHelloMock() {

		log.info("Running test: sayHelloMock.");

		String result = null;
		try {
			result = serviceImpl.sayHello(Language.ENG);
			org.junit.Assert.assertEquals(MOCK_SIMPLE_MESSAGE, result);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void sayHelloInLanguageMock() {

		log.info("Running test: sayHelloInLanguageMock.");

		GreetingServiceImpl serviceImpl = new GreetingServiceImpl(greetingRepoMock);		
		try {
			String message = serviceImpl.sayHello(Language.FRA);
			org.junit.Assert.assertNotNull("SayHello has returned null.", message);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void sayHelloUnknownLanguageMock() {

		log.info("Running test: sayHelloUnknownLanguageMock");

		try {
			String result = serviceImpl.sayHello(Language.UNK);
			org.junit.Assert.fail("Call to sayHello using unknown language did not throw ServiceException");
		} catch (ServiceException e) {
			// ServiceException was expected for this test
		}
	}

	@Test
	public void sayHelloByNameMock() {

		log.info("Running test: sayHelloByNameMock");

		try {
			String message = serviceImpl.sayHello(MOCK_NAME);
			org.junit.Assert.assertNotNull("SayHello(name) has returned null.", message);
			//log.debug("SayHello(name) has returned: " + message);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void sayHelloByNameInLanguageMock() {

		log.info("Running test: sayHelloByNameInLanguageMock");

		try {
			String message = serviceImpl.sayHello(Language.FRA, MOCK_NAME);
			org.junit.Assert.assertNotNull("SayHello(name) has returned null.", message);
			org.junit.Assert.assertEquals("SayHello(name) did not return the expected message.", MOCK_NAMED_MESSAGE, message);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void sayHelloByNameUnknownLanguageMock() {

		log.info("Running test: sayHelloByNameUnknownLanguageMock");

		try {
			String message = serviceImpl.sayHello(Language.UNK, MOCK_NAME);
			org.junit.Assert
					.fail("Call to sayHello(language, name) using unknown language did not throw ServiceException");
		} catch (ServiceException e) {
		}
	}
		
	@AfterClass
	public static void after() {
	}

}
