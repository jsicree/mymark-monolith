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
import com.mymark.app.data.reference.Language;
import com.mymark.app.service.GreetingService;
import com.mymark.app.service.ServiceException;

/**
 * @author Joseph Sicree
 *
 */
@RunWith(JUnit4.class)
public class GreetingServiceTest {

		protected final static Logger log = LoggerFactory.getLogger(GreetingServiceTest.class); 

		private static GreetingService greetingService;
		private static AbstractApplicationContext context;
		
		@BeforeClass
		public static void setup() {

			context = new AnnotationConfigApplicationContext(MyMarkAppConfig.class);		
			greetingService = (GreetingService) context
					.getBean("greetingService");		

		}

		@Test
		public void sayHello() {

			log.info(">> Entering sayHello.");
			try {
				String message = greetingService.sayHello();
				org.junit.Assert.assertNotNull("SayHello has returned null.", message);			
				log.debug("SayHello() has returned: " + message);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			log.info("<< Leaving sayHello.");
		}
		

		@Test
		public void sayHelloInLanguage() {

			log.info(">> Entering sayHelloInLanguage.");
			try {
				String message = greetingService.sayHello(Language.FRA);
				org.junit.Assert.assertNotNull("SayHello has returned null.", message);			
				log.debug("SayHello() has returned: " + message);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			
			log.info("<< Leaving sayHelloInLanguage.");
		}
		
		@Test
		public void sayHelloBadLanguage() {

			log.info(">> Entering sayHelloBadLanguage.");
			try {
				String message = greetingService.sayHello(Language.UNK);
				org.junit.Assert.fail("Call to sayHello using unknown language did not throw ServiceException");
			} catch (ServiceException e) {
				log.debug("Call to sayHello() with unknown language threw a ServiceException: " + e.getMessage());
			}
			
			log.info("<< Leaving sayHelloBadLanguage.");
		}

		
		@Test
		public void sayHelloByName() {

			log.info(">> Entering sayHelloByName.");
			String name = "John Doe";
			
			try {
				String message = greetingService.sayHello(name);
				org.junit.Assert.assertNotNull("SayHello(name) has returned null.", message);						
				log.debug("SayHello(name) has returned: " + message);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.info("<< Leaving sayHelloByName.");
		}
		
		@Test
		public void sayHelloByNameInLanguage() {

			log.info(">> Entering sayHelloByNameInLanguage.");
			String name = "John Doe";
			
			try {
				String message = greetingService.sayHello(Language.FRA, name);
				org.junit.Assert.assertNotNull("SayHello(name) has returned null.", message);						
				log.debug("SayHello(name) has returned: " + message);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			log.info("<< Leaving sayHelloByName.");
		}

		@Test
		public void sayHelloByNameBadLanguage() {

			log.info(">> Entering sayHelloByNameBadLanguage.");
			String name = "John Doe";
			
			try {
				String message = greetingService.sayHello(Language.UNK, name);
				org.junit.Assert.fail("Call to sayHello(language, name) using unknown language did not throw ServiceException");
			} catch (ServiceException e) {
				log.debug("Call to sayHello(language, name) with unknown language threw a ServiceException: " + e.getMessage());
			}

			log.info("<< Leaving sayHelloByNameBadLanguage.");
		}
		
		@AfterClass
		public static void after() {
			if (context != null) {
				context.close();
			}
		}

	}