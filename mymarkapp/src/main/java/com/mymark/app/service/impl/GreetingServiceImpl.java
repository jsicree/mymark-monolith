/**
 * 
 */
package com.mymark.app.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymark.app.data.domain.Greeting;
import com.mymark.app.data.reference.Language;
import com.mymark.app.jpa.repository.GreetingRepository;
import com.mymark.app.service.GreetingService;
import com.mymark.app.service.ServiceException;

/**
 * @author joseph_sicree
 *
 */
@Service
public class GreetingServiceImpl implements GreetingService {

	@Autowired
	private GreetingRepository greetingRepo;
	
	private static final String DEFAULT_MESSAGE = "Hi";

	/**
	 * 
	 */
	public GreetingServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.mymark.app.service.HelloService#sayHello()
	 */
	public String sayHello() throws ServiceException {

		String message = null;
		
		Greeting greeting = greetingRepo.findByLanguage(Language.ENG);
		if (greeting != null) {
			message = greeting.getSimpleMessage();
		} else {
			message = GreetingServiceImpl.DEFAULT_MESSAGE;
		}
		return message ;
	}

	/* (non-Javadoc)
	 * @see com.mymark.app.service.HelloService#sayHello(java.lang.String)
	 */
	public String sayHello(String name) throws ServiceException {
		String message = null;

		if (name != null) {
			Greeting greeting = greetingRepo.findByLanguage(Language.ENG);
			if (greeting != null) {
				String namedMessage = greeting.getNamedMessage();
				Map<String,String> map = new HashMap<String,String>();
				map.put("name", name);
				message = StrSubstitutor.replace(namedMessage, map,"{","}");
			} else {
				message = GreetingServiceImpl.DEFAULT_MESSAGE;
			}			
		} else {
			message = sayHello();
		}

		return message;
	}

}
