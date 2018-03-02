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

import com.mymark.app.config.SpringConfig;
import com.mymark.app.service.HelloService;
import com.mymark.app.service.ServiceException;

/**
 * @author Joseph Sicree
 *
 */
@RunWith(JUnit4.class)
public class HelloServiceTest {

		protected final static Logger log = LoggerFactory.getLogger(HelloServiceTest.class); 

		private static HelloService helloService;
		private static AbstractApplicationContext context;
		
		@BeforeClass
		public static void setup() {

			context = new AnnotationConfigApplicationContext(SpringConfig.class);		
			helloService = (HelloService) context
					.getBean("helloService");		

		}

		@Test
		public void sayHello() {

			log.info(">> Entering sayHello.");
			try {
				String message = helloService.sayHello();
				org.junit.Assert.assertNotNull("SayHello has returned null.", message);			
				log.debug("SayHello() has returned: " + message);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			log.info("<< Leaving sayHello.");
		}
		

		@Test
		public void sayHelloByName() {

			log.info(">> Entering sayHelloByName.");
			String name = "John Doe";
			
			try {
				String message = helloService.sayHello(name);
				org.junit.Assert.assertNotNull("SayHello(name) has returned null.", message);						
				log.debug("SayHello(name) has returned: " + message);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.info("<< Leaving sayHelloByName.");
		}
		
		
		@AfterClass
		public static void after() {
			if (context != null) {
				context.close();
			}
		}

	}