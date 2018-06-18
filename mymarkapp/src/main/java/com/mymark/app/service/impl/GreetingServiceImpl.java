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
import com.mymark.app.data.enums.Language;
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
	
	public GreetingServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public GreetingServiceImpl(GreetingRepository greetingRepo) {
		this.greetingRepo = greetingRepo;
	}

	public String sayHello() throws ServiceException {

		return sayHello(Language.ENG) ;
	}

	public String sayHello(String name) throws ServiceException {
		return sayHello(Language.ENG, name) ;
	}

	public String sayHello(Language language) throws ServiceException {
		String message = null;
		
		Greeting greeting = greetingRepo.findByLanguage(language);
		if (greeting != null) {
			message = greeting.getSimpleMessage();
		} else {
			throw new ServiceException("No greeting found for language " + language);
		}
		return message ;
	}

	public String sayHello(Language language, String name) throws ServiceException {
		String message = null;

		if (name != null) {
			Greeting greeting = greetingRepo.findByLanguage(language);
			if (greeting != null) {
				String namedMessage = greeting.getNamedMessage();
				Map<String,String> map = new HashMap<String,String>();
				map.put("name", name);
				message = StrSubstitutor.replace(namedMessage, map,"{","}");
			} else {
				throw new ServiceException("No greeting found for language " + language);
			}			
		} else {
			message = sayHello(language);
		}

		return message;
	}

}
